package edu.unicundi.mvc.controladores;

import edu.unicundi.mvc.modelo.DBManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/** 
 * @author Juan José Rangel
 * @version 1.0.0
 * @since 1.0.0
 */
public class Crear{
    //inicialicacion del gestor de conexion al servidor de base de datos
    private static DBManager database = new DBManager();

    /**
     * Crea el query con los datos para enviarlos al servidor
     * @param nombre     
     * @param apellido     
     * @param celular     
     * @param correo     
     * @param direccion
     * @since 1.0.0
     */
    public static void crear(String nombre, String apellido, String celular, String correo, String direccion) {
        try {
            String query = "INSERT INTO personas (nombre, apellido, celular, correo, direccion) VALUES (?, ?, ?, ?, ?);";
            try (PreparedStatement sentenciaP = database.open().prepareStatement(query)) {
                sentenciaP.setString(1, nombre);
                sentenciaP.setString(2, apellido);
                sentenciaP.setString(3, celular);
                sentenciaP.setString(4, correo);
                sentenciaP.setString(5, direccion);
                sentenciaP.executeUpdate();
            }
            database.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
    /**
     * crea la consulta, la envia al servidor y luego crea un arreglo para insertar en tabla
     * @return ArrayList<String[]>
     */
    public static ArrayList<String[]> obtenerTodos() {
        //array para guadar filas
        ArrayList<String[]> personas = new ArrayList<>();
        //limpiar arreglo para evitar duplicados
        personas.clear();

        try {
            String query = "SELECT * FROM personas;";
            try (PreparedStatement sentenciaP = database.open().prepareStatement(query)) {
                ResultSet resultado = sentenciaP.executeQuery();
                while (resultado.next()) {
                    personas.add(new String[] {resultado.getString("nombre"),
                    resultado.getString("apellido"),
                    resultado.getString("celular"),
                    resultado.getString("correo"),
                    resultado.getString("direccion")});
                }
            }
            database.close();
            return personas;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return personas;
    }
}
