/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author john
 */
public class ObjetoLista {
    private int clave;
    private String valor;
    private int filas;
    private int columnas;
    private int parejas;

    public ObjetoLista(int clave, String valor, int filas, int columnas, int parejas) {
        this.clave = clave;
        this.valor = valor;
        this.filas = filas;
        this.columnas = columnas;
        this.parejas = parejas;
    }

    public int getParejas() {
        return parejas;
    }

    public void setParejas(int parejas) {
        this.parejas = parejas;
    }

    

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
    
    

    @Override
    public String toString() {
        return this.valor;
    }
    
    
    
    
}
