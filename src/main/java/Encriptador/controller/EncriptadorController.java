package Encriptador.controller;

import Encriptador.model.Encriptador;

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

    public int [][] generarMatriz(String mensaje){
        int [][] matriz;
        int posicion = 0;
        int columnas = mensaje.length()%3==0?mensaje.length()/3:(int)(mensaje.length()/3 + 1);
        matriz = new int[3][columnas];
        for (int fila=0;fila<columnas;fila++){
            for(int columna=0;columna<3;columna++){
                matriz[columna][fila] = posicion<mensaje.length()&&mensaje.charAt(posicion)!=32?(int)mensaje.charAt(posicion)-96:0;
                posicion++;
            }
        }
        return matriz;
    }


}
