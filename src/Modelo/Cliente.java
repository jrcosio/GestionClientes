/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author jrblanco
 */
public class Cliente {
    
    private int id;
    private String nombre;
    private String nif;
    private String direccion;
    private String localidad;
    private String cp;
    private String provincia;
    private String telefono;
    private String email;
    private String observaciones;

    public Cliente() {
    }

    public Cliente(int id, String nombre, String nif, String direccion, String localidad, String cp, 
                   String provincia, String telefono, String email, String observaciones) {
        this.id = id;
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        this.localidad = localidad;
        this.cp = cp;
        this.provincia = provincia;
        this.telefono = telefono;
        this.email = email;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }    
    
}
