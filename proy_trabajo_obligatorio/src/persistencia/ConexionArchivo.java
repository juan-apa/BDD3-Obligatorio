/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.io.ObjectOutputStream;

/**
 *
 * @author Juan Aparicio
 */
public class ConexionArchivo implements IConexion{
    private boolean modifica;
    
    public ConexionArchivo(boolean modifica){
        this. modifica = modifica;
    }
    
    public boolean getModifica(){
        return this.modifica;
    }
}
