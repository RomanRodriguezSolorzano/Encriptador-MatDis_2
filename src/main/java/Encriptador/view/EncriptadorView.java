package Encriptador.view;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EncriptadorView{


    public static String pedirMensaje(){
        BufferedReader bufer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingresa el mensaje que quieras encriptar");
        try {
            return bufer.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarMensajeCifrado(double [][] matriz, int columnas){
        String palabra = "";
        for(int y=0;y<columnas;y++){
            for(int x=0;x<3;x++){
                palabra += (char)matriz[x][y];
            }
        }
        System.out.println(palabra);
    }



    public static void mostrarMatrizUnida(double [][] matrizUnida, int columnas){
        String matriz = "";
        for(int y=0;y<3;y++){
            for(int x=0;x<columnas;x++){
                matriz += String.format("%.0f",matrizUnida[y][x]) + " ";
            }
            matriz += "\n";
        }
        System.out.println(matriz);
    }



}
