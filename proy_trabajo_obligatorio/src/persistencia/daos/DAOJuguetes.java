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
import persistencia.PoolConexiones;
import persistencia.consultas.Consultas;

/**
 *
 * @author Juan Aparicio
 */
public class DAOJuguetes {
    private int cedulaNinio;
    private PoolConexiones ipc;
    
    public DAOJuguetes(int cedulaNinio) throws ExceptionPersistencia{
        this.ipc = new PoolConexiones();
        this.cedulaNinio = cedulaNinio;
    }
    
    public void insback(Juguete juguete) throws ExceptionPersistencia{
        /*Inicializo las variables*/
        Conexion con = (Conexion) this.ipc.obtenerConexion(true);
        Connection c = con.getConexion();
        boolean ok = false;
        
        System.out.println("Juguete: " + juguete.getNumero() + "; " + juguete.getDescripcion() + "; ");
        /*Hago las consultas a la base de datos.*/
        try {
            PreparedStatement pstmt = c.prepareStatement(Consultas.INGRESAR_JUGUETE);
            pstmt.setInt(1, juguete.getNumero());
            pstmt.setString(2, juguete.getDescripcion());
            pstmt.setInt(3, this.cedulaNinio);
            pstmt.executeUpdate();
            pstmt.close();
            ok = true;
        } catch (SQLException ex) {
            ok = false;
            throw new ExceptionPersistencia(ExceptionPersistencia.INGRESAR_DATOS);
        } finally {
            /*Libero la conexion*/
            this.ipc.liberarConexion(con, ok);
        }
    }
    
    public int largo() throws ExceptionPersistencia{
        /*Inicializo las variables*/
        Conexion con = (Conexion) this.ipc.obtenerConexion(true);
        Connection c = con.getConexion();
        int largo = 0;
        boolean ok = false;
        System.out.println("Largo: " + largo);
        /*Hago la consulta a la base de datos*/
        try {
            System.out.println("Largo: " + largo);
            PreparedStatement pstmt = c.prepareStatement(Consultas.CANTIDAD_JUGUETES_NINIO);
            pstmt.setInt(1, this.cedulaNinio);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Largo: " + largo);
            /*Como solo me devuelve una tupla, no tengo que iterar, con un if me alcanza*/
            if(rs.next()){
                largo = rs.getInt(1);
            }
            System.out.println("Largo: " + largo);
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
        return largo;
    }
    
    public Juguete k_esimo(int numeroJuguete) throws ExceptionPersistencia{
        /*Inicializo las variables*/
        Juguete jug = null;
        Conexion con = (Conexion) this.ipc.obtenerConexion(true);
        Connection c = con.getConexion();
        boolean ok = false;
        
        /*Obtengo los datos de la base de datos*/
        try {
            PreparedStatement pstmt = c.prepareStatement(Consultas.OBTENER_JUGUETE);
            pstmt.setInt(1, numeroJuguete);
            pstmt.setInt(2, this.cedulaNinio);
            ResultSet rs = pstmt.executeQuery();
            /*Como la consulta solo me devuelve un juguete, no tengo que usar un while*/
            if(rs.next()){
                int numJug = rs.getInt("numero");
                String descripcion = rs.getString("descripcion");
                jug = new Juguete(numJug, descripcion);
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
        
        /*Devuelvo los datos*/
        return jug;
    }
    
    public List<VOJuguete> listarJuguetes() throws ExceptionPersistencia{
        /*Inicializo las variables*/
        Conexion con = (Conexion) this.ipc.obtenerConexion(false);
        Connection c = ((Conexion) con).getConexion();
        ArrayList<VOJuguete> ret = new ArrayList<>();
        boolean ok = false;
        
        /*Obtengo los datos de la base de datos*/
        try {
            PreparedStatement pstmt = c.prepareStatement(Consultas.LISTAR_JUGUETES);
            pstmt.setInt(1, this.cedulaNinio);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                int numero = rs.getInt("numero");
                String descripcion = rs.getString("descripcion");
                /*La cedula del ninio no la obtengo del rs, porque son todos la misma*/
                ret.add(new VOJuguete(numero, descripcion, this.cedulaNinio));
            }
            rs.close();
            pstmt.close();
            ok = true;
        } catch (SQLException e) {
            ok = false;
        } finally {
            /*Libero la conexion*/
            this.ipc.liberarConexion(con, ok);
        }
        
        /*Devuelvo los datos*/
        return ret;
    }
    
    public void borrarJuguetes() throws ExceptionPersistencia{
        /*Inicializo las variables*/
        Conexion con = (Conexion) this.ipc.obtenerConexion(true);
        Connection c = ((Conexion) con).getConexion();
        boolean ok = false;
        
        /*Borro los datos de la base de datos*/
        try {
            PreparedStatement pstmt = c.prepareStatement(Consultas.BORRAR_JUGUETES_NINIO);
            pstmt.setInt(1, this.cedulaNinio);
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
}
