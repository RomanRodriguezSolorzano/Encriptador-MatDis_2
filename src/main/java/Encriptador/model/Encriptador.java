package Encriptador.model;

public class Encriptador {
    private String mensaje, mensajeEncriptado;
    private int[][] matrizMensaje;
    private int[][] llave;

    public Encriptador() {

    }

    public Encriptador(String mensaje, String mensajeEncriptado, int[][] matrizMensaje, int[][] llave) {
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

    public int[][] getMatrizMensaje() {
        return matrizMensaje;
    }

    public void setMatrizMensaje(int[][] matrizMensaje) {
        this.matrizMensaje = matrizMensaje;
    }

    public int[][] getLlave() {
        return llave;
    }

    public void setLlave(int[][] llave) {
        this.llave = llave;
    }
}
