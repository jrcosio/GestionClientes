/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionJava;


import Controlador.ControlClientes;
import Modelo.ClientesDAO;
import Modelo.ConexionPool;
import Vista.SplashScreen;
import Vista.VistaPrincipal;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author jrblanco
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("Gestión Java 0.1");
        
        
        //Modelo
        ClientesDAO modeloClientesDAO = new ClientesDAO();
        
        //Vista
        VistaPrincipal viewPrincipal = new VistaPrincipal();
        
        //Controlador
        ControlClientes controladorClientes = new ControlClientes(modeloClientesDAO,viewPrincipal);
         
   

    }

}
