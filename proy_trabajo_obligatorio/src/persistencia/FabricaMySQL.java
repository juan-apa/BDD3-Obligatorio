/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import logica.excepciones.ExceptionPersistencia;
import persistencia.daos.DAOJuguetes;
import persistencia.daos.DAONinios;
import persistencia.daos.IDAOJuguetes;
import persistencia.daos.IDAONinios;

/**
 *
 * @author Juan Aparicio
 */
public class FabricaMySQL implements FabricaAbstracta {

    public IDAONinios crearIDAONinios() throws ExceptionPersistencia {
        return new DAONinios();
    }

    public IDAOJuguetes crearIDAOJuguetes(int cedulaNinio) throws ExceptionPersistencia {
        return new DAOJuguetes(cedulaNinio);
    }

    @Override
    public IPoolConexiones crearIPoolConexiones() throws ExceptionPersistencia {
        return new PoolConexiones();
    }

}
