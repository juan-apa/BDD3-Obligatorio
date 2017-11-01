/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafica.controladoras;

import grafica.ventanas.Ventana;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import logica.excepciones.ExceptionNinio;
import logica.excepciones.ExceptionPersistencia;
import logica.valueObjects.VOJuguete;

/**
 *
 * @author Juan Aparicio
 */
public class ControllerListarJuguetes extends Controladora{
    
    public ControllerListarJuguetes(JFrame ven) {
        super(ven);
    }
    
    public List<VOJuguete> listarJuguetes(String ced){
        List<VOJuguete> ret = new ArrayList<VOJuguete>();
        try {
            int cedNum = Integer.parseInt(ced);
            ret = this.getFachada().listarJuguetes(cedNum);
        } catch (ExceptionPersistencia ex) {
            ((Ventana) this.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
        } catch (ExceptionNinio ex) {
            ((Ventana) this.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
        } catch (RemoteException ex) {
            ((Ventana) this.getVentana()).mostrarMensaje(ex.getMessage(), Ventana.ERROR);
        } catch (NumberFormatException ex) {
            ((Ventana) this.getVentana()).mostrarMensaje("El campo cédula tiene que ser numérico.", Ventana.ERROR);
        }
        return ret;
    }
}
