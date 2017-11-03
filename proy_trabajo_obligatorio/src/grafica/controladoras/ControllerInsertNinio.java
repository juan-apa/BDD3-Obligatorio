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
import logica.valueObjects.VONinio;

/**
 *
 * @author Juan Aparicio
 */
public class ControllerInsertNinio extends Controladora {

    public ControllerInsertNinio(JFrame ven) {
        super(ven);
    }

    public void insertNinio(String cedula, String nombre, String apellido) {
        int ced = 0;
        try {
            ced = Integer.parseInt(cedula);
            VONinio von = new VONinio(ced, nombre, apellido);

            if (Controladora.numerico(String.valueOf(von.getCedula()))) {
                try {
                    this.getFachada().nuevoNinio(von);
                    ((Ventana) this.getVentana()).mostrarMensaje("Niño ingresado con éxito.", Ventana.SUCCESS);
                } catch (ExceptionPersistencia ex) {
                    ((Ventana) this.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
                } catch (ExceptionNinio ex) {
                    ((Ventana) this.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
                } catch (RemoteException ex) {
                    Logger.getLogger(ControllerInsertNinio.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                ((Ventana) this.getVentana()).mostrarMensaje("El campo cédula no tiene formato numérico.", 0);
            }
        } catch (NumberFormatException ex) {
            ((Ventana) this.getVentana()).mostrarMensaje("El campo cédula solo puede contener números", Ventana.WARNING);
        }
    }
}
