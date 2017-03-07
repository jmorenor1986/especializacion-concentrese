package presentacion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modelo.ObjetoLista;

/**
 *
 * @author john
 */
public class HomeController implements Initializable {

    @FXML
    private ChoiceBox listaObjeto;
    @FXML
    private Label labelNotificacion;
    @FXML
    private BorderPane panelNotificacion;

    /**
     * metodo que se ejecuta cuando se hace click en el boton jugar
     *
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        HomeModel hm;
        hm = new HomeModel();
        this.panelNotificacion.setVisible(hm.verificaNotificacion((ObjetoLista) listaObjeto.getSelectionModel().getSelectedItem()));
        labelNotificacion.setText(hm.getMensajeNotificacion());
        if ("OK".equalsIgnoreCase(labelNotificacion.getText())) {
            try {
                abreVentanaTablero((ObjetoLista) listaObjeto.getSelectionModel().getSelectedItem());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * metodo que es invocado cuando selecciono un nivel de dificultad
     *
     * @param event
     */
    private void abreVentanaTablero( ObjetoLista objetoSeleccionado) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TableroController.class.getResource("Tablero.fxml"));
        Parent root = loader.load();
        TableroController controler = loader.getController();
        controler.inicializaVariablesJuego(objetoSeleccionado);
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * metodo que carga los parametros de inicio
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rb = ResourceBundle.getBundle("presentacion.resources.configuracion");
        listaObjeto.getItems().add(new ObjetoLista(0, "SELECCIONE NIVEL", 0, 0, 0));
        listaObjeto.getItems().add(new ObjetoLista(1, "FACIL", Integer.parseInt(rb.getString("nivelFacilFilas")), Integer.parseInt(rb.getString("nivelFacilColumnas")), 10));
        listaObjeto.getItems().add(new ObjetoLista(2, "MEDIO", Integer.parseInt(rb.getString("nivelMedioFilas")), Integer.parseInt(rb.getString("nivelMedioColumnas")), 20));
        listaObjeto.getItems().add(new ObjetoLista(3, "DIFICIL", Integer.parseInt(rb.getString("nivelDificilFilas")), Integer.parseInt(rb.getString("nivelDificilColumnas")), 30));
        listaObjeto.getSelectionModel().selectFirst();

    }

}
