/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica.controladoras;

import grafica.ventanas.Ventana;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import logica.Fachada;
import logica.excepciones.ExceptionJuguete;
import logica.excepciones.ExceptionNinio;
import logica.excepciones.ExceptionPersistencia;
import logica.excepciones.ExceptionRMI;

/**
 *
 * @author Juan Aparicio
 */
public class ControllerDarDescripcion extends Controladora {

    private Fachada f;

    public ControllerDarDescripcion(JFrame ven) {
        super(ven);
    }

    public void DarDescripcion(String cedulaNiño, String numeroJuguete) {
        try {
            int cedN = Integer.parseInt(cedulaNiño);
            int numJ = Integer.parseInt(numeroJuguete);
            String desc = super.getFachada().darDescripcion(cedN, numJ);
            ((Ventana) super.getVentana()).mostrarMensaje("La descripción del juguete es: " + desc, Ventana.SUCCESS);
        } catch (ExceptionPersistencia ex) {
            ((Ventana) super.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
        } catch (ExceptionNinio ex) {
            ((Ventana) super.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
        } catch (ExceptionJuguete ex) {
            ((Ventana) super.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
        } catch (RemoteException ex) {
            ((Ventana) super.getVentana()).mostrarMensaje(ExceptionRMI.obtenerMensaje(ExceptionRMI.ESTABLECER_CONEXION), Ventana.ERROR);
        } catch (NumberFormatException ex) {
            ((Ventana) super.getVentana()).mostrarMensaje("Los campos cedula y número tienen que ser numéricos.", Ventana.WARNING);
        }
    }

}
