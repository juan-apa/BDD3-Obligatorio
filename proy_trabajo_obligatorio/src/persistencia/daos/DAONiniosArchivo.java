/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia.daos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
public class DAONiniosArchivo implements IDAONinios{
    
    private File folder;
    
    public DAONiniosArchivo(){
        /* + "/.resources"*/
        this.folder = new File(System.getProperty("user.dir"));
    }

    @Override
    public boolean member(int cedulaNinio, IConexion ic) throws ExceptionPersistencia {
        return false;
    }

    @Override
    public void insert(Ninio ninio, IConexion ic) throws ExceptionPersistencia {
        ConexionArchivo con = ((ConexionArchivo) ic);
        FileOutputStream Arch = null;
        ObjectOutputStream flujo = null;
        
        try {
            String archivo = System.getProperty("user.dir")+"/.resources/ninios-" + ninio.getCedula() + ".txt";
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int cedulaNinio, IConexion ic) throws ExceptionPersistencia {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VONinio> listarNinios(IConexion ic) throws ExceptionPersistencia {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
