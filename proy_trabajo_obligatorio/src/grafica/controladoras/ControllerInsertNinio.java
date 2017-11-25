/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica.controladoras;

import grafica.ventanas.Ventana;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import logica.IFachada;
import logica.excepciones.ExceptionNinio;
import logica.excepciones.ExceptionPersistencia;
import logica.excepciones.ExceptionRMI;
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
                    IFachada f = super.getFachada();
                    if(f != null){
                        f.nuevoNinio(von);
                        ((Ventana) this.getVentana()).mostrarMensaje("Niño ingresado con éxito.", Ventana.SUCCESS);
                    }else{
                        throw new RemoteException();
                    }
                } catch (ExceptionPersistencia ex) {
                    ((Ventana) this.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
                } catch (ExceptionNinio ex) {
                    ((Ventana) this.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
                } catch (RemoteException ex) {
                    ((Ventana) this.getVentana()).mostrarMensaje(ExceptionRMI.obtenerMensaje(ExceptionRMI.ESTABLECER_CONEXION), Ventana.ERROR);
                } 
            } else {
                ((Ventana) this.getVentana()).mostrarMensaje("El campo cédula no tiene formato numérico.", 0);
            }
        } catch (NumberFormatException ex) {
            ((Ventana) this.getVentana()).mostrarMensaje("El campo cédula solo puede contener números", Ventana.WARNING);
        }
    }
}
