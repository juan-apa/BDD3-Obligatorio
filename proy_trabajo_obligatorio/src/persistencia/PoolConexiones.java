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
import java.util.Properties;
import logica.excepciones.ExceptionPersistencia;

/**
 *
 * @author Juan Aparicio
 */
public class PoolConexiones implements IPoolConexiones{

    private String driver;
    private String url;
    private String user;
    private String password;
    private int nivelTransaccionalidad;
    private Conexion[] conexiones;
    private int tamanio;
    private int creadas;
    private int tope;

    public PoolConexiones() throws ExceptionPersistencia {
        Properties p = new Properties();
        try {
            p.load(new FileReader(".resources/config.properties"));
        } catch (IOException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.ABRIR_PROPERTIES);
        }
        this.driver = p.getProperty("driver");
        this.url = p.getProperty("url");
        this.user = p.getProperty("user");
        this.password = p.getProperty("password");
        this.tamanio = 10;
        this.tope = 0;
        this.conexiones = new Conexion[tamanio];
        this.creadas = 0;

        for (int i = 0; i < this.tamanio; i++) {
            this.conexiones[i] = null;
        }

        try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException ex) {
            throw new ExceptionPersistencia(ExceptionPersistencia.CARGAR_DRIVER);
        }
    }

    public IConexion obtenerConexion(boolean modifica) throws ExceptionPersistencia {
        /*Las conexiones que estan en el array con tope, no estan siendo usadas,
        solo fueron usadas y ya fueron creadas.*/
        IConexion ret = null;
        synchronized (this) {
            while (ret == null) {
                if (this.tope > 0) {
                    /*Tengo alguna conexión para dar*/
                    ret = this.conexiones[tope - 1];
                    this.tope--;
                } else {
                    /*el tope está en 0, me fijo si puedo hacer una conexión*/
                    if (this.creadas < this.tamanio) {
                        /* Puedo crear una conexion.*/
                        Connection aux = null;
                        try {
                            System.out.println("llegue");
                            aux = DriverManager.getConnection(this.url, this.user, this.password);

                        } catch (SQLException ex) {
                            throw new ExceptionPersistencia(ExceptionPersistencia.ABRIR_CONEXION);
                        }
                        ret = new Conexion(aux);
                        creadas++;
                    } else {
                        /*No puedo crear una conexion.*/
                        try {
                            wait();
                        } catch (Exception e) {
                        }
                    }
                }
            }
            /*Genero una transacción, porque en el método liberarConexion ya presupone
            que hay una transacción hecha.*/
            Connection aux = ((Conexion) ret).getConexion();
            try {
                aux.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                aux.setAutoCommit(false);
            } catch (SQLException ex) {
                throw new ExceptionPersistencia(ExceptionPersistencia.ABRIR_TRANSACCION);
            }
        }
        /*finalmente devuelvo el resultado.*/
        return ret;
    }

    public void liberarConexion(IConexion con, boolean ok) throws ExceptionPersistencia {
        synchronized (this) {
            Connection aux = ((Conexion) con).getConexion();
            if (ok) {
                /*Todo salió bien, entonces commiteo la transacción.*/
                try {
                    aux.commit();
                } catch (SQLException e) {
                    throw new ExceptionPersistencia(ExceptionPersistencia.COMMIT_TRANSACCION);
                }
            } else {
                /*Algo no salió bien entonces hago un rollback de la transacción.*/
                try {
                    aux.rollback();
                } catch (SQLException e) {
                    throw new ExceptionPersistencia(ExceptionPersistencia.ROLLBACK_TRANSACCION);
                }
            }
            this.conexiones[this.tope] = (Conexion) con;
            this.tope++;
            notify();
        }
    }
}
