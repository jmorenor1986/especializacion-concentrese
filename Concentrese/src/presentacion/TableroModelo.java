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
    public void marcarPosicion(String fila,String columna){
       this.urlImagen= getLogica().getTablero()[Integer.parseInt(fila)][Integer.parseInt(columna)].getUrl();
    }

    public ConcentreseLogica getLogica() {
        if(logica==null){
            logica= new ConcentreseLogica(this.filasTablero, this.columnasTablero);
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
    
    
    
    
    
    
}
