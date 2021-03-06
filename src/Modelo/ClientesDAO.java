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
public class ClientesDAO{ //extends ConexionBD {

    
    public ArrayList<Cliente> readAllClientes(){
        
        PreparedStatement pst;
        ResultSet rs;
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        Cliente cliente;
       
        Connection conexion = null;
        
        try {
            conexion = ConexionPool.getInstancia().getConexion();  
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
                System.out.println("Error en la conexión 0x0001");
            }
        
        }catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                ConexionPool.getInstancia().cerrarConexion(conexion);
            } catch (SQLException ex) {
                System.out.println("Error cerrando la conexión de readALL: " + ex.getMessage());
            }
        }
        
        return listaClientes;
    }
    
    public Cliente readCliente(int ID){
        PreparedStatement pst;
        ResultSet rs;
        Cliente cliente=null;
        

        Connection conexion = null;
                
        try {
            conexion = ConexionPool.getInstancia().getConexion();
            if (conexion != null){
                String sql = "SELECT * FROM clientes WHERE id=?";
                
                pst = conexion.prepareStatement(sql);
                        
                pst.setInt(1, ID);
                
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
                }
            }else{
                System.out.println("Error en la conexión 0x0002");
            }
        
        }catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                ConexionPool.getInstancia().cerrarConexion(conexion);
            } catch (SQLException ex) {
                System.out.println("Error cerrando la conexión de readALL: " + ex.getMessage());
            }
        }
       
       
        return cliente;
        
        
    }
    
    public boolean add(Cliente cliente){
        
        PreparedStatement pst;
        boolean estado = false;
        //Connection conexion = getConexion();
        
        Connection conexion = null;
        try {
            conexion = ConexionPool.getInstancia().getConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la conexión");
        }
        try {
            
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
                System.out.println("Error en la conexión 0x0003");
            }
        
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                ConexionPool.getInstancia().cerrarConexion(conexion);
            } catch (SQLException ex) {
                System.out.println("Error cerrando la conexión de readALL: " + ex.getMessage());
            }
        }
        
        return estado;
    }
    
    public boolean update(Cliente cliente) {
        
        PreparedStatement pst;
        boolean estado = false;
//        Connection conexion = getConexion();

        Connection conexion = null;
        try {
            conexion = ConexionPool.getInstancia().getConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la conexión");
        }
        try {
         
            
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
                System.out.println("Error en la conexión 0x0004");
            }
        
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                ConexionPool.getInstancia().cerrarConexion(conexion);
            } catch (SQLException ex) {
                System.out.println("Error cerrando la conexión de readALL: " + ex.getMessage());
            }
        }
        
        return estado;
        
    }
    
    public boolean delete(int id){
        PreparedStatement pst;
        boolean estado = false;
//        Connection conexion = getConexion();
        Connection conexion = null;
        try {
            conexion = ConexionPool.getInstancia().getConexion();
        } catch (SQLException ex) {
            System.out.println("Error en la conexión");
        }
        
        try {
           
            
            if (conexion != null){
        
                String sql = "DELETE FROM clientes WHERE id=?"; 
                        
                pst = conexion.prepareStatement(sql);

                pst.setInt(1, id);
                int res = pst.executeUpdate();
                
                estado = res > 0; // si el resultado de ejecutar la sentencia SQL es mayor que cero me devuelve true y todo fue correcto.
               
            }else{
                System.out.println("Error en la conexión 0x0005");
            }
        
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                ConexionPool.getInstancia().cerrarConexion(conexion);
            } catch (SQLException ex) {
                System.out.println("Error cerrando la conexión de readALL: " + ex.getMessage());
            }
        }
        
        return estado;
        
        
    }
    
    
}
