/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import logica.excepciones.ExceptionPersistencia;
import persistencia.consultas.Consultas;

/**
 *
 * @author Juan Aparicio
 */
public class ServidorRMI {
    public static void main(String[] args) {
        try {
            ServidorRMI.CrearBaseDeDatos();
            String ip = "localhost";
            String puerto = "1099";
            LocateRegistry.createRegistry(Integer.parseInt(puerto));
            Fachada fachada = Fachada.getInstancia();
            Naming.rebind("//"+ip+":"+puerto+"/fachada", fachada);
            System.out.println("Servidor corriendo...");
        } catch (ExceptionPersistencia ex) {
            System.err.println("Error al inicializar el serv. 1: " + ex.getMessage() + "; ");
                    ex.printStackTrace();
        } catch (RemoteException ex) {
            System.err.println("Error al inicializar el serv. 2: " + ex.getMessage());
        } catch (MalformedURLException ex) {
            System.err.println("Error al inicializar el serv. 3");
        } catch (ClassNotFoundException ex) {
             System.err.println("Error al inicializar el serv. bdd: " + ex.getMessage());
        }
    }
    
    public static void CrearBaseDeDatos() throws ExceptionPersistencia, ClassNotFoundException{
        Properties p = new Properties();
        try {
            p.load(new FileReader(".resources/config.properties"));
        } catch (IOException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.ABRIR_PROPERTIES);
        }
        p.setProperty("driver", "com.mysql.jdbc.Driver");
        p.setProperty("url", "jdbc:mysql://localhost/");
        p.setProperty("user", "root");
        p.setProperty("password", "root");//"290980196");
        
        String url = p.getProperty("url");
        String driver = p.getProperty("driver");
        String user = p.getProperty("user");
        String password = p.getProperty("password");
        
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url,user,password);
            String crearBaseDatos = Consultas.CREAR_BASE_DATOS;
            System.out.println("Se crea la base de datos");
            String crearTablaJuguetes = Consultas.CREAR_TABLA_JUGUETES;
            String crearTablaNinios = Consultas.CREAR_TABLA_NINIOS;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(crearBaseDatos);
             if (existeTablaJuguetes(con)){
                System.out.println("Se crea la tabla Jugetes");
                stmt.executeUpdate(crearTablaJuguetes);
            }
            if (existeTablaNinios(con)){
                System.out.println("Se crea la tabla Ninos");
                stmt.executeUpdate(crearTablaNinios);
            }
           
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            throw new ExceptionPersistencia(ex.getMessage());
        }
    }
    public static boolean existeTablaJuguetes(Connection con) throws SQLException{
        boolean existeTabla = false;
        String consultarExisteTablaJuguetes = "Select * from p4ej1.juguetes";
        Statement consulta = con.createStatement();
        ResultSet rs = consulta.executeQuery(consultarExisteTablaJuguetes);
        while(rs.next()){
            existeTabla = true;
        }
        consulta.close();
        return existeTabla;
    }
    
    public static boolean existeTablaNinios(Connection con) throws SQLException{
        boolean existeTabla = false;
        String consultarExisteTablaNinios = "Select * from p4ej1.ninios";
        Statement consulta = con.createStatement();
        ResultSet rs = consulta.executeQuery(consultarExisteTablaNinios);
        while(rs.next()){
            existeTabla = true;
        }
        consulta.close();
        return existeTabla;
    }
    
}
