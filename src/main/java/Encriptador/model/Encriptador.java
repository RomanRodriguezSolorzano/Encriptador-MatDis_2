package Encriptador.model;

public class Encriptador {
    private String mensaje, mensajeEncriptado;
    private double[][] matrizMensaje;
    private double[][] llave;

    private double [][] matrizCifrada;

    private double[][] unionMatrices;

    private int columnas;


    public Encriptador() {

    }

    public Encriptador(String mensaje, String mensajeEncriptado, double[][] matrizMensaje, double[][] llave) {
        this.mensaje = mensaje;
        this.mensajeEncriptado = mensajeEncriptado;
        this.matrizMensaje = matrizMensaje;
        this.llave = llave;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeEncriptado() {
        return mensajeEncriptado;
    }

    public void setMensajeEncriptado(String mensajeEncriptado) {
        this.mensajeEncriptado = mensajeEncriptado;
    }

    public double[][] getMatrizMensaje() {
        return matrizMensaje;
    }

    public void setMatrizMensaje(double[][] matrizMensaje) {
        this.matrizMensaje = matrizMensaje;
    }

    public double[][] getLlave() {
        return llave;
    }

    public void setLlave(double[][] llave) {
        this.llave = llave;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public double[][] getUnionMatrices() {
        return unionMatrices;
    }

    public void setUnionMatrices(double[][] unionMatrices) {
        this.unionMatrices = unionMatrices;
    }

    public double[][] getMatrizCifrada() {
        return matrizCifrada;
    }

    public void setMatrizCifrada(double[][] matrizCifrada) {
        this.matrizCifrada = matrizCifrada;
    }


}
