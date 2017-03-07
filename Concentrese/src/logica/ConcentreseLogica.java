/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import modelo.ObjetoImagen;

/**
 *
 * @author john
 */
public class ConcentreseLogica {

    private int filaActual;
    private int columnaActual;
    private int contadorParejas;
    private final int filasTablero;
    private final int columnasTablero;
    private final int parejasTablero;
    private ObjetoImagen tablero[][];
    private ArrayList<Integer> listaNumeros;
    private ArrayList<Integer> lista;
    private ObjetoImagen primeraImagen;
    private ObjetoImagen segundaImagen;
    private boolean pareja;
    private boolean winner;

    public ConcentreseLogica(int filasTablero, int columnasTablero, int parejasTablero) {
        this.filasTablero = filasTablero;
        this.columnasTablero = columnasTablero;
        this.parejasTablero = parejasTablero;
        tablero = new ObjetoImagen[filasTablero][columnasTablero];
    }

    public void llenaTablero() {
        llenaLista();
        int numeroImagenes = filasTablero * columnasTablero;
        int numeroAleatorioImagen = 0;
        int numeroAleatorioFilas = 0;
        int numeroAleatorioColumnas = 0;
        listaNumeros = new ArrayList();
        int contador = 0;
        while (numeroImagenes != 0) {
            contador++;
            numeroAleatorioFilas = (int) (Math.random() * (filasTablero));
            numeroAleatorioColumnas = (int) (Math.random() * (columnasTablero));
            numeroAleatorioImagen = lista.get((int) (Math.random() * lista.size()));
            while (!validaListaNumeros(numeroAleatorioImagen)) {
                while (!validaCasilla(numeroAleatorioColumnas, numeroAleatorioFilas)) {
                    ObjetoImagen imagen = new ObjetoImagen();
                    imagen.setEstado("I");
                    imagen.setUrl("/presentacion/images/" + numeroAleatorioImagen + ".png");
                    imagen.setNombre("" + numeroAleatorioImagen);
                    imagen.setIdImagen(contador);
                    imagen.setColumna(numeroAleatorioFilas);
                    imagen.setFila(numeroAleatorioColumnas);
                    tablero[numeroAleatorioFilas][numeroAleatorioColumnas] = imagen;
                    listaNumeros.add(numeroAleatorioImagen);
                    numeroImagenes--;
                    break;
                }
                break;
            }
        }
    }

    public boolean llenarImagen(int fila, int columna) {
        if (this.primeraImagen == null) {
            this.primeraImagen = this.tablero[fila][columna];
            //this.primeraImagen.setEstado("A");
            this.pareja=false;
        } else if (this.segundaImagen == null) {
            this.segundaImagen = this.tablero[fila][columna];
            //this.segundaImagen.setEstado("A");
            return validarEstados();
        }
        return true;
    }

    public boolean validarEstados() {
        if (this.primeraImagen.getIdImagen() == this.segundaImagen.getIdImagen()) {
            System.out.println("misma imagen seleccionada");
            this.segundaImagen = null;
            this.setPareja(false);
            return false;
        } else if (this.primeraImagen.getUrl().equals(this.segundaImagen.getUrl())) {
            this.contadorParejas++;
            if(this.parejasTablero == this.contadorParejas)
                this.setWinner(true);
            //this.segundaImagen.setEstado("A");
            this.setPareja(true);
            System.out.println("Ok");
            return true;
        } else {
            System.out.println("mal");
            this.setPareja(false);
            //this.segundaImagen.setEstado("A");
            //this.primeraImagen = null;
            //this.segundaImagen = null;
            return false;
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

    public ObjetoImagen getPrimeraImagen() {
        return primeraImagen;
    }

    public ObjetoImagen getSegundaImagen() {
        return segundaImagen;
    }

    public void setPrimeraImagen(ObjetoImagen primeraImagen) {
        this.primeraImagen = primeraImagen;
    }

    public void setSegundaImagen(ObjetoImagen segundaImagen) {
        this.segundaImagen = segundaImagen;
    }

    public boolean isPareja() {
        return pareja;
    }

    public void setPareja(boolean pareja) {
        this.pareja = pareja;
    }
    
    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
    

}
