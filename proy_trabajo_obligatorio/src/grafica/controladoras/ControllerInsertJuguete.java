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

/**
 *
 * @author Juan Aparicio
 */
public class ControllerInsertJuguete extends Controladora {

    public ControllerInsertJuguete(JFrame ven) {
        super(ven);
    }

    public void insertJuguete(String cedulaNinio, String descripcion) {
        try {
            int ced = Integer.parseInt(cedulaNinio);
            IFachada f = super.getFachada();
            if(f != null){
                f.nuevoJuguete(descripcion, ced);
                ((Ventana) this.getVentana()).mostrarMensaje("Juguete ingresado con éxito", Ventana.SUCCESS);
            }else{
                throw new RemoteException();
            }
        } catch (ExceptionPersistencia ex) {
            ((Ventana) this.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
        } catch (ExceptionNinio ex) {
            ((Ventana) this.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
        } catch (RemoteException ex) {
            ((Ventana) this.getVentana()).mostrarMensaje(ExceptionRMI.obtenerMensaje(ExceptionRMI.ESTABLECER_CONEXION), Ventana.ERROR);
        } catch (NumberFormatException ex){
            ((Ventana) this.getVentana()).mostrarMensaje("El campo cédula tiene que tener formato numérico.", Ventana.ERROR);
        }
    }
}
