/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.consultas;

/**
 *
 * @author Juan Aparicio
 */
public class Consultas {

    public static final String CREAR_BASE_DATOS = "CREATE DATABASE IF NOT EXISTS P4EJ1";
    public static final String CREAR_TABLA_NINIOS = "CREATE TABLE IF NOT EXISTS P4EJ1.Ninios (cedula int primary key not null,nombre varchar(45) not null,apellido varchar(45) not null)";
    public static final String CREAR_TABLA_JUGUETES = "CREATE TABLE IF NOT EXISTS P4EJ1.Juguetes (numero int primary key not null,descripcion varchar(45),cedulaNinio int NOT NULL,FOREIGN KEY (cedulaNinio) REFERENCES P4EJ1.Ninios(cedula))";
    public static final String LISTAR_NINIOS = "SELECT * FROM P4EJ1.Ninios ORDER BY cedula DESC";
    public static final String INGRESAR_NINIO = "INSERT INTO P4EJ1.Ninios (cedula, nombre, apellido) VALUES (?, ?, ?)";
    public static final String INGRESAR_JUGUETE = "INSERT INTO P4EJ1.Juguetes (numero, descripcion, cedulaNinio) VALUES (?, ?, ?)";
    public static final String NUMERO_ULTIMO_JUGUETE_NINIO = "SELECT numero FROM P4EJ1.Juguetes WHERE cedulaNinio = ? ORDER BY numero DESC LIMIT 1";
    public static final String LISTAR_JUGUETES = "SELECT * FROM P4EJ1.Juguetes WHERE cedulaNinio = ?";
    public static final String DAR_DESCRIPCION = "SELECT descripcion FROM P4EJ1.Juguetes WHERE numero = ? AND cedulaNinio = ?";
    public static final String BORRAR_JUGUETES_NINIO = "DELETE FROM P4EJ1.Juguetes WHERE cedulaNinio = ?";
    public static final String BORRAR_NINIO = "DELETE FROM P4EJ1.Ninios WHERE cedula = ?";
    public static final String EXISTE_NINIO = "SELECT * FROM P4EJ1.Ninios WHERE cedula = ? LIMIT 1";
    public static final String EXISTE_JUGUETE_NINIO = "SELECT numero FROM P4EJ1.Juguetes WHERE numero = ? AND cedulaNinio = ?";
    public static final String OBTENER_NINIO = "SELECT * FROM P4EJ1.Ninios WHERE cedula = ?";
    public static final String CANTIDAD_JUGUETES_NINIO = "SELECT COUNT(*) FROM P4EJ1.Juguetes WHERE cedulaNinio = ?";
    public static final String OBTENER_JUGUETE = "SELECT * FROM P4EJ1.Juguetes WHERE numero = ? AND cedulaNinio = ?";
}
