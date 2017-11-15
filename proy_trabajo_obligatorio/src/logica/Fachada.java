/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import logica.valueObjects.VONinio;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.excepciones.ExceptionFabrica;
import logica.excepciones.ExceptionJuguete;
import logica.excepciones.ExceptionNinio;
import logica.excepciones.ExceptionPersistencia;
import persistencia.IConexion;
import persistencia.IPoolConexiones;
import persistencia.PoolConexiones;
import logica.valueObjects.VOJuguete;
import persistencia.FabricaAbstracta;
import persistencia.daos.DAOJuguetes;
import persistencia.daos.DAONinios;
import persistencia.daos.IDAONinios;

/**
 *
 * @author Juan Aparicio
 */
public class Fachada extends UnicastRemoteObject implements IFachada {

    private static final long serialVersionUID = 1L;

    private static Fachada f = null;
    private FabricaAbstracta fabrica;
    private IDAONinios ninios;
    private IPoolConexiones ipc = null;

    public static Fachada getInstancia() throws ExceptionPersistencia, RemoteException, ExceptionFabrica {
        if (f == null) {
            Fachada.f = new Fachada();
        }
        return Fachada.f;
    }

    private Fachada() throws ExceptionPersistencia, RemoteException, ExceptionFabrica {
        try {
            Properties p = new Properties();
            p.load(new FileReader(".resources/config.properties"));
            this.fabrica = (FabricaAbstracta) Class.forName(p.getProperty("fabrica")).newInstance();
            this.ninios = fabrica.crearIDAONinios();
        } catch (FileNotFoundException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.ABRIR_PROPERTIES);
        } catch (IOException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.ABRIR_PROPERTIES);
        } catch (ClassNotFoundException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_PROPERTY);
        } catch (InstantiationException ex) {
            throw new ExceptionFabrica(ExceptionFabrica.ERROR_CREAR_FABRICA);
        } catch (IllegalAccessException ex) {
            throw new ExceptionFabrica(ExceptionFabrica.ERROR_CREAR_FABRICA);
        }
        
        
        
        this.ninios = new DAONinios();
        this.ipc = new PoolConexiones();
    }

    /*Ingresa un nuevo niño al sistema, chequeando que no existiera. */
    public void nuevoNinio(VONinio von) throws ExceptionPersistencia, ExceptionNinio, RemoteException {
        IConexion ic = this.ipc.obtenerConexion(true);
        try {
            if(! this.ninios.member(von.getCedula(), ic)){
                Ninio insert = new Ninio(von.getCedula(), von.getNombre(), von.getApellido(), this.fabrica.crearIDAOJuguetes(von.getCedula()));
                this.ninios.insert(insert, ic);
            }
            else{
                throw new ExceptionNinio(ExceptionNinio.EXISTE_NINIO);
            }
            this.ipc.liberarConexion(ic, true);
        } catch (Exception e) {
            this.ipc.liberarConexion(ic, false);
            throw e;
        }
    }

    /*Ingresa un nuevo juguete al sistema, chequeando que el niño que le 
      corresponde esté registrado. El programa asignará automáticamente al nuevo
      juguete el número siguiente al del último juguete que ya tenía el niño. 
      Por ejemplo, si tenía 5 juguetes, asignará el nº 6. */
    public void nuevoJuguete(String desc, int cedN) throws ExceptionPersistencia, ExceptionNinio, RemoteException {
        IConexion ic = this.ipc.obtenerConexion(true);
        try {
            if(this.ninios.member(cedN, ic)){
                Ninio n = this.ninios.find(cedN, ic);
                n.insertJuguete(new Juguete(n.cantidadJuguetes(ic), desc), ic);
            }
            else {
                throw new ExceptionNinio(ExceptionNinio.NO_EXISTE_NINIO);
            }
            this.ipc.liberarConexion(ic, true);
        } catch (Exception e) {
            this.ipc.liberarConexion(ic, false);
            throw e;
        }
        
    }

    /*Devuelve un listado de todos los niños registrados, ordenado por cédula. */
    public List<VONinio> listarNinios() throws ExceptionPersistencia, RemoteException {
        IConexion ic = this.ipc.obtenerConexion(false);
        List<VONinio> ret = null;
        try {
            ret = this.ninios.listarNinios(ic);
            this.ipc.liberarConexion(ic, true);
        } catch (Exception e) {
            this.ipc.liberarConexion(ic, false);
            throw e;
        }
        return ret;
    }

    /*Devuelve un listado de todos los juguetes de un niño determinado, 
      (chequeando que dicho niño esté registrado) ordenado por número de juguete*/
    public List<VOJuguete> listarJuguetes(int cedN) throws ExceptionPersistencia, ExceptionNinio, RemoteException {
        IConexion ic = this.ipc.obtenerConexion(false);
        List<VOJuguete> ret = null;
        try {
            if(this.ninios.member(cedN, ic)){
                ret = this.ninios.find(cedN, ic).listarJuguetes(ic);
            }
            else{
                throw new ExceptionNinio(ExceptionNinio.NO_EXISTE_NINIO);
            }
            this.ipc.liberarConexion(ic, true);
        } catch (Exception e) {
            this.ipc.liberarConexion(ic, false);
            throw e;
        }
        return ret;
    }

    /*Devuelve la descripción de un juguete, dados su número y la cédula
      del niño que le corresponde (chequeando que el niño exista y tenga un 
      juguete con ese número). */
    public String darDescripcion(int cedN, int numJ) throws ExceptionPersistencia, ExceptionNinio, ExceptionJuguete, RemoteException {
        IConexion ic = this.ipc.obtenerConexion(false);
        String ret = new String();
        try {
            if(this.ninios.member(cedN, ic)){
                Ninio n = this.ninios.find(cedN, ic);
                if(n.cantidadJuguetes(ic) > numJ){
                    ret = n.obtenerJuguete(numJ, ic).getDescripcion();
                }
                else {
                    throw new ExceptionJuguete(ExceptionJuguete.NO_EXISTE_JUGUETE);
                }
            }
            else {
                throw new ExceptionNinio(ExceptionNinio.NO_EXISTE_NINIO);
            }
            this.ipc.liberarConexion(ic, true);
        } catch (Exception e) {
            this.ipc.liberarConexion(ic, false);
            throw e;
        }
        return ret;
    }

    /*Elimina del sistema al niño con la cédula ingresada, y también elimina a 
      todos sus juguetes, chequeando que el niño esté registrado. */
    public void borrarNinioJuguetes(int cedN) throws ExceptionPersistencia, ExceptionNinio, RemoteException {
        IConexion ic = this.ipc.obtenerConexion(true);
        try {
            if(this.ninios.member(cedN, ic)){
                this.ninios.delete(cedN, ic);
            } else {
                throw new ExceptionNinio(ExceptionNinio.NO_EXISTE_NINIO);
            }
            this.ipc.liberarConexion(ic, true);
        } catch (Exception e) {
            this.ipc.liberarConexion(ic, false);
            throw e;
        }
    }

}
