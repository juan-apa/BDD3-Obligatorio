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
public class ExceptionFabrica extends Exception {

    public static final int ERROR_CREAR_FABRICA = 0;

    private static final String[] MENSAJES_ERROR = {
        "Error al crear una nueva instancia de la fabrica."
    };

    public ExceptionFabrica() {
    }

    public ExceptionFabrica(String mensaje) {
        super(mensaje);
    }

    public ExceptionFabrica(int errorCode) {
        super(ExceptionFabrica.MENSAJES_ERROR[errorCode]);
    }

    public ExceptionFabrica(String mensaje, Throwable thrwbl) {
        super(mensaje, thrwbl);
    }

    public ExceptionFabrica(Throwable thrwbl) {
        super(thrwbl);
    }

    public static String obtenerMensaje(int errorCode) {
        return MENSAJES_ERROR[errorCode];
    }
}
