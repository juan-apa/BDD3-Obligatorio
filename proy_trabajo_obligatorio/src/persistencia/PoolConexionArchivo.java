/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import logica.excepciones.ExceptionPersistencia;

/**
 *
 * @author Juan Aparicio
 */
public class PoolConexionArchivo implements IPoolConexiones{

    @Override
    public IConexion obtenerConexion(boolean modifica) throws ExceptionPersistencia {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void liberarConexion(IConexion con, boolean ok) throws ExceptionPersistencia {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
