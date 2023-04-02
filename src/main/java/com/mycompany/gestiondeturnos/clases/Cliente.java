/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiondeturnos.clases;

/**
 *
 * @author PC
 */
public class Cliente {
    
        	private String dni;
	private String apellido;
	private String nombre;
	private String telefono;
	
	

//---------------------GETTERS---------------------------
	public String obtenerDni() {
		return dni;
	}
	public String obtenerNombre() {
		return nombre;
	}
    public String obtenerApellido() {
		return apellido;
	}
    public String obtenerTel() {
		return telefono;
	}
 //---------------------SETTERT----------------------------
    public void setDni(String dni) {
    	this.dni = dni;
    }
    public void setNombre(String name) {
    	this.nombre = name;
    }
    public void setApellido(String apellido) {
	   this.apellido = apellido;
    }
    public void setTelefono(String tel) {
	    this.telefono = tel;
     }
     public String getNombreCompleto(){
        return nombre +" "+ apellido;
    }
    
}
