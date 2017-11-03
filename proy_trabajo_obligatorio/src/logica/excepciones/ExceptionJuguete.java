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
public class ExceptionJuguete extends Exception {

    public static final int EXISTE_JUGUETE = 0;
    public static final int NO_EXISTE_JUGUETE = 1;

    private static final String[] MENSAJES_ERROR = {
        "El juguete ya se encuentra ingresado en el sistema.",
        "El juguete no se encuentra ingresado en el sistema."
    };

    public ExceptionJuguete(String mensaje) {
        super(mensaje);
    }

    public ExceptionJuguete(int errorCode) {
        super(ExceptionJuguete.MENSAJES_ERROR[errorCode]);
    }

    public ExceptionJuguete(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ExceptionJuguete(Throwable thrwbl) {
        super(thrwbl);
    }
    
    public static String obtenerMensaje(int errorCode){
        return MENSAJES_ERROR[errorCode];
    }

}
