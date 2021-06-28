/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;



/**
 *
 * @author jrblanco
 */
public class ConexionBD {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String urlLocal = "jdbc:mysql://192.168.0.100:3306/test";
    private static final String urlInternet = "jdbc:mysql://62.42.154.158:3306/test";
    private static final String urlInternetDDNS = "jdbc:mysql://jrblanco.ddns.net:3306/test";
    private static final String usuario = "admin";
    private static final String password = "6979junx@76";
    
    /**
     * Es protected, solo en el modulo se puede usar.Parametro: int net:
     *
     *
     * @param net
     *       0 = Para red local
     *       1 = Desde Internet
     *       2 = desde la ddns
     * @return 
     *       La conexion a la base de datos.
     */
    protected static Connection getConexion(int net) {
        Connection conn = null;
        
        try {
            Class.forName(driver);
            if (net==0) {
                conn = DriverManager.getConnection(urlLocal,usuario,password);
            }else if (net==1) {
                conn = DriverManager.getConnection(urlInternet,usuario,password);
            }else {
                conn = DriverManager.getConnection(urlInternetDDNS,usuario,password);
            }
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error cargando Driver: " + ex.getMessage());
        }catch (SQLException ex) {
            System.out.println("Error en la conexión con la base de datos: " + ex.getMessage());
        }
        
        return conn;
    } 
    
}
