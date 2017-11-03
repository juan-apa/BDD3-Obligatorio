/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import logica.excepciones.ExceptionJuguete;
import logica.excepciones.ExceptionNinio;
import logica.excepciones.ExceptionPersistencia;
import logica.valueObjects.VOJuguete;
import logica.valueObjects.VONinio;

/**
 *
 * @author juan
 */
public interface IFachada extends Remote {

    public void nuevoNinio(VONinio von) throws ExceptionPersistencia, ExceptionNinio, RemoteException;

    public void nuevoJuguete(String desc, int cedN) throws ExceptionPersistencia, ExceptionNinio, RemoteException;

    public List<VONinio> listarNinios() throws ExceptionPersistencia, RemoteException;

    public List<VOJuguete> listarJuguetes(int cedN) throws ExceptionPersistencia, ExceptionNinio, RemoteException;

    public String darDescripcion(int cedN, int numJ) throws ExceptionPersistencia, ExceptionNinio, ExceptionJuguete, RemoteException;

    public void borrarNinioJuguetes(int cedN) throws ExceptionPersistencia, ExceptionNinio, RemoteException;

}
