/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import logica.ConcentreseHilo;
import modelo.ObjetoLista;

/**
 * FXML Controller class
 *
 * @author john
 */
public class TableroController implements Initializable {

    private ObjetoLista objetoSeleccionado;
    private TableroModelo modelo;
    @FXML
    private Label nivel;
    @FXML
    private Label lbGanador;
    @FXML
    private Label lbCronometro;
    @FXML
    public GridPane tablaJuego;

    private Timer timerImagen;

    private Timer timerImagenes;

    private TimerTask timerTask;

    private TimerTask tareaCronometro;

    private Timer timerCronometro;

    private boolean validaTiempo, validaCronometro;

    private ImageView imagenAnadir;

    private int segundos, minutos;
   
    private String min="", seg="";

    @FXML
    public void verificaPosicion(MouseEvent event) throws InterruptedException {
        if(!getModelo().isOpen())
        {
            Node source = (Node) event.getPickResult().getIntersectedNode();
            ImageView img = (ImageView) source;
            String[] posiciones = img.getId().split("-");
            getModelo().marcarPosicion(posiciones[0], posiciones[1]);
            ImageView imagenAdd = new ImageView();
            imagenAdd.setImage(new Image(getModelo().getUrlImagen()));
            imagenAdd.setId(img.getId());
            if (getModelo().isPareja()) {
                if (getModelo().isWinner()) {
                    validaCronometro = false;
                    lbGanador.setText("*************Felicitaciones eres el ganador. " + lbCronometro.getText() + "***********");
                }
                getModelo().setValida(false);
            }

            if (getModelo().isValida()) {
                tablaJuego.add(imagenAdd, Integer.parseInt(posiciones[1]), Integer.parseInt(posiciones[0]));
                this.validaTiempo = true;
                validarTiempoImagen(Integer.parseInt(posiciones[1]), Integer.parseInt(posiciones[0]));

            } else {
                this.validaTiempo = false;
                ImageView imagenAdd2 = new ImageView();
                imagenAdd2.setImage(new Image(getModelo().getUrlImagen()));
                imagenAdd2.setId(getModelo().getColumna() + "-" + getModelo().getFila());
                tablaJuego.add(imagenAdd, Integer.parseInt(posiciones[1]), Integer.parseInt(posiciones[0]));
                validarTiempoImagenes(getModelo().getFila(), getModelo().getColumna(), Integer.parseInt(posiciones[1]), Integer.parseInt(posiciones[0]));
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imagenAnadir = new ImageView();
        timerImagen = new Timer();
        timerImagenes = new Timer();
        validaCronometro = true;
        inicializaCronometro();
        timerCronometro = new Timer();
        timerCronometro.schedule(tareaCronometro, 1000, 1000);
    }

    /**
     * metodo que trae el nivel y datos del juego seleccionado
     *
     * @param objeto
     */
    public void inicializaVariablesJuego(ObjetoLista objeto) {
        this.objetoSeleccionado = objeto;
        this.nivel.setText(objetoSeleccionado.getValor());
        for (int i = 0; i < objetoSeleccionado.getFilas(); i++) {
            for (int j = 0; j < objetoSeleccionado.getColumnas(); j++) {
                ImageView image = new ImageView(new Image("/presentacion/images/pregunta.png"));
                if (this.objetoSeleccionado.getValor().equalsIgnoreCase("FACIL")) {
                    tablaJuego.setLayoutX(300.0);
                } else if (this.objetoSeleccionado.getValor().equalsIgnoreCase("MEDIO")) {
                    tablaJuego.setLayoutX(200);
                } else {
                    tablaJuego.setLayoutX(50);
                }
                tablaJuego.setLayoutY(127.0);
                image.setId(i + "-" + j);
                tablaJuego.add(image, j, i);

            }
        }
        getModelo().setColumnasTablero(this.objetoSeleccionado.getColumnas());
        getModelo().setFilasTablero(this.objetoSeleccionado.getFilas());
        getModelo().setParejasTablero(this.objetoSeleccionado.getParejas());
        getModelo().getLogica().llenaTablero();

    }

    public TableroModelo getModelo() {
        if (modelo == null) {
            modelo = new TableroModelo();
        }
        return modelo;
    }

    public void validarTiempoImagen(int filaImagen, int columnaImagen) {
        timerTask = new TimerTask() {

            @Override
            @SuppressWarnings("empty-statement")
            public void run() {
                if (validaTiempo) {
                    Platform.runLater(() -> {
                        String rutaImagen = getModelo().validaTiempoImagenes();
                        if (!"".equalsIgnoreCase(rutaImagen)) {
                            imagenAnadir = new ImageView();
                            imagenAnadir.setId(columnaImagen + "-" + filaImagen);
                            imagenAnadir.setImage(new Image(rutaImagen));
                            tablaJuego.add(imagenAnadir, filaImagen, columnaImagen);

                        }
                    });
                } else {
                    timerImagen.cancel();
                    timerImagen.purge();
                }
            }
        };
        timerImagen = new Timer();
        timerImagen.schedule(timerTask, 2000);
    }

    public void validarTiempoImagenes(int fila, int columna, int fila2, int columna2) {
        timerTask = new TimerTask() {
            @Override
            @SuppressWarnings("empty-statement")
            public void run() {
                if (!validaTiempo) {
                    Platform.runLater(() -> {
                        String rutaImagen = getModelo().validaTiempoImagenes();
                        if (!"".equalsIgnoreCase(rutaImagen)) {
                            imagenAnadir = new ImageView();
                            imagenAnadir.setId(columna + "-" + fila);
                            imagenAnadir.setImage(new Image(rutaImagen));
                            tablaJuego.add(imagenAnadir, fila, columna);
                            imagenAnadir = new ImageView();
                            imagenAnadir.setId(columna2 + "-" + fila2);
                            imagenAnadir.setImage(new Image(rutaImagen));
                            tablaJuego.add(imagenAnadir, fila2, columna2);
                        }
                    });
                } else {
                    timerImagenes.cancel();
                    timerImagenes.purge();
                }
            }
        };
        timerImagenes = new Timer();
        timerImagenes.schedule(timerTask, 2000);
    }
 
    public void inicializaCronometro(){
        tareaCronometro = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if(validaCronometro)
                    {
                        segundos++;
                        if( segundos == 59 )
                        {
                            segundos = 0;
                            minutos++;
                        }
                        if( minutos < 10 ) min = "0" + minutos;
                        else min = Integer.toString(minutos);
                        if( segundos < 10 ) seg = "0" + segundos;
                        else seg = Integer.toString(segundos);
                        lbCronometro.setText("Tiempo: "+min+":"+seg);
                    }
                });               
            }
        };
    }
}
