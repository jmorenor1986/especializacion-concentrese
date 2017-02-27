/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.List;
import modelo.ObjetoImagen;

/**
 *
 * @author john
 */
public class ConcentreseLogica {

    private int filaActual;
    private int columnaActual;
    private final int filasTablero;
    private final int columnasTablero;
    private ObjetoImagen tablero[][];
    private ArrayList<Integer> listaNumeros;
    private ArrayList<Integer> lista;

    public ConcentreseLogica(int filasTablero, int columnasTablero) {
        this.filasTablero = filasTablero;
        this.columnasTablero = columnasTablero;
        tablero = new ObjetoImagen[filasTablero][columnasTablero];
    }

    public void llenaTablero() {
        llenaLista();
        int numeroImagenes = filasTablero * columnasTablero;
        int numeroAleatorioImagen = 0;
        int numeroAleatorioFilas = 0;
        int numeroAleatorioColumnas = 0;
        listaNumeros = new ArrayList();
        while (numeroImagenes != 0) {
            numeroAleatorioFilas = (int) (Math.random() * (filasTablero));
            numeroAleatorioColumnas = (int) (Math.random() * (columnasTablero));
            numeroAleatorioImagen = lista.get((int) (Math.random() * lista.size()));
            while (!validaListaNumeros(numeroAleatorioImagen)) {
                while (!validaCasilla(numeroAleatorioColumnas, numeroAleatorioFilas)) {
                    ObjetoImagen imagen = new ObjetoImagen();
                    imagen.setEstado("I");
                    imagen.setUrl("/presentacion/images/" + numeroAleatorioImagen + ".png");
                    imagen.setNombre("" + numeroAleatorioImagen);
                    imagen.setIdImagen(numeroAleatorioImagen);
                    tablero[numeroAleatorioFilas][numeroAleatorioColumnas] = imagen;
                    listaNumeros.add(numeroAleatorioImagen);
                    numeroImagenes--;
                    break;
                }
                break;
            }

        }
    }

    /**
     * falso si no esta verdadero si esta el numero
     *
     * @param numero
     * @return
     */
    public boolean validaListaNumeros(int numero) {
        boolean rta = false;
        int contador = 0;
        for (Integer listaNumero : listaNumeros) {
            if (listaNumero == numero) {
                contador += 1;
            }
            if (contador == 2) {
                lista.remove(new Integer(numero));
                rta = true;
                break;
            }
        }

        return rta;
    }

    /**
     * falso si esta vacia verdadero si esta llena
     *
     * @param fila
     * @param columna
     * @return
     */
    public boolean validaCasilla(int columna, int fila) {
        boolean rta = false;
        try {
            if (tablero[fila][columna].getIdImagen() != 0) {
                rta = true;
            }
        } catch (NullPointerException e) {
            rta = false;
        }

        return rta;
    }

    public void llenaLista() {
        if (lista == null) {
            lista = new ArrayList<>();
        }
        for (int i = 1; i <= (filasTablero * columnasTablero) / 2; i++) {
            lista.add(i);
        }
    }


    public int getFilaActual() {
        return filaActual;
    }

    public void setFilaActual(int filaActual) {
        this.filaActual = filaActual;
    }

    public int getColumnaActual() {
        return columnaActual;
    }

    public void setColumnaActual(int columnaActual) {
        this.columnaActual = columnaActual;
    }

    public ObjetoImagen[][] getTablero() {
        return tablero;
    }

    public void setTablero(ObjetoImagen[][] tablero) {
        this.tablero = tablero;
    }

}
