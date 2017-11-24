/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.excepciones;

/**
 *
 * @author Juan Aparicio
 */
public class ExceptionPersistencia extends Exception {

    public static final int ABRIR_CONEXION = 0;
    public static final int CERRAR_CONEXION = 1;
    public static final int ABRIR_TRANSACCION = 2;
    public static final int COMMIT_TRANSACCION = 3;
    public static final int ROLLBACK_TRANSACCION = 4;
    public static final int CARGAR_DRIVER = 5;
    public static final int OBTENER_DATOS = 6;
    public static final int INGRESAR_DATOS = 7;
    public static final int BORRAR_DATOS = 8;
    public static final int ABRIR_PROPERTIES = 9;
    public static final int CREAR_DB = 10;
    public static final int CREAR_TABLE_NINIOS = 11;
    public static final int CREAR_TABLE_JUGUETES = 12;
    public static final int OBTENER_PROPERTY = 13;

    private static final String[] MENSAJES_ERROR = {
        "Error al abrir la conexión con la base de datos.",
        "Error al cerrar la conexión con la base de datos.",
        "Error al generar la transacción.",
        "Error al guardar los cambios en la base de datos.",
        "Error al deshacer los cambios en la base de datos.",
        "Error al cargar el driver de la base de datos.",
        "Error al obtener los datos de la base de datos.",
        "Error al ingresar los datos a la base de datos.",
        "Error al borrar los datos de la base de datos.",
        "Error al leer el archivo de configuración properties.",
        "Error al crear la base de datos.",
        "Error al crear la tabla 'Ninios'.",
        "Error al crear la tabla 'Juguetes'.",
        "Error al leer la propiedad del archivo de configuraciones"
    };

    public ExceptionPersistencia() {
    }

    public ExceptionPersistencia(String mensaje) {
        super(mensaje);
    }

    public ExceptionPersistencia(int errorCode) {
        super(ExceptionPersistencia.MENSAJES_ERROR[errorCode]);
    }

    public ExceptionPersistencia(String mensaje, Throwable thrwbl) {
        super(mensaje, thrwbl);
    }

    public ExceptionPersistencia(Throwable thrwbl) {
        super(thrwbl);
    }

    public static String obtenerMensaje(int errorCode) {
        return MENSAJES_ERROR[errorCode];
    }

}
