/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import logica.excepciones.ExceptionPersistencia;
import persistencia.daos.IDAOJuguetes;
import persistencia.daos.IDAONinios;

/**
 *
 * @author juan
 */
public interface FabricaAbstracta {

    public IDAONinios crearIDAONinios() throws ExceptionPersistencia;

    public IDAOJuguetes crearIDAOJuguetes(int cedulaNinio) throws ExceptionPersistencia;
}
