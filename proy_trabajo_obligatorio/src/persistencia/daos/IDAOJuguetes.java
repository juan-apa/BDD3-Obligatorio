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
 * @author juan
 */
public interface IDAOJuguetes {

    public void insback(Juguete juguete, IConexion ic) throws ExceptionPersistencia;

    public int largo(IConexion ic) throws ExceptionPersistencia;

    public Juguete k_esimo(int numeroJuguete, IConexion ic) throws ExceptionPersistencia;

    public List<VOJuguete> listarJuguetes(IConexion ic) throws ExceptionPersistencia;

    public void borrarJuguetes(IConexion ic) throws ExceptionPersistencia;
}
