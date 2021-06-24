/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClientesDAO;
import Vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jrblanco
 */
public class ControlClientes implements ActionListener{
    
    /*
        Atributos, un objeto del modelo y de la vista
    */
    private ClientesDAO modeloClientes;
    private VistaPrincipal vistaClientes;
    
    public ControlClientes(ClientesDAO modelo, VistaPrincipal vista){
        this.modeloClientes = modelo;
        this.vistaClientes = vista;
        
        this.RellenarTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Añadir")) {
            System.out.println("Boton Añadir");
            this.anadirDatosClientes();
          
        } else if (e.getActionCommand().equals("Guardar")){
            System.out.println("Boton Guardar");
        } else if (e.getActionCommand().equals("Borrar")){
            System.out.println("Boton Borrar");
        } else if (e.getActionCommand().equals("Nuevo")){
            System.out.println("Boton Nuevo");
        }
    }
    
    private void RellenarTabla(){
        JTable tablatemp = vistaClientes.getTablaClientes();
        DefaultTableModel modelTable;
        ArrayList<Cliente> listaclientes = modeloClientes.readAllClientes();
        
        Object[] datosfila = new Object[3];
        
        modelTable = (DefaultTableModel) tablatemp.getModel();//tablaClientes.getModel();
        
        listaclientes.forEach(Cliente -> {
            datosfila[0] = Cliente.getId();
            datosfila[1] = Cliente.getNombre();
            datosfila[2] = Cliente.getNif();
            modelTable.addRow(datosfila);
        });
        
        tablatemp.setModel(modelTable);
        vistaClientes.setTablaClientes(tablatemp);
       //tablaClientes.setModel(modelTable);    
    }
    
    private void anadirDatosClientes(){
        
        Cliente clientenuevo = new Cliente();
        
        clientenuevo.setId(vistaClientes.getTxtId());
        clientenuevo.setNombre(vistaClientes.getTxtNombre());
        clientenuevo.setNif(vistaClientes.getTxtNif());
        clientenuevo.setDireccion(vistaClientes.getTxtDireccion());
        clientenuevo.setLocalidad(vistaClientes.getTxtLocalidad());
        clientenuevo.setCp(vistaClientes.getTxtCP());
        clientenuevo.setProvincia(vistaClientes.getTxtProvincia());
        clientenuevo.setTelefono(vistaClientes.getTxtTelefono());
        clientenuevo.setEmail(vistaClientes.getTxtEmail());
        clientenuevo.setObservaciones(vistaClientes.getTxtObservaciones());
        
        if (this.modeloClientes.add(clientenuevo)) {
            JOptionPane.showMessageDialog(vistaClientes,"El Cliente se ha añadido correctamente");
        }else {
            JOptionPane.showMessageDialog(vistaClientes,"Erro añadiendo un nuevo cliente");
        }
        
        
    }
}
