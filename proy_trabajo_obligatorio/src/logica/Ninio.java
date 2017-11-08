/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import logica.excepciones.ExceptionPersistencia;
import logica.valueObjects.VOJuguete;
import persistencia.Conexion;
import persistencia.IConexion;
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
    
    public boolean tieneJuguete(int numJ, IConexion ic) throws ExceptionPersistencia{
        boolean ret = false;
        if(this.juguetes.largo(ic) > numJ){
            ret = true;
        }
        return ret;
    }
    
    public void insertJuguete(Juguete j, IConexion ic) throws ExceptionPersistencia{
        this.juguetes.insback(j, ic);
    }
    
    public Juguete obtenerJuguete(int numJ, IConexion ic) throws ExceptionPersistencia{
        return this.juguetes.k_esimo(numJ, ic);
    }
    
    public List<VOJuguete> listarJuguetes(IConexion ic) throws ExceptionPersistencia{
        return this.juguetes.listarJuguetes(ic);
    }
    
    public void borrarJuguetes(IConexion ic) throws ExceptionPersistencia{
        this.juguetes.borrarJuguetes(ic);
    }
    
    public int cantidadJuguetes(IConexion ic) throws ExceptionPersistencia{
        return this.juguetes.largo(ic);
    }
}
