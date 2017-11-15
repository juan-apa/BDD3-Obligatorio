/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia.daos;

import java.util.List;
import logica.Juguete;
import logica.excepciones.ExceptionPersistencia;
import logica.valueObjects.VOJuguete;
import persistencia.IConexion;

/**
 *
 * @author Juan Aparicio
 */
public class DAOJuguetesArchivo implements IDAOJuguetes{

    @Override
    public void insback(Juguete juguete, IConexion ic) throws ExceptionPersistencia {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int largo(IConexion ic) throws ExceptionPersistencia {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Juguete k_esimo(int numeroJuguete, IConexion ic) throws ExceptionPersistencia {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VOJuguete> listarJuguetes(IConexion ic) throws ExceptionPersistencia {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrarJuguetes(IConexion ic) throws ExceptionPersistencia {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
