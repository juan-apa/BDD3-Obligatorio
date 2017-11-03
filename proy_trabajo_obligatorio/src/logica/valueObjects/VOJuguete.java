/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.valueObjects;

import java.io.Serializable;

/**
 *
 * @author Juan Aparicio
 */
public class VOJuguete extends VObject implements Serializable {

    private static final long serialVersionUID = 1L;
    private int numero;
    private String descripcion;
    private int cedulaNinio;

    public VOJuguete(int numero, String descripcion, int cedulaNinio) {
        this.numero = numero;
        this.descripcion = descripcion;
        this.cedulaNinio = cedulaNinio;
    }

    public int getNumero() {
        return numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCedulaNinio() {
        return cedulaNinio;
    }

    @Override
    public String[] toArray() {
        String[] ret = {
            String.valueOf(this.numero),
            this.descripcion,
            String.valueOf(this.cedulaNinio)
        };
        return ret;
    }
}
