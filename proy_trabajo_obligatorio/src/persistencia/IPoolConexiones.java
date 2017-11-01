/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

/**
 *
 * @author juan
 */
public interface IPoolConexiones {
    public IConexion obtenerConexion(boolean b);
    public void liberarConexion(IConexion ic, boolean b);
}
