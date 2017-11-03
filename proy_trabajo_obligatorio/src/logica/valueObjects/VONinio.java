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
public class VONinio extends VObject implements Serializable {

    private static final long serialVersionUID = 1L;
    private int cedula;
    private String nombre;
    private String apellido;

    public VONinio(int cedula, String nombre, String apellido) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    @Override
    public String[] toArray() {
        String[] ret = {
            String.valueOf(this.cedula),
            this.nombre,
            this.apellido
        };
        return ret;
    }
}
