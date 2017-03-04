/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import logica.ConcentreseLogica;

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
    private boolean validaHilo;
    private int fila;
    private int columna;
    private boolean pareja;

    public void marcarPosicion(String fila, String columna) {
        pareja = false;
        this.urlImagen = getLogica().getTablero()[Integer.parseInt(fila)][Integer.parseInt(columna)].getUrl();
        if (getLogica().llenarImagen(Integer.parseInt(fila), Integer.parseInt(columna))) {
            valida = true;
            pareja = getLogica().isPareja();
            if (pareja) {
                this.getLogica().setPrimeraImagen(null);
                this.getLogica().setSegundaImagen(null);
            }
        } else {
            valida = false;
            this.fila = getLogica().getPrimeraImagen().getFila();
            this.columna = getLogica().getPrimeraImagen().getColumna();
            //this.setUrlImagen("/presentacion/images/pregunta.png");
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

    public String validaTiempoImagenes() {
        String ruta = "";
        try {
            if (getLogica().getSegundaImagen() == null && this.pareja == false) {
                ruta = "/presentacion/images/pregunta.png";
                getLogica().setPrimeraImagen(null);
            } else {
                if (!pareja) {
                    if (getLogica().getPrimeraImagen().getUrl().equalsIgnoreCase(getLogica().getSegundaImagen().getUrl())) {
                        System.out.println("si son");
                        this.getLogica().setPrimeraImagen(null);
                        this.getLogica().setSegundaImagen(null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ruta;
    }

    public boolean validaEstados() {

        return getLogica().validarEstados();
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

    public boolean isPareja() {
        return pareja;
    }

    public void setPareja(boolean pareja) {
        this.pareja = pareja;
    }

    public void setValida(boolean valida) {
        this.valida = valida;
    }

    public boolean isValidaHilo() {
        return validaHilo;
    }

    public void setValidaHilo(boolean validaHilo) {
        this.validaHilo = validaHilo;
    }

}
