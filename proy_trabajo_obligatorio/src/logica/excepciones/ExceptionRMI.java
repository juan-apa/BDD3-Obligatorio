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
public class ExceptionRMI extends Exception {

    public static final int ESTABLECER_CONEXION = 0;
    public static final int PUBLICAR_OBJETO = 1;

    private static final String[] MENSAJES_ERROR = {
        "Error al establer la conexión con el servidor.",
        "Error al montar el objeto en red para ser accedido por RMI."
    };

    public ExceptionRMI() {
    }

    public ExceptionRMI(String mensaje) {
        super(mensaje);
    }

    public ExceptionRMI(int errorCode) {
        super(ExceptionRMI.MENSAJES_ERROR[errorCode]);
    }

    public ExceptionRMI(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ExceptionRMI(Throwable thrwbl) {
        super(thrwbl);
    }

    public static String obtenerMensaje(int errorCode) {
        return MENSAJES_ERROR[errorCode];
    }

}
