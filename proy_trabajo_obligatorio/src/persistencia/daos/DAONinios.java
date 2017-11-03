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
import logica.Ninio;
import logica.excepciones.ExceptionPersistencia;
import logica.valueObjects.VONinio;
import persistencia.IPoolConexiones;
import persistencia.PoolConexiones;
import persistencia.Conexion;
import persistencia.consultas.Consultas;

/**
 *
 * @author Juan Aparicio
 */
public class DAONinios {

    /*Atributos para acceso a la DB*/
    private PoolConexiones ipc;

    public DAONinios() throws ExceptionPersistencia {
        ipc = new PoolConexiones();
    }

    public boolean member(int cedulaNinio) throws ExceptionPersistencia {
        /*Inicializo las variables*/
        Conexion con = (Conexion) this.ipc.obtenerConexion(false);
        Connection c = ((Conexion) con).getConexion();
        boolean existeNinio = false;
        boolean ok = false;

        /*Hago la consulta con la base de datos.*/
        PreparedStatement pstmt = null;
        try {
            pstmt = c.prepareStatement(Consultas.EXISTE_NINIO);
            pstmt.setInt(1, cedulaNinio);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                existeNinio = true;
            }
            rs.close();
            pstmt.close();
            ok = true;
        } catch (SQLException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        } finally {
            /*Libero la conexion*/
            this.ipc.liberarConexion(con, ok);
        }

        /*Devuelvo el resultado*/
        return existeNinio;
    }

    public void insert(Ninio ninio) throws ExceptionPersistencia {
        /*Inicializo las variables*/
        Conexion con = (Conexion) this.ipc.obtenerConexion(true);
        Connection c = ((Conexion) con).getConexion();
        boolean ok = false;

        /*Hago la consulta a la base de datos*/
        try {
            PreparedStatement pstmt = c.prepareStatement(Consultas.INGRESAR_NINIO);
            pstmt.setInt(1, ninio.getCedula());
            pstmt.setString(2, ninio.getNombre());
            pstmt.setString(3, ninio.getApellido());
            pstmt.executeUpdate();
            pstmt.close();
            ok = true;
        } catch (SQLException ex) {
            ok = false;
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        } finally {
            /*Libero la conexion*/
            this.ipc.liberarConexion(con, ok);
        }
    }

    public Ninio find(int cedulaNinio) throws ExceptionPersistencia {
        /*Inicializo las variables*/
        Ninio ninio = null;
        Conexion con = (Conexion) this.ipc.obtenerConexion(false);
        Connection c = ((Conexion) con).getConexion();
        boolean ok = false;

        /*Hago la consulta a la base de datos*/
        try {
            PreparedStatement pstmt = c.prepareStatement(Consultas.OBTENER_NINIO);
            pstmt.setInt(1, cedulaNinio);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                ninio = new Ninio(rs.getInt("cedula"), rs.getString("nombre"), rs.getString("apellido"), new DAOJuguetes(rs.getInt("cedula")));
            }
            rs.close();
            pstmt.close();
            ok = true;
        } catch (SQLException e) {
            ok = false;
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        } finally {
            /*Libero la conexion*/
            this.ipc.liberarConexion(con, ok);
        }

        return ninio;
    }

    public void delete(int cedulaNinio) throws ExceptionPersistencia {
        /*Inicializo las variables*/
        Conexion con = (Conexion) this.ipc.obtenerConexion(true);
        Connection c = ((Conexion) con).getConexion();
        boolean ok = false;

        /*Hago la consulta a la base de datos*/
        try {
            /*TODO antes tengo que borrar los juguetes del ninio*/
 /*TODO llamar al borrar juguetes del DAOJuguetes*/
            PreparedStatement pstmt = c.prepareStatement(Consultas.BORRAR_NINIO);
            pstmt.setInt(1, cedulaNinio);
            pstmt.executeUpdate();
            pstmt.close();
            ok = true;
        } catch (SQLException e) {
            ok = false;
            throw new ExceptionPersistencia(ExceptionPersistencia.BORRAR_DATOS);
        } finally {
            /*Libero la conexion*/
            this.ipc.liberarConexion(con, ok);
        }
    }

    public List<VONinio> listarNinios() throws ExceptionPersistencia {
        /*Inicializo las variables*/
        Conexion con = (Conexion) this.ipc.obtenerConexion(false);
        Connection c = ((Conexion) con).getConexion();
        List<VONinio> ret = new ArrayList<>();
        boolean ok = false;

        /*Hago la consulta a la base de datos*/
        try {
            PreparedStatement pstmt = c.prepareStatement(Consultas.LISTAR_NINIOS);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int cedula = rs.getInt("cedula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                ret.add(new VONinio(cedula, nombre, apellido));
            }
            rs.close();
            pstmt.close();
            ok = true;
        } catch (SQLException e) {
            ok = false;
            throw new ExceptionPersistencia(ExceptionPersistencia.OBTENER_DATOS);
        } finally {
            /*Libero la conexion*/
            this.ipc.liberarConexion(con, ok);
        }

        /*Devuelvo el resultado*/
        return ret;
    }
}
