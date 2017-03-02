/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import presentacion.TableroController;

/**
 *
 * @author jjmoreno
 */
public class ConcentreseHilo extends Thread {

    private int fila;
    private int columna;
    private boolean validaTiempo;
    private TableroController controlador;
    private GridPane tablaJuegoHilo;

    public ConcentreseHilo(String nombreHilo) {
        super(nombreHilo);
        validaTiempo = false;// no ha pasado los dos segundos
    }

    @Override
    public void run() {
        while (!this.validaTiempo) {
            try {
                Thread.sleep(2000);
                validaTiempo = true;
                 tablaJuegoHilo.add(new ImageView(new Image("/presentacion/images/pregunta.png")), Integer.parseInt(""+columna), Integer.parseInt(""+fila));
                 controlador.setTablaJuego(tablaJuegoHilo);

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

    public TableroController getControlador() {
        return controlador;
    }

    public void setControlador(TableroController controlador) {
        this.controlador = controlador;
    }

    public GridPane getTablaJuegoHilo() {
        return tablaJuegoHilo;
    }

    public void setTablaJuegoHilo(GridPane tablaJuegoHilo) {
        this.tablaJuegoHilo = tablaJuegoHilo;
    }
    
    
    
    

}
