/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Juguete;
import logica.excepciones.ExceptionPersistencia;
import logica.valueObjects.VOJuguete;
import persistencia.Conexion;
import persistencia.ConexionArchivo;
import persistencia.IConexion;

/**
 *
 * @author Juan Aparicio
 */
public class DAOJuguetesArchivo implements IDAOJuguetes, Serializable{
    private static final long serialVersionUID = 1L;
    private int cedulaNinio;
    private File folder;
    
    public DAOJuguetesArchivo(int cedulaNinio){
        this.cedulaNinio = cedulaNinio;
        this.folder = new File(System.getProperty("user.dir")+"/.resources/archivos");
    }
    
    @Override
    public void insback(Juguete juguete, IConexion ic) throws ExceptionPersistencia {
        ConexionArchivo con = (ConexionArchivo) ic;
        try{
            String archivo = this.folder.getPath() + "juguetes - " + this.cedulaNinio + ".txt";
            FileOutputStream Arch = new FileOutputStream(archivo);
            ObjectOutputStream flujo = new ObjectOutputStream(Arch);
            flujo.writeObject(juguete);
            flujo.close();
            Arch.close();
        } catch (FileNotFoundException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        } catch (IOException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        }
    }

    @Override
    public int largo(IConexion ic) throws ExceptionPersistencia {
        ConexionArchivo con = (ConexionArchivo) ic;
        int largo = 0;
        for(File archJuguete : this.folder.listFiles()){
            String nombreArch = archJuguete.getName();
            if(nombreArch.contains("jugutes - " + String.valueOf(this.cedulaNinio))){
                largo ++;
            }
        }
        return largo;
    }

    @Override
    public Juguete k_esimo(int numeroJuguete, IConexion ic) throws ExceptionPersistencia {
        ConexionArchivo con = (ConexionArchivo) ic;
//        ArrayList<File> archivosJuguetes = new ArrayList<>();
        Juguete kesimo = null;
        
        /*Recorro los archivos en el directorio, abro los jugutes, los cargo en un 
        juguete auxiliar y me fijo el número. Si el número es el deseado, lo devuelvo.*/
        for(File archJuguete : this.folder.listFiles()){
            String nombreArch = archJuguete.getName();
            if(nombreArch.contains("jugutes - " + String.valueOf(this.cedulaNinio))){
                Juguete aux = this.leerJugueteDeArchivo(archJuguete.getPath());
                if(aux.getNumero() == numeroJuguete){
                    kesimo = aux;
                }
            }
        }
        return kesimo;
    }

    @Override
    public List<VOJuguete> listarJuguetes(IConexion ic) throws ExceptionPersistencia {
        ArrayList<VOJuguete> listado = new ArrayList<>();
        for(File archJuguete : this.folder.listFiles()){
            String nombreArch = archJuguete.getName();
            if(nombreArch.contains("jugutes - " + String.valueOf(this.cedulaNinio))){
                Juguete aux = this.leerJugueteDeArchivo(archJuguete.getPath());
                listado.add(new VOJuguete(aux.getNumero(), aux.getDescripcion(), this.cedulaNinio));
            }
        }
        return listado;
    }

    @Override
    public void borrarJuguetes(IConexion ic) throws ExceptionPersistencia {
        for(File archJuguete : this.folder.listFiles()){
            String nombreArch = archJuguete.getName();
            if(nombreArch.contains("jugutes - " + String.valueOf(this.cedulaNinio))){
                archJuguete.delete();
            }
        }
    }
    
    private Juguete leerJugueteDeArchivo(String archivo) throws ExceptionPersistencia{
        Juguete ret = null;
        FileInputStream Arch = null;
        ObjectInputStream flujo = null;
        try {
            Arch = new FileInputStream(archivo);
            flujo = new ObjectInputStream(Arch);
            ret = (Juguete) flujo.readObject();
        } catch (FileNotFoundException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        } catch (IOException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        } catch (ClassNotFoundException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        } finally {
            if(flujo != null){
                try {
                    flujo.close();
                } catch (IOException ex) {
                    throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
                }
            }
            if(Arch != null){
                try {
                    Arch.close();
                } catch (IOException ex) {
                    throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
                }
            }
        }
        return ret;
    }

}
