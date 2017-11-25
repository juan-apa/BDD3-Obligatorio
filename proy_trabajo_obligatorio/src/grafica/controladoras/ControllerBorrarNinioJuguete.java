/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica.controladoras;

import grafica.ventanas.Ventana;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import logica.excepciones.ExceptionNinio;
import logica.excepciones.ExceptionPersistencia;
import logica.excepciones.ExceptionRMI;

/**
 *
 * @author Juan Aparicio
 */
public class ControllerBorrarNinioJuguete extends Controladora {

    public ControllerBorrarNinioJuguete(JFrame ven) {
        super(ven);
    }

    public void borrarNiniosYJuguetes(String cedulaNinio) {
        try {
            int cedula = Integer.parseInt(cedulaNinio);
            super.getFachada().borrarNinioJuguetes(cedula);
            ((Ventana) super.getVentana()).mostrarMensaje("El niño y sus juguetes fueron borrados con éxito.", Ventana.SUCCESS);
        } catch (ExceptionPersistencia ex) {
            ((Ventana) super.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
        } catch (ExceptionNinio ex) {
            ((Ventana) super.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
        } catch (RemoteException ex) {
            ((Ventana) super.getVentana()).mostrarMensaje(ExceptionRMI.obtenerMensaje(ExceptionRMI.ESTABLECER_CONEXION), Ventana.ERROR);
        } catch (NumberFormatException ex) {
            ((Ventana) super.getVentana()).mostrarMensaje("El campo cedula tiene que ser numérico.", Ventana.ERROR);
        }
    }
}
