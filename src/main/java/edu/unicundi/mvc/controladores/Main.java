package edu.unicundi.mvc.controladores;
import edu.unicundi.mvc.vista.Ventana;

/** 
 * @author Juan José Rangel
 * @version 1.0.0
 * @since 1.0.0
 */
public class Main {
     /**
     * @param args Los argumentos de la linea de comandos
     */
    public static void main(String args[]) {
        //creacion del frame
        Ventana vista = new Ventana();
        //creacion del controlador y envio de la ventana para manejo
        Controlador incio = new Controlador(vista);
    }
}
