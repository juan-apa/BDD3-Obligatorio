/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafica.controladoras;

import grafica.ventanas.Ventana;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import logica.IFachada;

/**
 *
 * @author Juan Aparicio
 */
public class Controladora {
    private IFachada f;
    private JFrame ven;

    public Controladora(JFrame ven) {
        try {
            this.ven = ven;
            String puerto = "1099";
            String ip = "localhost";
            this.f = (IFachada) Naming.lookup("//"+ip+":"+puerto+"/fachada");
        } catch (NotBoundException ex) {
            ((Ventana) ven).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
        } catch (MalformedURLException ex) {
            ((Ventana) ven).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
        } catch (RemoteException ex) {
            ((Ventana) ven).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
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
    
    
    public static boolean numerico(String s){
        char [] str = s.toCharArray();
        boolean ret = true;
        int i = 0;
        while(i < str.length && ret == true){
            switch(str[i]){
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
