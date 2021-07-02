
package Modelo;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;

/**
 *
 * @author jrblanco
 * Usando el Patrón Singleton.
 */
public class ConexionPool {
    
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String IP = "192.168.64.2";
    private final String url="jdbc:mysql://" + IP + ":3306/test";
    private final String usuario = "admin";
    private final String password = "";

    private static ConexionPool dataSource;
    private BasicDataSource basicDataSource = null;
    
    private ConexionPool() {
        
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driver);
        basicDataSource.setUsername(usuario);
        basicDataSource.setPassword(password);
        basicDataSource.setUrl(url);
        
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(20);
        basicDataSource.setMaxTotal(50);
        basicDataSource.setMaxWaitMillis(-1);
   
    }

    public static ConexionPool getInstancia() {
        if (dataSource == null){    //Si no esta iniciado pues lo inicia
            dataSource = new ConexionPool();
        }
        return dataSource; //devuelve el la conexión
    }
    
    public Connection getConexion() throws SQLException {
        return this.basicDataSource.getConnection();
    }
    
    public void cerrarConexion(Connection conn) throws SQLException{
        conn.close();
    }

    public String getIP() {
        return IP;
    }
    
    
    
}
