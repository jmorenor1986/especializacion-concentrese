/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.util.LinkedList;
import java.util.List;
import logica.ConcentreseLogica;
import modelo.ObjetoImagen;

/**
 *
 * @author john
 */
public class TableroModelo {

    private int filasTablero;
    private int columnasTablero;
    private ConcentreseLogica logica;
    private String urlImagen;
    private boolean valida;
    private int fila;
    private int columna;

    public void marcarPosicion(String fila, String columna) {
        this.urlImagen = getLogica().getTablero()[Integer.parseInt(fila)][Integer.parseInt(columna)].getUrl();
        if (getLogica().llenarImagen(Integer.parseInt(fila), Integer.parseInt(columna))) {
            valida = true;
        } else {
            valida = false;
            this.fila = getLogica().getPrimeraImagen().getFila();
            this.columna = getLogica().getPrimeraImagen().getColumna();
            this.setUrlImagen("/presentacion/images/pregunta.png");
            this.getLogica().setPrimeraImagen(null);
            this.getLogica().setSegundaImagen(null);

        }

    }

    public ConcentreseLogica getLogica() {
        if (logica == null) {
            logica = new ConcentreseLogica(this.filasTablero, this.columnasTablero);
        }
        return logica;
    }

    public int getFilasTablero() {
        return filasTablero;
    }

    public void setFilasTablero(int filasTablero) {
        this.filasTablero = filasTablero;
    }

    public int getColumnasTablero() {
        return columnasTablero;
    }

    public void setColumnasTablero(int columnasTablero) {
        this.columnasTablero = columnasTablero;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public boolean isValida() {
        return valida;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

}
