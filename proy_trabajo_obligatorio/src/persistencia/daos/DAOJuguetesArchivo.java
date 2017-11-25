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
import logica.Juguete;
import logica.excepciones.ExceptionPersistencia;
import logica.valueObjects.VOJuguete;
import persistencia.ConexionArchivo;
import persistencia.IConexion;

/**
 *
 * @author Juan Aparicio
 */
public class DAOJuguetesArchivo implements IDAOJuguetes, Serializable {

    private static final long serialVersionUID = 1L;
    private int cedulaNinio;
    private File folder;

    public DAOJuguetesArchivo(int cedulaNinio) {
        this.cedulaNinio = cedulaNinio;
        this.folder = new File(System.getProperty("user.dir") + "/.resources/archivos");
    }

    @Override
    public void insback(Juguete juguete, IConexion ic) throws ExceptionPersistencia {
        ConexionArchivo con = (ConexionArchivo) ic;

        ArrayList<Juguete> arr = new ArrayList<>();
        String archivo = this.folder.getPath() + "/juguetes-" + this.cedulaNinio + ".txt";
        try {
            String nombreArchivo = "juguetes-" + this.cedulaNinio + ".txt"; //nuevo
            if (this.existeArchivo(nombreArchivo)) {
                arr = this.leerJugueteDeArchivo(archivo);
            }
            arr.add(juguete);
            this.escribirJugueteArchivo(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int largo(IConexion ic) throws ExceptionPersistencia {

        ConexionArchivo con = (ConexionArchivo) ic;
        int largo = 0;

        String nombreArch = this.folder.getPath() + "/juguetes-" + this.cedulaNinio + ".txt";
        String nombreArchivo = "juguetes-" + this.cedulaNinio + ".txt";
        if (this.existeArchivo(nombreArchivo)) {
            ArrayList<Juguete> arr = this.leerJugueteDeArchivo(nombreArch);
            largo = arr.size();
        }
        return largo;
    }

    @Override
    public Juguete k_esimo(int numeroJuguete, IConexion ic) throws ExceptionPersistencia {
        ConexionArchivo con = (ConexionArchivo) ic;
        Juguete kesimo = null;

        /*Recorro los archivos en el directorio, abro los jugutes, los cargo en un 
        juguete auxiliar y me fijo el número. Si el número es el deseado, lo devuelvo.*/
        for (File archJuguete : this.folder.listFiles()) {
            String nombreArch = archJuguete.getName();
            if (nombreArch.contains("juguetes-" + String.valueOf(this.cedulaNinio))) {
                ArrayList<Juguete> aux = this.leerJugueteDeArchivo(archJuguete.getPath());
                kesimo = aux.get(numeroJuguete);
            }
        }
        return kesimo;
    }

    @Override
    public List<VOJuguete> listarJuguetes(IConexion ic) throws ExceptionPersistencia {
        ConexionArchivo con = (ConexionArchivo) ic;
        ArrayList<VOJuguete> ret = new ArrayList<>();

        for (File archJuguete : this.folder.listFiles()) {
            String nombreArch = archJuguete.getName();
            if (nombreArch.contains("juguetes-" + String.valueOf(this.cedulaNinio) + ".txt")) {
                ArrayList<Juguete> aux = this.leerJugueteDeArchivo(archJuguete.getPath());
                for (Juguete j : aux) {
                    ret.add(new VOJuguete(j.getNumero(), j.getDescripcion(), this.cedulaNinio));
                }
            }
        }
        return ret;
    }

    @Override
    public void borrarJuguetes(IConexion ic) throws ExceptionPersistencia {
        System.out.println("llegue");
        ConexionArchivo con = (ConexionArchivo) ic;
        for (File archJuguete : this.folder.listFiles()) {
            String nombreArch = archJuguete.getName();
            if (nombreArch.contains("juguetes-" + String.valueOf(this.cedulaNinio) + ".txt")) {
                archJuguete.delete();
            }
        }
    }

    private ArrayList<Juguete> leerJugueteDeArchivo(String archivo) throws ExceptionPersistencia {
        ArrayList<Juguete> listado = new ArrayList<>();
        FileInputStream Arch = null;
        ObjectInputStream flujo = null;
        try {
            Arch = new FileInputStream(archivo);
            flujo = new ObjectInputStream(Arch);
            listado = (ArrayList<Juguete>) flujo.readObject();
        } catch (FileNotFoundException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        } catch (IOException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        } catch (ClassNotFoundException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        } finally {
            if (flujo != null) {
                try {
                    flujo.close();
                } catch (IOException ex) {
                    throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
                }
            }
            if (Arch != null) {
                try {
                    Arch.close();
                } catch (IOException ex) {
                    throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
                }
            }
        }

        return listado;
    }

    private void escribirJugueteArchivo(ArrayList<Juguete> arr) throws ExceptionPersistencia {
        FileOutputStream Arch = null;
        ObjectOutputStream flujo = null;
        try {
            String archivo = this.folder.getPath() + "/juguetes-" + this.cedulaNinio + ".txt";
            Arch = new FileOutputStream(archivo);
            flujo = new ObjectOutputStream(Arch);
            flujo.writeObject(arr);
            
        } catch (FileNotFoundException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        } catch (IOException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        }
        finally{
            try {
                if(flujo != null)
                    flujo.close();
            } catch (IOException ex) {
                throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
            }
            try {
                if(Arch != null)
                    Arch.close();
            } catch (IOException ex) {
                throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
            }
        }

    }

    private boolean existeArchivo(String nombreArch) {
        boolean existe = false;
        for (File archJuguete : this.folder.listFiles()) {
            String aux = archJuguete.getName();
            if (aux.contains(nombreArch)) {
                existe = true;
            }
        }
        return existe;
    }

}
