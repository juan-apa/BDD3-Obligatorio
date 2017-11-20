/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.util.logging.Level;
import java.util.logging.Logger;
import logica.excepciones.ExceptionPersistencia;

/**
 *
 * @author Juan Aparicio
 */
public class PoolConexionArchivo implements IPoolConexiones{
    private int escritores;
    private int lectores;
    
    public PoolConexionArchivo(){
       this.escritores = 0;
       this.lectores = 0;
    }
    
    @Override
    public IConexion obtenerConexion(boolean modifica) throws ExceptionPersistencia {
        IConexion ret = null;
        synchronized (this) {
            while(ret == null){
                if(modifica){
                    if(escritores > 0 || lectores > 0){
                        try{
                            this.wait();
                        } catch (InterruptedException ex) {}
                    }
                    else{
                        ret = new ConexionArchivo(modifica);
                        this.escritores++;
                    }
                }
                else{
                    if(escritores > 0){
                        try{
                            this.wait();
                        } catch (InterruptedException ex) {}
                    }
                    else{
                        ret = new ConexionArchivo(modifica);
                        this.lectores++;
                    }
                }
            }
        }
        return ret;
    }

    @Override
    public void liberarConexion(IConexion con, boolean ok) throws ExceptionPersistencia {
        synchronized(this){
            if(((ConexionArchivo) con).getModifica()){
                this.escritores--;
            }
            else{
                this.lectores--;
            }
            this.notifyAll();
        }
    }

}
