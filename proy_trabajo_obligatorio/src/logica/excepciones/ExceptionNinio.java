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
public class ExceptionNinio extends Exception{
    public static final int EXISTE_NINIO = 0;
    public static final int NO_EXISTE_NINIO = 1;
    
    private static final String[] MENSAJES_ERROR = {
        "El niño ya se encuentra ingresado en el sistema.",
        "El niño no se encuentra ingresado en el sistema."
    };

    public ExceptionNinio() {
    }

    public ExceptionNinio(String mensaje) {
        super(mensaje);
    }
    
    public ExceptionNinio(int errorCode){
        super(ExceptionNinio.MENSAJES_ERROR[errorCode]);
    }

    public ExceptionNinio(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ExceptionNinio(Throwable thrwbl) {
        super(thrwbl);
    }

}
