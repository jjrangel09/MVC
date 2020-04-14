package edu.unicundi.mvc.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/** 
 * @author Juan José Rangel
 * @version 1.0.0
 * @since 1.0.0
 */
public class DBManager {
    //controlador de la base (actualizado)
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //url del servidor de la base (se debe especificar la zona horaria para evitar conflictos)
    private final String DB_URL = "jdbc:mysql://localhost:3306/mvc?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    //usuario y contraseña de la base
    private static final String USER = "root";
    private static final String PASS = "";
    
    //inicializacion de variable que obtiene la conexion a base de adatos
    private Connection conn = null;
    /**
    *metodo para abrir la conexion
    *@return Connection 
    */
    public Connection open() {
        try {
            Class.forName(JDBC_DRIVER);
            try {
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (SQLException ex) {
                System.out.println("No se pudo conectar a la base de datos: " + ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Problemas de conexion:" + ex);
        }
        return conn;
    }
    /**
    *metodo que cierra la conexion a la base de datos
    */
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("No se pudo cerrar la conexion: " + e);
        }
    }
}
