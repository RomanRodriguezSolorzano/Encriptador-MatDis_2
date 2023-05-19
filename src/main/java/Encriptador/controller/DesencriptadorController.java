package Encriptador.controller;

import Encriptador.model.Encriptador;
import Encriptador.view.DescencriptadorView;
import Encriptador.view.EncriptadorView;

public class DesencriptadorController {
    Encriptador encriptador;

    public DesencriptadorController(){
        encriptador = new Encriptador();
    }

    void obtenerLlaveYMatrizColumnas(String palabraEncriptada){
        int columnas = (palabraEncriptada.length()-9)/3;
        double[][] llave = new double[3][3];
        double [][] matrizCifrada = new double[3][columnas];
        int posicion=0;
        for(int y=0;y<columnas;y++){
            for(int x=0;x<3;x++){
                matrizCifrada[x][y] = (int) palabraEncriptada.charAt(posicion);
                posicion++;
            }
        }
        for(int y=0;y<3;y++){
            for(int x=0;x<3;x++){
                llave[x][y] =  (int) palabraEncriptada.charAt(posicion);
                posicion++;
            }
        }

        encriptador.setColumnas(columnas);
        encriptador.setLlave(llave);
        encriptador.setMatrizCifrada(matrizCifrada);
    }


    public void desencriptarMatriz(){
        encriptador.setMensajeEncriptado(DescencriptadorView.pedirMensaje());
        obtenerLlaveYMatrizColumnas(encriptador.getMensajeEncriptado());
        double determinante = determinante(encriptador.getLlave());
        double [][] adjunta = adjunta(Cofactores(encriptador.getLlave()));
        double [][] inversa = inversa(adjunta,determinante);
        double [][] resultado = multiplicacionMatrices(inversa,encriptador.getMatrizCifrada(),encriptador.getColumnas());
        //EncriptadorView.mostrarMatrizUnida(encriptador.getMatrizCifrada(),encriptador.getColumnas());
        //EncriptadorView.mostrarMatrizUnida(encriptador.getLlave(),3);
        //EncriptadorView.mostrarMatrizUnida(adjunta,3);
        //EncriptadorView.mostrarMatrizUnida(inversa,3);
        //EncriptadorView.mostrarMatrizUnida(resultado,encriptador.getColumnas());
        DescencriptadorView.mostrarMensajeDescifrado(resultado,encriptador.getColumnas());
    }

    double [][] adjunta(double [][] llave){
        double [][] adjunta = new double[3][3];
        for(int y=0;y<3;y++){
            for(int x=0;x<3;x++){
                adjunta[y][x]=llave[x][y];
            }
        }
        return adjunta;
    }




    double determinante(double [][]llave){
        return ((llave[0][0])*(llave[1][1])*(llave[2][2])+(llave[1][0])*(llave[2][1])*(llave[0][2])+(llave[2][0])*(llave[0][1])*(llave[1][2]))-((llave[2][0])*(llave[1][1])*(llave[0][2])+(llave[1][0])*(llave[0][1])*(llave[2][2])+(llave[0][0])*(llave[2][1])*(llave[1][2]));
    }

    double [][] Cofactores(double [][] llave){//Obtencion de Cofactores (Matriz LLave)
        double  VarCof[]=new double[4]; //Variables Cofactores
        double [][] Cof = new double [3][3];
        double s=1;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){

                int in=0; //indice Variables Cof
                for(int k=0;k<3;k++){
                    for(int l=0;l<3;l++){
                        if(k!=i&&l!=j){
                            VarCof[in]=llave[k][l];
                            in++;
                        }
                    }
                }//Fin ciclos Variables Cofactores

                //Obtencion Cofactores
                if(s==2||s==4||s==6||s==8){
                    Cof[i][j]=(VarCof[0]*VarCof[3]-VarCof[1]*VarCof[2])*-1;
                }else{
                    Cof[i][j]=VarCof[0]*VarCof[3]-VarCof[1]*VarCof[2]; //Formula Cofactores
                }
                s++;
            }//Fin ciclo CF
        }//Fin ciclo Cofactores -

        return Cof;
    }//Fin Metodo Cofactore

    double [][] inversa(double [][] adjunta, double determinante){
        double [][] resultado = new double[3][3];
        for(int y=0;y<3;y++){
            for(int x=0;x<3;x++){
                resultado[y][x] += adjunta[y][x] * (1/determinante);
            }

        }
        return resultado;
    }

    double [][] multiplicacionMatrices(double [][] llave,double [] [] matriz, int columnas){
        double [][] resultado = new double[3][columnas];
        for(int y=0;y<3;y++){
            for(int x=0;x<columnas;x++){
                for(int z=0;z<3;z++){
                    resultado[y][x] += (llave[y][z] * matriz[z][x]);
                }
            }

        }
        return resultado;
    }
}
