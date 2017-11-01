/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafica.controladoras;

import grafica.ventanas.Ventana;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import logica.excepciones.ExceptionNinio;
import logica.excepciones.ExceptionPersistencia;

/**
 *
 * @author Juan Aparicio
 */
public class ControllerInsertJuguete extends Controladora{

    public ControllerInsertJuguete(JFrame ven) {
        super(ven);
    }
    
    public void insertJuguete(String cedulaNinio, String descripcion){
        try {
            int ced = Integer.parseInt(cedulaNinio);
            this.getFachada().nuevoJuguete(descripcion, ced);
            ((Ventana) this.getVentana()).mostrarMensaje("Juguete ingresado con éxito", Ventana.SUCCESS);
        } catch (NumberFormatException e) {
            ((Ventana) this.getVentana()).mostrarMensaje("El cámpo cédula tiene que ser numérico.", Ventana.WARNING);
        } catch (ExceptionPersistencia ex) {
            ((Ventana) this.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
        } catch (ExceptionNinio ex) {
            ((Ventana) this.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
        } catch (RemoteException ex) {
            ((Ventana) this.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
        }
    }
}
