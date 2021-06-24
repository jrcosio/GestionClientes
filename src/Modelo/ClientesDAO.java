/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author jrblanco
 * 
 * Descripción: Es el Objeto de Acceso a los Datos (DAO = Data Access Object)
 */
public class ClientesDAO {
    
    private PreparedStatement pst;
            
    private ResultSet rs;
    
    public ArrayList<Cliente> readAllClientes(){
        
        
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        Cliente cliente;
        Connection conexion = null;
        
        try {
            conexion = ConexionBD.getConexion(0);
            
            if (conexion != null){
                String sql = "SELECT * FROM clientes WHERE 1";
                
                pst = conexion.prepareStatement(sql);
                        
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    cliente = new Cliente(); //Creamos un objeto cliente
                    
                    cliente.setId(rs.getInt("id"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setNif(rs.getString("nif"));
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setLocalidad(rs.getString("localidad"));
                    cliente.setCp(rs.getString("cp"));
                    cliente.setProvincia(rs.getString("provincia"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setObservaciones(rs.getString("observaciones"));
                    
                    listaClientes.add(cliente);
                    
                }
                
               
            }else{
                System.out.println("Error en la conexión");
            }
        
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                pst.close();
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando la conexión de readALL: " + ex.getMessage());
            }
        }
       
       
        return listaClientes;
        
        
    }
    
    public boolean add(Cliente cliente){
        
        boolean estado = false;
        Connection conexion = null;
        
        try {
            conexion = ConexionBD.getConexion(0);
            
            if (conexion != null){
        
                String sql = "INSERT INTO clientes(nombre, nif, direccion, localidad, cp, provincia, telefono, email, observaciones) VALUES (?,?,?,?,?,?,?,?,?)";

                pst = conexion.prepareStatement(sql);

                pst.setString(1, cliente.getNombre());
                pst.setString(2, cliente.getNif());
                pst.setString(3, cliente.getDireccion());
                pst.setString(4, cliente.getLocalidad());
                pst.setString(5, cliente.getCp());
                pst.setString(6, cliente.getProvincia());
                pst.setString(7, cliente.getTelefono());
                pst.setString(8, cliente.getEmail());
                pst.setString(9, cliente.getObservaciones());

                int res = pst.executeUpdate();
                
                estado = res > 0; // si el resultado de ejecutar la sentencia SQL es mayor que cero me devuelve true.
               
            }else{
                System.out.println("Error en la conexión");
            }
        
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                pst.close();
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando la conexión de ADD: " + ex.getMessage());
            }
        }
        
        return estado;
    }
    
    public boolean update(Cliente cliente) {
        
        boolean estado = false;
        Connection conexion = null;
        
        try {
            conexion = ConexionBD.getConexion(0);
            
            if (conexion != null){
        
                String sql = "UPDATE clientes SET nombre=?, nif=?, direccion=?, localidad=?, cp=?, provincia=?, telefono=?, email=?, observaciones=? WHERE id=?"; 
                        
                pst = conexion.prepareStatement(sql);

                pst.setString(1, cliente.getNombre());
                pst.setString(2, cliente.getNif());
                pst.setString(3, cliente.getDireccion());
                pst.setString(4, cliente.getLocalidad());
                pst.setString(5, cliente.getCp());
                pst.setString(6, cliente.getProvincia());
                pst.setString(7, cliente.getTelefono());
                pst.setString(8, cliente.getEmail());
                pst.setString(9, cliente.getObservaciones());
                
                pst.setInt(10, cliente.getId());

                int res = pst.executeUpdate();
                
                estado = res > 0; // si el resultado de ejecutar la sentencia SQL es mayor que cero me devuelve true.
               
            }else{
                System.out.println("Error en la conexión");
            }
        
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                pst.close();
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error Cerrando la base de datos en UPDATE");
            }
        }
        
        return estado;
        
    }
    
    public boolean delete(int id){
        
        boolean estado = false;
        Connection conexion = null;
        
        try {
            conexion = ConexionBD.getConexion(0);
            
            if (conexion != null){
        
                String sql = "DELETE FROM clientes WHERE id=?"; 
                        
                pst = conexion.prepareStatement(sql);

                pst.setInt(1, id);
                int res = pst.executeUpdate();
                
                estado = res > 0; // si el resultado de ejecutar la sentencia SQL es mayor que cero me devuelve true y todo fue correcto.
               
            }else{
                System.out.println("Error en la conexión");
            }
        
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                pst.close();
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error Cerrando la base de datos en DELETE");
            }
        }
        
        return estado;
        
        
    }
    
    
}
