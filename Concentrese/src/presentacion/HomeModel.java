/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import modelo.ObjetoLista;

/**
 *
 * @author john
 */
public class HomeModel {
    private boolean seleccionNivel;
    private String mensajeNotificacion;

    public boolean verificaNotificacion(ObjetoLista opcion) {
        System.out.println(opcion.getClave() + "--" + opcion.getValor());
        if (opcion.getClave() != 0) {
            this.mensajeNotificacion = "OK";
            seleccionNivel = false;
        } else {
            this.mensajeNotificacion = "Por favor, seleccione un nivel";
            seleccionNivel = true;
        }
        return seleccionNivel;

    }

    public String getMensajeNotificacion() {
        return mensajeNotificacion;
    }

    public void setMensajeNotificacion(String mensajeNotificacion) {
        this.mensajeNotificacion = mensajeNotificacion;
    }

 

}
