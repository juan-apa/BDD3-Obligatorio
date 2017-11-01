/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logica.Juguete;
import logica.Ninio;
import persistencia.consultas.Consultas;
import persistencia.Conexion;
import persistencia.IConexion;

/**
 *
 * @author Juan Aparicio
 */
public class AccesoBD {
//    public static boolean existeNinio(IConexion con, int cedula) throws SQLException{
//        Connection c = ((Conexion) con).getConexion();
//        boolean existeNinio = false;
//        PreparedStatement pstmt = c.prepareStatement(Consultas.EXISTE_NINIO);
//        pstmt.setInt(1, cedula);
//        ResultSet rs = pstmt.executeQuery();
//        if(rs.next()){
//            existeNinio = true;
//        }
//        rs.close();
//        pstmt.close();
//        return existeNinio;
//    }
//    
//    public static boolean existeJugueteNinio(IConexion con, int cedN, int numJ) throws SQLException{
//        Connection c = ((Conexion) con).getConexion();
//        boolean existeJugueteNinio = false;
//        PreparedStatement pstmt = c.prepareStatement(Consultas.EXISTE_JUGUETE_NINIO);
//        pstmt.setInt(1, numJ);
//        pstmt.setInt(2, cedN);
//        ResultSet rs = pstmt.executeQuery();
//        
//        if(rs.next()){
//            existeJugueteNinio = true;
//        }
//        rs.close();
//        pstmt.close();
//        return existeJugueteNinio;
//    }
//    
//    public static void insertarNinio(IConexion con, Ninio n) throws SQLException{
//        Connection c = ((Conexion) con).getConexion();
//        PreparedStatement pstmt = c.prepareStatement(Consultas.INGRESAR_NINIO);
//        pstmt.setInt(1, n.getCedula());
//        pstmt.setString(2, n.getNombre());
//        pstmt.setString(3, n.getApellido());
//        pstmt.executeUpdate();
//        pstmt.close();
//    }
//    
//    public static void insertarJuguete(IConexion con, Juguete j) throws SQLException{
//        Connection c = ((Conexion) con).getConexion();
//        PreparedStatement pstmt = c.prepareStatement(Consultas.INGRESAR_JUGUETE);
//        pstmt.setInt(1, j.getNumero());
//        pstmt.setString(2, j.getDescripcion());
//        pstmt.setInt(3, j.getCedulaNinio());
//        pstmt.executeUpdate();
//        pstmt.close();
//    }
//    
//    public static List<Ninio> listarTodosNinios(IConexion con) throws SQLException{
//        Connection c = ((Conexion) con).getConexion();
//        ArrayList<Ninio> ret = new ArrayList<Ninio>();
//        PreparedStatement pstmt = c.prepareStatement(Consultas.LISTAR_NINIOS);
//        ResultSet rs = pstmt.executeQuery();
//        
//        while(rs.next()){
//            int cedula = rs.getInt("cedula");
//            String nombre = rs.getString("nombre");
//            String apellido = rs.getString("apellido");
//            ret.add(new Ninio(cedula, nombre, apellido));
//        }
//        rs.close();
//        pstmt.close();
//        return ret;
//    }
//    
//    public static List<Juguete> listarJuguetesDeNinio(IConexion con, int cedulaNinio) throws SQLException{
//        Connection c = ((Conexion) con).getConexion();
//        ArrayList<Juguete> ret = new ArrayList<Juguete>();
//        PreparedStatement pstmt = c.prepareStatement(Consultas.LISTAR_JUGUETES);
//        pstmt.setInt(1, cedulaNinio);
//        ResultSet rs = pstmt.executeQuery();
//        
//        while(rs.next()){
//            int numero = rs.getInt("numero");
//            String descripcion = rs.getString("descripcion");
//            /*La cedula del ninio no la obtengo del rs, porque son todos la misma*/
//            ret.add(new Juguete(numero, descripcion, cedulaNinio));
//        }
//        rs.close();
//        pstmt.close();
//        return ret;
//    }
//    
//    public static String descripcionJuguete(IConexion con, int numeroJuguete, int cedulaNinio) throws SQLException{
//        Connection c = ((Conexion) con).getConexion();
//        String ret = new String();
//        PreparedStatement pstmt = c.prepareStatement(Consultas.DAR_DESCRIPCION);
//        pstmt.setInt(1, numeroJuguete);
//        pstmt.setInt(2, cedulaNinio);
//        ResultSet rs = pstmt.executeQuery();
//        
//        while(rs.next()){
//            ret = rs.getString("descripcion");
//        }
//        rs.close();
//        pstmt.close();
//        return ret;
//    }
//    
//    public static void borrarJuguetes(IConexion con, int cedulaNinio) throws SQLException{
//        Connection c = ((Conexion) con).getConexion();
//        PreparedStatement pstmt = c.prepareStatement(Consultas.BORRAR_JUGUETES_NINIO);
//        pstmt.setInt(1, cedulaNinio);
//        pstmt.executeUpdate();
//        pstmt.close();
//    }
//    
//    public static void borrarNinios(IConexion con, int cedulaNinio) throws SQLException{
//        Connection c = ((Conexion) con).getConexion();
//        PreparedStatement pstmt = c.prepareStatement(Consultas.BORRAR_NINIO);
//        pstmt.setInt(1, cedulaNinio);
//        pstmt.executeUpdate();
//        pstmt.close();
//    }
//    
//    public static int ultimoNumeroJugueteNinio(IConexion con, int cedulaNinio) throws SQLException{
//        Connection c = ((Conexion) con).getConexion();
//        PreparedStatement pstmt = c.prepareStatement(Consultas.NUMERO_ULTIMO_JUGUETE_NINIO);
//        pstmt.setInt(1, cedulaNinio);
//        ResultSet rs = pstmt.executeQuery();
//        int ultJuguete = -1;
//        if(rs.next()){
//            ultJuguete = rs.getInt("numero");
//        }
//        rs.close();
//        pstmt.close();
//        return ultJuguete;
//    }
}
