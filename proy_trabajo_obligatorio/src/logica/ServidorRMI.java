/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;
import logica.excepciones.ExceptionPersistencia;

/**
 *
 * @author Juan Aparicio
 */
public class ServidorRMI {

    public static void main(String[] args) {
        try {
            Properties p = new Properties();
            try {
                p.load(new FileReader(".resources/config.properties"));
            } catch (IOException ex) {
                throw new ExceptionPersistencia(ExceptionPersistencia.ABRIR_PROPERTIES);
            }
            String ip = p.getProperty("ip");
            String puerto = p.getProperty("puerto");
            LocateRegistry.createRegistry(Integer.parseInt(puerto));
            Fachada fachada = Fachada.getInstancia();
            System.out.println("Antes de rebind");
            Naming.rebind("//" + ip + ":" + puerto + "/fachada", fachada);
            System.out.println("Servidor corriendo...");
        } catch (ExceptionPersistencia ex) {
            System.err.println("Error al inicializar el serv. 1: " + ex.getMessage() + "; ");
            ex.printStackTrace();
        } catch (RemoteException ex) {
            System.err.println("Error al inicializar el serv. 2: " + ex.getMessage());
        } catch (MalformedURLException ex) {
            System.err.println("Error al inicializar el serv. 3");
        }
    }

}
