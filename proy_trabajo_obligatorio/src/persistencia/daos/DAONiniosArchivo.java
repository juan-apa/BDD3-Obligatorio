/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia.daos;

import java.util.List;
import logica.Ninio;
import logica.excepciones.ExceptionPersistencia;
import logica.valueObjects.VONinio;
import persistencia.IConexion;

/**
 *
 * @author Juan Aparicio
 */
public class DAONiniosArchivo implements IDAONinios{

    @Override
    public boolean member(int cedulaNinio, IConexion ic) throws ExceptionPersistencia {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Ninio ninio, IConexion ic) throws ExceptionPersistencia {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ninio find(int cedulaNinio, IConexion ic) throws ExceptionPersistencia {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int cedulaNinio, IConexion ic) throws ExceptionPersistencia {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VONinio> listarNinios(IConexion ic) throws ExceptionPersistencia {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
