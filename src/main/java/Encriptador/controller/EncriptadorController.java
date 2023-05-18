package Encriptador.controller;

import Encriptador.model.Encriptador;
import Encriptador.view.EncriptadorView;

public class EncriptadorController {

    private Encriptador encriptador;
    public EncriptadorController() {
        encriptador = new Encriptador();
    }

    public double [] [] generarLlave(){
        double [][] llaveGenerada = new double [3][3];
        int maximo = 10;
        int minimo = 1;
        int rango = maximo - minimo + 1;
        for(int fila=0;fila<3;fila++){
            for (int columna=0;columna<3;columna++){
                llaveGenerada[fila][columna] = (int)(Math.random() * rango) + minimo;
            }
        }
        return llaveGenerada;
    }

    public double [][] generarMatriz(String mensaje,int columnasMatriz){
        double [][] matriz;
        int posicion = 0;
        matriz = new double[3][columnasMatriz];
        for (int fila=0;fila<columnasMatriz;fila++){
            for(int columna=0;columna<3;columna++){
                matriz[columna][fila] = posicion<mensaje.length()&&mensaje.charAt(posicion)!=32?(int)mensaje.charAt(posicion)-96:0;
                posicion++;
            }
        }
        return matriz;
    }

    public int obtenerColumnas(String mensaje){
        return mensaje.length()%3==0?mensaje.length()/3:(int)(mensaje.length()/3 + 1);
    }


    int [][] multiplicacionMatrices(int [][] llave,int [] [] matriz, int columnas){
        int [][] resultado = new int[3][columnas];
        for(int y=0;y<3;y++){
            for(int x=0;x<columnas;x++){
                for(int z=0;z<3;z++){
                    resultado[y][x] += llave[y][z] * matriz[z][x];
                }
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

    double [][] unirMatrices(double [][] llave, double [][] matriz, int columnas){
        double [][] union = new double[3][columnas+3];
        for(int y=0;y<3;y++){
            for(int x=0;x<columnas;x++){
                union[y][x] = matriz[y][x];
            }
        }
        int t=0;
        for(int y=0;y<3;y++){
            t=0;
            for(int x=columnas;x<columnas+3;x++){
                union[y][x] = llave[y][t];
                t++;
            }
        }
        return union;
    }

    String mensajeCifrado(double [][] matrizCifrada, int columnas){
        String mensajeCifrado = "";
        for(int y=0;y<columnas;y++){
            for(int x=0;x<3;x++){
                mensajeCifrado += (char)matrizCifrada[x][y];
            }
        }
        return mensajeCifrado;
    }

    public void encriptarMensaje(){
        encriptador.setMensaje(EncriptadorView.pedirMensaje());
        encriptador.setColumnas(obtenerColumnas(encriptador.getMensaje()));
        encriptador.setLlave(generarLlave());
        encriptador.setMatrizMensaje(generarMatriz(encriptador.getMensaje(), encriptador.getColumnas()));
        encriptador.setMatrizCifrada(multiplicacionMatrices(encriptador.getLlave(),encriptador.getMatrizMensaje(),encriptador.getColumnas()));
        encriptador.setUnionMatrices(unirMatrices(encriptador.getLlave(),encriptador.getMatrizCifrada(),encriptador.getColumnas()));
        encriptador.setMensajeEncriptado(mensajeCifrado(encriptador.getMatrizCifrada(), encriptador.getColumnas()));

        //EncriptadorView.mostrarMatrizUnida(encriptador.getMatrizMensaje(),encriptador.getColumnas());
        //EncriptadorView.mostrarMatrizUnida(encriptador.getLlave(),3);
        //EncriptadorView.mostrarMatrizUnida(encriptador.getMatrizCifrada(),encriptador.getColumnas());
        EncriptadorView.mostrarMatrizUnida(encriptador.getLlave(), 3);
        EncriptadorView.mostrarMatrizUnida(encriptador.getUnionMatrices(), encriptador.getColumnas()+3);
        EncriptadorView.mostrarMensajeCifrado(encriptador.getUnionMatrices(), encriptador.getColumnas()+3);

        desencriptarMatriz(encriptador.getLlave(), encriptador.getMatrizCifrada(),encriptador.getColumnas());
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

    double [][] inversaMatriz(double [][] llave){
        double [][] inversa = {{1,0,0},{0,1,0},{0,0,1}};
        double valor = 0;
        int pivote =0;
        for(int y=0;y<3;y++){
            for(int x=0;x<=y;x++){
                if(y==x){
                    valor = llave[y][x];
                    for(int z=0;z<3;z++){
                        inversa[y][z] = inversa[y][z]/valor;
                    }
                }
                else{
                    if(x==1){
                        for(int t=0;t<2;t++){
                            for(int z=0;z<3;z++){
                                inversa[x][z] = inversa[t][z]*inversa[x][z]*(-1)+inversa[x][z];
                            }
                        }
                    }
                    else if(x==2){
                         for(int z=0;z<3;z++){
                               inversa[x][z] = inversa[1][z]*inversa[x][z]*(-1)+inversa[x][z];
                         }
                    }
                }
            }
        }
        for(int y=1;y>=0;y--){
            for(int x=2;x<=y;x++){
                    for(int z=0;z<3;z++){
                        inversa[y][z] = inversa[y][z]+(llave[y+1][z]*(-1));
                    }
            }
        }
        EncriptadorView.mostrarMatrizUnida(llave,3);
        EncriptadorView.mostrarMatrizUnida(inversa,3);
        return inversa;
    }

    double [][] inversa(double [][] adjunta, double determinante){
        double [][] resultado = new double[3][3];
        for(int y=0;y<3;y++){
            for(int x=0;x<3;x++){
                resultado[y][x] += adjunta[y][x] * (1/determinante);
            }

        }
        return resultado;
    }


    double determinante(double [][]llave){
        return ((llave[0][0])*(llave[1][1])*(llave[2][2])+(llave[1][0])*(llave[2][1])*(llave[0][2])+(llave[2][0])*(llave[0][1])*(llave[1][2]))-((llave[2][0])*(llave[1][1])*(llave[0][2])+(llave[1][0])*(llave[0][1])*(llave[2][2])+(llave[0][0])*(llave[2][1])*(llave[1][2]));
    }
    void desencriptarMatriz(double [][]llave, double [][]matrizcifrada,int columna){
        double determinante = determinante(llave);
        double [][] adjunta = adjunta(Cofactores(llave));
        double [][] inversa = inversa(adjunta,determinante);
        double [][] resultado = multiplicacionMatrices(inversa,matrizcifrada,columna);
        EncriptadorView.mostrarMatrizUnida(adjunta,3);
        EncriptadorView.mostrarMatrizUnida(inversa,3);
        EncriptadorView.mostrarMatrizUnida(resultado,2);
        EncriptadorView.mostrarMensajeDescifrado(resultado,columna);
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

}
