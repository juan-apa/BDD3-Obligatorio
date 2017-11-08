/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import logica.excepciones.ExceptionPersistencia;

/**
 *
 * @author juan
 */
public interface IPoolConexiones {

    public IConexion obtenerConexion(boolean modifica) throws ExceptionPersistencia;

    public void liberarConexion(IConexion con, boolean ok) throws ExceptionPersistencia;
}
