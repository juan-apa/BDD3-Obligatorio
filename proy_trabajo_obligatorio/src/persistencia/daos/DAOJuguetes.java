/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.Juguete;
import logica.excepciones.ExceptionPersistencia;
import logica.valueObjects.VOJuguete;
import persistencia.Conexion;
import persistencia.IConexion;
import persistencia.consultas.Consultas;

/**
 *
 * @author Juan Aparicio
 */
public class DAOJuguetes implements IDAOJuguetes {

    private int cedulaNinio;

    public DAOJuguetes(int cedulaNinio) throws ExceptionPersistencia {
        this.cedulaNinio = cedulaNinio;
    }

    public void insback(Juguete juguete, IConexion ic) throws ExceptionPersistencia {
        /*Inicializo las variables*/
        Conexion con = (Conexion) ic;
        Connection c = con.getConexion();

        /*Hago las consultas a la base de datos.*/
        try {
            PreparedStatement pstmt = c.prepareStatement(Consultas.INGRESAR_JUGUETE);
            pstmt.setInt(1, juguete.getNumero());
            pstmt.setString(2, juguete.getDescripcion());
            pstmt.setInt(3, this.cedulaNinio);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        }
    }

    public int largo(IConexion ic) throws ExceptionPersistencia {
        /*Inicializo las variables*/
        Conexion con = (Conexion) ic;
        Connection c = con.getConexion();
        int largo = 0;

        /*Hago la consulta a la base de datos*/
        try {
            PreparedStatement pstmt = c.prepareStatement(Consultas.CANTIDAD_JUGUETES_NINIO);
            pstmt.setInt(1, this.cedulaNinio);
            ResultSet rs = pstmt.executeQuery();
            /*Como solo me devuelve una tupla, no tengo que iterar, con un if me alcanza*/
            if (rs.next()) {
                largo = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        }

        /*Devuelvo el resultado*/
        return largo;
    }

    public Juguete k_esimo(int numeroJuguete, IConexion ic) throws ExceptionPersistencia {
        /*Inicializo las variables*/
        Juguete jug = null;
        Conexion con = (Conexion) ic;
        Connection c = con.getConexion();

        /*Obtengo los datos de la base de datos*/
        try {
            PreparedStatement pstmt = c.prepareStatement(Consultas.OBTENER_JUGUETE);
            pstmt.setInt(1, numeroJuguete);
            pstmt.setInt(2, this.cedulaNinio);
            ResultSet rs = pstmt.executeQuery();
            /*Como la consulta solo me devuelve un juguete, no tengo que usar un while*/
            if (rs.next()) {
                int numJug = rs.getInt("numero");
                String descripcion = rs.getString("descripcion");
                jug = new Juguete(numJug, descripcion);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        }

        /*Devuelvo los datos*/
        return jug;
    }

    public List<VOJuguete> listarJuguetes(IConexion ic) throws ExceptionPersistencia {
        /*Inicializo las variables*/
        Conexion con = (Conexion) ic;
        Connection c = ((Conexion) con).getConexion();
        ArrayList<VOJuguete> ret = new ArrayList<>();

        /*Obtengo los datos de la base de datos*/
        try {
            PreparedStatement pstmt = c.prepareStatement(Consultas.LISTAR_JUGUETES);
            pstmt.setInt(1, this.cedulaNinio);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int numero = rs.getInt("numero");
                String descripcion = rs.getString("descripcion");
                /*La cedula del ninio no la obtengo del rs, porque son todos la misma*/
                ret.add(new VOJuguete(numero, descripcion, this.cedulaNinio));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        }

        /*Devuelvo los datos*/
        return ret;
    }

    public void borrarJuguetes(IConexion ic) throws ExceptionPersistencia {
        /*Inicializo las variables*/
        Conexion con = (Conexion) ic;
        Connection c = ((Conexion) con).getConexion();

        /*Borro los datos de la base de datos*/
        try {
            PreparedStatement pstmt = c.prepareStatement(Consultas.BORRAR_JUGUETES_NINIO);
            pstmt.setInt(1, this.cedulaNinio);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new ExceptionPersistencia(ExceptionPersistencia.BORRAR_DATOS);
        }
    }
}
