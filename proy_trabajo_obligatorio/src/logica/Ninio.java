/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import persistencia.daos.DAOJuguetes;

/**
 *
 * @author Juan Aparicio
 */
public class Ninio {

    private int cedula;
    private String nombre;
    private String apellido;
    private DAOJuguetes juguetes;

    public Ninio(int cedula, String nombre, String apellido, DAOJuguetes juguetes) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.juguetes = juguetes;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public DAOJuguetes getJuguetes() {
        return this.juguetes;
    }

    public void setDaoJuguetes(DAOJuguetes juguetes) {
        this.juguetes = juguetes;
    }
}
