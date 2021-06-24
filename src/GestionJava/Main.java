/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionJava;


import Controlador.ControlClientes;
import Modelo.ClientesDAO;
import Vista.VistaPrincipal;



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
        
        viewPrincipal.setControlador(controladorClientes);
        
        viewPrincipal.setLocationRelativeTo(null);
        viewPrincipal.setVisible(true);
       
        
//        VistaPrincipal mainwin = new VistaPrincipal();
//        mainwin.setTitle("::: Gestion Java 0.1 ::: Por JR Blanco :::");
//        mainwin.setLocationRelativeTo(null);
//        mainwin.setVisible(true);
//        
        
        
   

    }

}
