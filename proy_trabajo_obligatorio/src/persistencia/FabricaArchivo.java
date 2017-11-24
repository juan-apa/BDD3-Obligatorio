/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import logica.excepciones.ExceptionPersistencia;
import persistencia.daos.DAOJuguetesArchivo;
import persistencia.daos.DAONiniosArchivo;
import persistencia.daos.IDAOJuguetes;
import persistencia.daos.IDAONinios;

/**
 *
 * @author Juan Aparicio
 */
public class FabricaArchivo implements FabricaAbstracta {

    @Override
    public IDAONinios crearIDAONinios() throws ExceptionPersistencia {
        return new DAONiniosArchivo();
    }

    @Override
    public IDAOJuguetes crearIDAOJuguetes(int cedulaNinio) throws ExceptionPersistencia {
        return new DAOJuguetesArchivo(cedulaNinio);
    }

    @Override
    public IPoolConexiones crearIPoolConexiones() throws ExceptionPersistencia {
        return new PoolConexionArchivo();
    }

}
