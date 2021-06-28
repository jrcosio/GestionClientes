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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jrblanco
 */
public class ControlClientes{
    
    /*
        Atributos, un objeto del modelo y de la vista
    */
    private ClientesDAO modeloClientes;
    private VistaPrincipal vistaClientes;
    
    public ControlClientes(ClientesDAO modelo, VistaPrincipal vista){
        this.modeloClientes = modelo;
        this.vistaClientes = vista;
                        
        /*
        *   Conectamos los botones con sus eventos.
        */       
        vistaClientes.getBtnAnadir().addActionListener((ActionEvent e) -> this.onBotonAnadir());
        vistaClientes.getBtnGuardar().addActionListener((ActionEvent e) -> this.onBotonGuardar());
        vistaClientes.getBtnBorrar().addActionListener((ActionEvent e) -> this.onBotonBorrar());
        vistaClientes.getBtnNuevo().addActionListener((ActionEvent e) -> this.onBotonNuevo());
        
        vistaClientes.getBtnSalir().addActionListener((ActionEvent e) -> {
            System.out.println("Adios....");
            System.exit(0);
            
        });
               
        vistaClientes.getTablaClientes().addMouseListener((new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                onTabla();
            }
        }));
                
        vistaClientes.setTitle("::: Gestion Java 0.1 ::: Por JR Blanco :::");
        vistaClientes.setLocationRelativeTo(null);
        vistaClientes.setVisible(true); //Mostrar ventana
        
        
        //Carga los datos de la BD en la Tabla
        this.CargarDatosTabla();
    }
    
    /*
    *   Método del botón Añadir: recoge los datos de los text y los guarda en la DB
    */
    private void onBotonAnadir(){
        Cliente clientenuevo = new Cliente();
        /*
        * Comprobamos que en Nombre y NIF hay datos, ya que son campos obligatorios
        */
        if (!vistaClientes.getTxtNombre().getText().equals("") && !vistaClientes.getTxtNIF().getText().equals("")){
            clientenuevo.setNombre(vistaClientes.getTxtNombre().getText());
            clientenuevo.setNif(vistaClientes.getTxtNIF().getText());
            clientenuevo.setDireccion(vistaClientes.getTxtDireccion().getText());
            clientenuevo.setLocalidad(vistaClientes.getTxtLocalidad().getText());
            clientenuevo.setCp(vistaClientes.getTxtCP().getText());
            clientenuevo.setProvincia(vistaClientes.getTxtProvincia().getText());
            clientenuevo.setTelefono(vistaClientes.getTxtTelefono().getText());
            clientenuevo.setEmail(vistaClientes.getTxtEmail().getText());
            clientenuevo.setObservaciones(vistaClientes.getTxtObservaciones().getText());

            if (this.modeloClientes.add(clientenuevo)) {
                JOptionPane.showMessageDialog(vistaClientes,"El Cliente se ha añadido correctamente");
                this.limpiarLosTxts();
                this.CargarDatosTabla();
            }else {
                JOptionPane.showMessageDialog(vistaClientes,"Error añadiendo un nuevo cliente");
            }
        } else {
            JOptionPane.showMessageDialog(vistaClientes,"Los campos Nombre y NIF son obligatorios");
        }
    }
    
    private  void onBotonGuardar(){
        Cliente clienteActual = new Cliente();
       if (!vistaClientes.getTxtNombre().getText().equals("") && !vistaClientes.getTxtNIF().getText().equals("")){
            clienteActual.setId(Integer.parseInt(vistaClientes.getTxtId().getText()));
            clienteActual.setNombre(vistaClientes.getTxtNombre().getText());
            clienteActual.setNif(vistaClientes.getTxtNIF().getText());
            clienteActual.setDireccion(vistaClientes.getTxtDireccion().getText());
            clienteActual.setLocalidad(vistaClientes.getTxtLocalidad().getText());
            clienteActual.setCp(vistaClientes.getTxtCP().getText());
            clienteActual.setProvincia(vistaClientes.getTxtProvincia().getText());
            clienteActual.setTelefono(vistaClientes.getTxtTelefono().getText());
            clienteActual.setEmail(vistaClientes.getTxtEmail().getText());
            clienteActual.setObservaciones(vistaClientes.getTxtObservaciones().getText());

            if (this.modeloClientes.update(clienteActual)) {
                JOptionPane.showMessageDialog(vistaClientes,"El Cliente se ha actualizado correctamente");
                this.CargarDatosTabla();
            }else {
                JOptionPane.showMessageDialog(vistaClientes,"Error actualizando el cliente");
            }
        } else {
            JOptionPane.showMessageDialog(vistaClientes,"Los campos Nombre y NIF son obligatorios");
        }
        
        
    }
    
    private void onBotonBorrar(){
        if (!(vistaClientes.getTxtId().getText().equals(""))) {
            if (modeloClientes.delete(Integer.parseInt(vistaClientes.getTxtId().getText()))) {
                JOptionPane.showMessageDialog(vistaClientes, "Cliente Borrado");
            }else {
                JOptionPane.showMessageDialog(vistaClientes, "No ha sido posible borrar el cliente");
            }
            this.limpiarLosTxts();
            this.CargarDatosTabla();
        }
    }
    
    private void onBotonNuevo(){
        this.limpiarLosTxts();
        
    }
    
    private void onTabla(){
        int fila = vistaClientes.getTablaClientes().getSelectedRow();
        
        int ID = Integer.parseInt((String)vistaClientes.getTablaClientes().getValueAt(fila, 0).toString());
        
        System.out.println(ID);
        Cliente cliente = modeloClientes.readCliente(ID);
        
        vistaClientes.getTxtId().setText(""+cliente.getId());
        vistaClientes.getTxtNombre().setText(cliente.getNombre());
        vistaClientes.getTxtNIF().setText(cliente.getNif());
        vistaClientes.getTxtDireccion().setText(cliente.getDireccion());
        vistaClientes.getTxtLocalidad().setText(cliente.getLocalidad());
        vistaClientes.getTxtCP().setText(cliente.getCp());
        vistaClientes.getTxtProvincia().setText(cliente.getProvincia());
        vistaClientes.getTxtTelefono().setText(cliente.getTelefono());
        vistaClientes.getTxtEmail().setText(cliente.getEmail());
        vistaClientes.getTxtObservaciones().setText(cliente.getObservaciones());
        
    }
        
    /*
    *   Método que carga en la Tabla todos los clientes
    */
    private void CargarDatosTabla(){
        /*
        *   Crear una variable con el modelo de la tabla y le iniciamos con la tabla de la vista llamado al getter de esta
        */
        DefaultTableModel modelTable = (DefaultTableModel) vistaClientes.getTablaClientes().getModel(); //Obtenemos el Model del JTable de la vista
        
        //Leer llamando al modelo de la DB todos los criente y lo almacena en un arraylist.
        ArrayList<Cliente> listaclientes = modeloClientes.readAllClientes();
        
        Object[] datosfila = new Object[3];
        
        modelTable.setNumRows(0); //reinicia a cero las filas de la tabla
                
        /*
        *   Se recorre el Arralist que contiene todos los clientes
        */
        listaclientes.forEach(Cliente -> { 
            datosfila[0] = Cliente.getId();
            datosfila[1] = Cliente.getNombre();
            datosfila[2] = Cliente.getNif();
            modelTable.addRow(datosfila);
        }); 
    } 
     
    /*
    *   Método
    */
    private void limpiarLosTxts(){
        vistaClientes.getTxtId().setText("");
        vistaClientes.getTxtNombre().setText("");
        vistaClientes.getTxtNIF().setText("");
        vistaClientes.getTxtDireccion().setText("");
        vistaClientes.getTxtLocalidad().setText("");
        vistaClientes.getTxtCP().setText("");
        vistaClientes.getTxtProvincia().setText("");
        vistaClientes.getTxtTelefono().setText("");
        vistaClientes.getTxtEmail().setText("");
        vistaClientes.getTxtObservaciones().setText("");
    }
}
