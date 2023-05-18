package Encriptador.view;

import javax.swing.*;

public class EncriptadorView{


    public static String pedirMensaje(){
        return JOptionPane.showInputDialog("Ingresa el mensaje que quieras encriptar");
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

    public static void mostrarMatrizUnida(int [][] matrizUnida, int columnas){
        String matriz = "";
        for(int y=0;y<3;y++){
            for(int x=0;x<columnas;x++){
                matriz += matrizUnida[y][x] + " ";
            }
            matriz += "\n";
        }
        System.out.println(matriz);
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

    public static void mostrarMensajeDescifrado(double [][] matriz, int columnas){
        String palabra = "";
        for(int y=0;y<columnas;y++){
            for(int x=0;x<3;x++){
                if(Integer.parseInt(String.format("%.0f",matriz[x][y]))==0){
                    palabra += " ";
                }else{
                    palabra += (char)(Integer.parseInt(String.format("%.0f",matriz[x][y]))+96);
                }
            }
        }
        System.out.println(palabra);
    }

}
