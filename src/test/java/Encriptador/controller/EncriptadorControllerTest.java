package Encriptador.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncriptadorControllerTest {
    EncriptadorController encriptador = new EncriptadorController();

    @Test
    @DisplayName("Generar Matriz a partir del mensaje hola")
    void generarMatrizPrueba1() {
        String mensaje = "hola";
        int columnas = encriptador.obtenerColumnas(mensaje);
        double [][] matrizGenerada = encriptador.generarMatriz(mensaje, columnas);
        double [][] matrizResultado = {{8,1},
                                    {15,0},
                                    {12,0}};

        for(int fila=0;fila<3;fila++){
            for(int columna=0;columna<2;columna++){
                assertEquals(matrizResultado[fila][columna], matrizGenerada[fila][columna]);
            }
        }

    }

    @Test
    @DisplayName("Generar Matriz a partir del mensaje hola que tal amix")
    void generarMatrizPrueba2() {
        String mensaje = "hola que tal amix";
        int columnas = encriptador.obtenerColumnas(mensaje);
        double [][] matrizGenerada = encriptador.generarMatriz(mensaje, columnas);
        double [][] matrizResultado = {{8,1,21,20,0,9},
                                    {15,0,5,1,1,24},
                                    {12,17,0,12,13,0}};
        for(int fila=0;fila<3;fila++){
            for(int columna=0;columna<6;columna++){
                assertEquals(matrizResultado[fila][columna], matrizGenerada[fila][columna]);
            }
        }

    }

    @Test
    @DisplayName("Generar Llave Aleatoria")
    void generarLlave(){
        double [][] llaveAleatoria = encriptador.generarLlave();
        for(int fila=0;fila<3;fila++){
            for(int columna=0;columna<3;columna++){
                System.out.print(llaveAleatoria[fila][columna]+" ");
            }
            System.out.println("");
        }

    }

    @Test
    @DisplayName("Generar Llave Aleatoria")
    void encriptarMensaje(){
        EncriptadorController encriptar = new EncriptadorController();
        encriptar.encriptarMensaje();
    }



}