/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javafx.scene.layout.GridPane;

/**
 *
 * @author jjmoreno
 */
public class ConcentreseHilo extends Thread {

    private int fila;
    private int columna;
    private boolean validaTiempo;
    private String url;

    public ConcentreseHilo(String nombreHilo) {
        super(nombreHilo);
        validaTiempo = false;// no ha pasado los dos segundos
    }

    @Override
    public void run() {
        while (!this.validaTiempo) {
            try {
                Thread.sleep(2000);
                System.out.println("hilo");
            } catch (Exception e) {
                e.printStackTrace();
                validaTiempo = false;
            }
        }

    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public boolean isValidaTiempo() {
        return validaTiempo;
    }

    public void setValidaTiempo(boolean validaTiempo) {
        this.validaTiempo = validaTiempo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
