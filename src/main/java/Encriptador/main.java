package Encriptador;

import Encriptador.controller.DesencriptadorController;
import Encriptador.controller.EncriptadorController;

import javax.swing.*;

public class main {
    public static void main(String [] args){
        EncriptadorController encriptar = new EncriptadorController();
        DesencriptadorController desencriptar = new DesencriptadorController();
        String[] options = {"Encriptar", "Descencriptar", "Salir"};
        int x;
        do {
            x = JOptionPane.showOptionDialog(null, "Elige la opcion que quiera realizar", "Menu de selección", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[2]);
            switch (x){
                case 0:
                    encriptar.encriptarMensaje();
                    break;
                case 1:
                    desencriptar.desencriptarMatriz();
                    break;
            }
        }while(x!=2);


    }

}
