/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import logica.valueObjects.VONinio;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.AccesoBD;
import logica.excepciones.ExceptionJuguete;
import logica.excepciones.ExceptionNinio;
import logica.excepciones.ExceptionPersistencia;
import persistencia.IConexion;
import persistencia.IPoolConexiones;
import persistencia.PoolConexiones;
import logica.valueObjects.VOJuguete;
import persistencia.daos.DAOJuguetes;
import persistencia.daos.DAONinios;

/**
 *
 * @author Juan Aparicio
 */
public class Fachada  extends UnicastRemoteObject implements IFachada{
    private static final long serialVersionUID = 1L;

    private static Fachada f = null;
    private DAONinios ninios;
    
    public static Fachada getInstancia() throws ExceptionPersistencia, RemoteException{
        if(f == null){
            Fachada.f = new Fachada();
        }
        return Fachada.f;
    }

    private Fachada() throws ExceptionPersistencia, RemoteException {
        this.ninios = new DAONinios();
    }

    /*Ingresa un nuevo niño al sistema, chequeando que no existiera. */
    public void nuevoNinio(VONinio von) throws ExceptionPersistencia, ExceptionNinio, RemoteException {
        if(! this.ninios.member(von.getCedula())){
            Ninio insert = new Ninio(von.getCedula(), von.getNombre(), von.getApellido(), new DAOJuguetes(von.getCedula()));
            this.ninios.insert(insert);
        }
        else{
            throw new ExceptionNinio(ExceptionNinio.EXISTE_NINIO);
        }
    }

    /*Ingresa un nuevo juguete al sistema, chequeando que el niño que le 
      corresponde esté registrado. El programa asignará automáticamente al nuevo
      juguete el número siguiente al del último juguete que ya tenía el niño. 
      Por ejemplo, si tenía 5 juguetes, asignará el nº 6. */
    public void nuevoJuguete(String desc, int cedN) throws ExceptionPersistencia, ExceptionNinio, RemoteException {
        if(this.ninios.member(cedN)){
            System.err.println("antes dj");
            DAOJuguetes dj = this.ninios.find(cedN).getJuguetes(); 
            System.err.println("despues dj");
            dj.insback(new Juguete(dj.largo(), desc));
            System.err.println("despues insback");
        }
        else{
            throw new ExceptionNinio(ExceptionNinio.NO_EXISTE_NINIO);
        }
        
    }

    /*Devuelve un listado de todos los niños registrados, ordenado por cédula. */
    public List<VONinio> listarNinios() throws ExceptionPersistencia, RemoteException {
        return this.ninios.listarNinios();
    }

    /*Devuelve un listado de todos los juguetes de un niño determinado, 
      (chequeando que dicho niño esté registrado) ordenado por número de juguete*/
    public List<VOJuguete> listarJuguetes(int cedN) throws ExceptionPersistencia, ExceptionNinio, RemoteException {
        List<VOJuguete> ret = new ArrayList<>();
        if(this.ninios.member(cedN)){
            ret = this.ninios.find(cedN).getJuguetes().listarJuguetes();
        }
        else{
            throw new ExceptionNinio(ExceptionNinio.NO_EXISTE_NINIO);
    }
        return ret;
    }

    /*Devuelve la descripción de un juguete, dados su número y la cédula
      del niño que le corresponde (chequeando que el niño exista y tenga un 
      juguete con ese número). */
    public String darDescripcion(int cedN, int numJ) throws ExceptionPersistencia, ExceptionNinio, ExceptionJuguete, RemoteException {
        String descripcion = new String();
        if(this.ninios.member(cedN)){
            if(this.ninios.find(cedN).getJuguetes().largo() > numJ && numJ >= 0)  
                descripcion = this.ninios.find(cedN).getJuguetes().k_esimo(numJ).getDescripcion();
            else
                throw new ExceptionJuguete(ExceptionJuguete.NO_EXISTE_JUGUETE);
        }
        else{
            throw new ExceptionNinio(ExceptionNinio.NO_EXISTE_NINIO);
        }
        return descripcion;
    }

    /*Elimina del sistema al niño con la cédula ingresada, y también elimina a 
      todos sus juguetes, chequeando que el niño esté registrado. */
    public void borrarNinioJuguetes(int cedN) throws ExceptionPersistencia, ExceptionNinio, RemoteException {
        if(this.ninios.member(cedN))
            this.ninios.delete(cedN);
        else
            throw new ExceptionNinio(ExceptionNinio.NO_EXISTE_NINIO);
    }

}
