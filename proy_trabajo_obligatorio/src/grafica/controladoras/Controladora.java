/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica.controladoras;

import grafica.ventanas.Ventana;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import logica.IFachada;
import logica.excepciones.ExceptionPersistencia;
import logica.excepciones.ExceptionRMI;

/**
 *
 * @author Juan Aparicio
 */
public class Controladora {

    private IFachada f;
    private JFrame ven;
    private int puerto;
    private String ip;
    
    public Controladora(JFrame ven) {
        /*Cargo estos valores por defecto, luego los sobreescribo con los datos
        del archivo properties.*/
        this.puerto = 1099;
        this.ip = "localhost";
        try {
            /*Cargo el archivo properties y obtengo sus datos.*/
            Properties p = new Properties();
            try {
                p.load(new FileReader(".resources/config.properties"));
                this.puerto = Integer.parseInt(p.getProperty("puerto"));
                this.ip = p.getProperty("ip");
            } catch (FileNotFoundException ex) {
                ((Ventana) ven).mostrarMensaje(ExceptionPersistencia.obtenerMensaje(ExceptionPersistencia.ABRIR_PROPERTIES), Ventana.ERROR);
            } catch (IOException ex) {
                ((Ventana) ven).mostrarMensaje(ExceptionPersistencia.obtenerMensaje(ExceptionPersistencia.ABRIR_PROPERTIES), Ventana.ERROR);
            }
            this.ven = ven;
            /*Establezco la conexión RMI para obtener la fachada.*/
            this.f = (IFachada) Naming.lookup("//" + ip + ":" + puerto + "/fachada");
        } catch (NotBoundException ex) {
            ((Ventana) ven).mostrarMensaje(ExceptionRMI.obtenerMensaje(ExceptionRMI.ESTABLECER_CONEXION), Ventana.ERROR);
        } catch (MalformedURLException ex) {
            ((Ventana) ven).mostrarMensaje(ExceptionRMI.obtenerMensaje(ExceptionRMI.ESTABLECER_CONEXION), Ventana.ERROR);
        } catch (RemoteException ex) {
            ((Ventana) ven).mostrarMensaje(ExceptionRMI.obtenerMensaje(ExceptionRMI.ESTABLECER_CONEXION), Ventana.ERROR);
        }
    }

    public IFachada getFachada() {
        return f;
    }

    public void setFachada(IFachada f) {
        this.f = f;
    }

    public JFrame getVentana() {
        return ven;
    }

    public void setVentana(JFrame ven) {
        this.ven = ven;
    }

    public static boolean numerico(String s) {
        char[] str = s.toCharArray();
        boolean ret = true;
        int i = 0;
        while (i < str.length && ret == true) {
            switch (str[i]) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    break;
                default:
                    ret = false;
                    break;
            }
            i++;
        }
        return ret;
    }
}
