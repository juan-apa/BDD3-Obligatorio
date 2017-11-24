/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import logica.excepciones.ExceptionPersistencia;
import persistencia.consultas.Consultas;

/**
 *
 * @author Juan Aparicio
 */
public class CreacionBDD {

    public static void main(String[] args) {
        try {
            System.out.println(System.getProperty("user.dir"));
            crearBaseDeDatos();
        } catch (ExceptionPersistencia ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static void crearBaseDeDatos() throws ExceptionPersistencia {
        Properties p = new Properties();
        try {
            p.load(new FileReader(".resources/config.properties"));
        } catch (IOException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.ABRIR_PROPERTIES);
        }

        String url = p.getProperty("url");
        String driver = p.getProperty("driver");
        String user = p.getProperty("user");
        String password = p.getProperty("password");

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);
            crearBDD(con);
            crearTablaNinios(con);
            crearTablaJuguetes(con);
            con.close();
        } catch (SQLException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.CERRAR_CONEXION);
        } catch (ClassNotFoundException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.CARGAR_DRIVER);
        }
    }

    private static void crearBDD(Connection con) throws ExceptionPersistencia {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(Consultas.CREAR_BASE_DATOS);
            stmt.close();
        } catch (SQLException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.CREAR_DB);
        }
    }

    private static void crearTablaNinios(Connection con) throws ExceptionPersistencia {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(Consultas.CREAR_TABLA_NINIOS);
            stmt.close();
        } catch (SQLException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.CREAR_TABLE_NINIOS);
        }
    }

    private static void crearTablaJuguetes(Connection con) throws ExceptionPersistencia {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(Consultas.CREAR_TABLA_JUGUETES);
            stmt.close();
        } catch (SQLException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.CREAR_TABLE_JUGUETES);
        }
    }
}
