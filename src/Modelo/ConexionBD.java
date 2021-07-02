/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author jrblanco
 */
public class ConexionBD {
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://192.168.64.2:3306/test";
    private final String usuario = "admin";
    private final String password = "";
    
    private Connection conn = null;
    
    
    /**
     * @return 
     *       La conexion a la base de datos.
     */
    public Connection getConexion() {
        
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,usuario,password);
        
        } catch (ClassNotFoundException ex) {
            System.out.println("Error cargando Driver: " + ex.getMessage());
        }catch (SQLException ex) {
            System.out.println("Error en la conexión con la base de datos: " + ex.getMessage());
        }
        
        return conn;
    } 
    
    public void cerrarConexion() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error cerrando conexión de la BD\n" + ex.getMessage());
        }
    }
    
}
