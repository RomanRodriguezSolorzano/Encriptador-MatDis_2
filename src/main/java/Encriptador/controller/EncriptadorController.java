package Encriptador.controller;

import Encriptador.model.Encriptador;
import Encriptador.view.EncriptadorView;

public class EncriptadorController {

    private Encriptador encriptador;
    public EncriptadorController() {
        encriptador = new Encriptador();
    }

    public int [] [] generarLlave(){
        int [][] llaveGenerada = new int [3][3];
        int maximo = 6;
        int minimo = 1;
        int rango = maximo - minimo + 1;
        for(int fila=0;fila<3;fila++){
            for (int columna=0;columna<3;columna++){
                llaveGenerada[fila][columna] = (int)(Math.random() * rango) + minimo;
            }
        }
        return llaveGenerada;
    }

    public int [][] generarMatriz(String mensaje,int columnasMatriz){
        int [][] matriz;
        int posicion = 0;
        matriz = new int[3][columnasMatriz];
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

    int [][] unirMatrices(int [][] llave, int [][] matriz, int columnas){
        int [][] union = new int[3][columnas+3];
        for(int y=0;y<3;y++){
            for(int x=0;x<columnas;x++){
                union[y][x] = matriz[y][x];
                if(x<3){
                    union[y][x+columnas] = llave[y][x];
                }
            }
        }
        return union;
    }

    String mensajeCifrado(int [][] matrizCifrada, int columnas){
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
        EncriptadorView.mostrarMatrizUnida(encriptador.getUnionMatrices(), encriptador.getColumnas()+3);
        EncriptadorView.mostrarMensajeCifrado(encriptador.getMatrizCifrada(), encriptador.getColumnas());
    }

}
