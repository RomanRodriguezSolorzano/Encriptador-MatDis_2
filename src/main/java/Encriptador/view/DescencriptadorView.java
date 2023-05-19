package Encriptador.view;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DescencriptadorView {

    public static String pedirMensaje(){
        BufferedReader bufer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingresa el mensaje que quieras desencriptar");
        try {
            return bufer.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
