/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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
    private GridPane tablaJuego;

    @FXML
    public void verificaPosicion(MouseEvent event) {
        Node source = (Node) event.getPickResult().getIntersectedNode();
        ImageView img = (ImageView) source;
        String[] posiciones = img.getId().split("-");
        getModelo().marcarPosicion(posiciones[0], posiciones[1]);
        tablaJuego.add(new ImageView(new Image(getModelo().getUrlImagen())), Integer.parseInt(posiciones[0]), Integer.parseInt(posiciones[1]));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
                tablaJuego.autosize();
                if (this.objetoSeleccionado.getValor().equalsIgnoreCase("FACIL")) {
                    tablaJuego.setLayoutX(300.0);
                } else if (this.objetoSeleccionado.getValor().equalsIgnoreCase("MEDIO")) {
                    tablaJuego.setLayoutX(200);
                } else {
                    tablaJuego.setLayoutX(50);
                }
                tablaJuego.setLayoutY(127.0);
                image.setId(i + "-" + j);
                tablaJuego.add(image, i, j);

            }
        }
        getModelo().setColumnasTablero(this.objetoSeleccionado.getColumnas());
        getModelo().setFilasTablero(this.objetoSeleccionado.getFilas());
        getModelo().getLogica().llenaTablero();

    }

    public TableroModelo getModelo() {
        if (modelo == null) {
            modelo = new TableroModelo();
        }
        return modelo;
    }

}
