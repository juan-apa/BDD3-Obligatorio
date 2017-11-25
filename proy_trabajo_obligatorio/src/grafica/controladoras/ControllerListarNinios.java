/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica.controladoras;

import grafica.ventanas.ListarNinios;
import grafica.ventanas.Ventana;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import logica.IFachada;
import logica.excepciones.ExceptionPersistencia;
import logica.excepciones.ExceptionRMI;
import logica.valueObjects.VONinio;

/**
 *
 * @author Juan Aparicio
 */
public class ControllerListarNinios extends Controladora {

    public ControllerListarNinios(JFrame ven) {
        super(ven);
    }

    public List<VONinio> listarNinios() {
        List<VONinio> ret = new ArrayList<>();
        try {
            IFachada f = super.getFachada();
            if(f != null){
                ret = f.listarNinios();
            }
            else{
                throw new RemoteException();
            }
        } catch (ExceptionPersistencia ex) {
            ((Ventana) this.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
        } catch (RemoteException ex) {
            ((Ventana) this.getVentana()).mostrarMensaje(ExceptionRMI.obtenerMensaje(ExceptionRMI.ESTABLECER_CONEXION), Ventana.ERROR);
        }
        return ret;
    }

}
