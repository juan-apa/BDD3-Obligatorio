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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Ninio;
import logica.excepciones.ExceptionPersistencia;
import logica.valueObjects.VONinio;
import persistencia.ConexionArchivo;
import persistencia.IConexion;

/**
 *
 * @author Juan Aparicio
 */
public class DAONiniosArchivo implements IDAONinios, Serializable{
    
    private File folder;
    
    public DAONiniosArchivo(){
        this.folder = new File(System.getProperty("user.dir")+"/.resources/archivos");
    }

    @Override
    public boolean member(int cedulaNinio, IConexion ic) throws ExceptionPersistencia {
        ConexionArchivo con = (ConexionArchivo) ic;
        boolean esta = false;
        for (File archNinio : this.folder.listFiles()){
           String nombreArchivo = archNinio.getName();
           if(nombreArchivo.contains("ninios-"+String.valueOf(cedulaNinio)+".txt")){
               esta = true;
           }
       }
        
        return esta;
    }

    @Override
    public void insert(Ninio ninio, IConexion ic) throws ExceptionPersistencia {
        ConexionArchivo con = ((ConexionArchivo) ic);
        FileOutputStream Arch = null;
        ObjectOutputStream flujo = null;
        
        try {
            String archivo = this.folder.getPath()+"/ninios-" + ninio.getCedula() + ".txt";
            Arch = new FileOutputStream(archivo);
            flujo = new ObjectOutputStream(Arch);
            flujo.writeObject(ninio);
        } catch (FileNotFoundException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.INGRESAR_DATOS);
        } catch (IOException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.INGRESAR_DATOS);
        } finally {
            try {
                flujo.close();
            } catch (IOException ex){
                throw new ExceptionPersistencia(ExceptionPersistencia.INGRESAR_DATOS);
            }
            try {
                Arch.close();
            } catch (IOException ex) {
               throw new ExceptionPersistencia(ExceptionPersistencia.INGRESAR_DATOS);
            }
        }
    }

    @Override
    public Ninio find(int cedulaNinio, IConexion ic) throws ExceptionPersistencia {
        //
        ConexionArchivo con = (ConexionArchivo) ic;
        Ninio ret = null;
        FileInputStream Arch = null;
        ObjectInputStream flujo = null;

        //
        for (File archNinio : this.folder.listFiles()) {
            String nombreArchivo = archNinio.getName();
            if (nombreArchivo.contains("ninios-" + String.valueOf(cedulaNinio) + ".txt")) {
                try {
                    Arch = new FileInputStream(archNinio);
                    flujo = new ObjectInputStream(Arch);
                    ret = (Ninio) flujo.readObject();
                } catch (FileNotFoundException ex) {
                    throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
                } catch (IOException ex) {
                    throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
                } catch (ClassNotFoundException ex) {
                    throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
                }
            }
        }
        return ret;
    }
    
    @Override
    public void delete(int cedulaNinio, IConexion ic) throws ExceptionPersistencia {
       ConexionArchivo con = (ConexionArchivo) ic;
       for (File archNinio : this.folder.listFiles()){
           String nombreArchivo = archNinio.getName();
           if(nombreArchivo.contains("ninios-"+String.valueOf(cedulaNinio)+".txt")){
               archNinio.delete();
           }
       }
    }

    @Override
    public List<VONinio> listarNinios(IConexion ic) throws ExceptionPersistencia {
        //ConexionArchivo con = (ConexionArchivo) ic;
        ArrayList<VONinio> listado = new ArrayList<>();
        System.out.println("UNO");
        for (File archNinio: this.folder.listFiles()){
            String nombreArch = archNinio.getName();
            System.out.println("DOS");
            if(nombreArch.contains("ninios-")){
                Ninio n = leerNinioDeArchivo(archNinio.getPath());
                System.out.println("TRES");
                VONinio aux = new VONinio(n.getCedula(), n.getNombre(), n.getApellido());
                listado.add(aux); 
           }
        }
        return listado;
    }
    
    
    private Ninio leerNinioDeArchivo(String archivo) throws ExceptionPersistencia {
        Ninio ret = null;
        FileInputStream Arch = null;
        ObjectInputStream flujo = null;
        try {
            Arch = new FileInputStream(archivo);
            flujo = new ObjectInputStream(Arch);
            ret = (Ninio) flujo.readObject();
            System.out.println("DOS DOS");
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
