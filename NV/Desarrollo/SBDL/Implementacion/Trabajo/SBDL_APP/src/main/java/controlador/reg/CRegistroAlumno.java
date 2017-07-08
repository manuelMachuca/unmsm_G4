/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.reg;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;

import dao.entidad.Persona;
import dao.entidad.Usuario;
import excepciones.NegocioExcepcion;
import modelo.AlumnoService;
import modelo.PersonaService;
import util.AplicacionUtil;
import util.Constantes;

/**
 *
 * @author Miauuu
 */

public class CRegistroAlumno implements Serializable{
    

    public AlumnoService alumnoService;
    public PersonaService personaService;
    public Persona persona;
    public Usuario usuario;
    
    public String direccion;
     public Date fechaNac;
     public String nombres;
     public String apellidos;
     public Integer telefono;
     public String correoelectronico;
    
     public String password;
    
    
    public CRegistroAlumno(){
        persona = new Persona();
        usuario = new Usuario();
        alumnoService = new AlumnoService();
        personaService = new PersonaService();
        direccion="";
        fechaNac = null;
        nombres = "";
        apellidos = "";
        telefono = null;
        correoelectronico = "";
    }
    
    
    public void registrarAlumno(){
        
        try{
	    	if(personaService.correoNoExiste(correoelectronico)){
	        	
	    		persona.setDireccion(direccion);
		        persona.setFechaNac(fechaNac);
		        persona.setCorreoelectronico(correoelectronico);
		        persona.setNombres(nombres);
		        persona.setApellidos(apellidos);
//		        persona.setUbigeo(ubigeo);
		        persona.setTelefono(telefono);
		        
		        usuario.setPassword(password);
		
		        alumnoService.registrarAlumno(persona,usuario);
		        limpiarCampos();
		        AplicacionUtil.mostrarMensajeJSF(Constantes.SEVERIDAD_INFO, "Bienvenido Alumno! ha sido registrado.", "REGISTRADO CORRECTAMENTE");
	        }else{
	        	AplicacionUtil.mostrarMensajeJSF(Constantes.SEVERIDAD_WARNING, "CORREO EXISTENTE", "El correo ya se encuentra registrado");
	        }
	        
        }catch(NegocioExcepcion ne){
        	AplicacionUtil.mostrarMensajeJSF(3, "ERROR!", "Error al registrar, " + ne.getMessage());
        }
        
    }
    public void limpiarCampos(){
        direccion="";
        fechaNac = null;
        nombres = "";
        apellidos = "";
        telefono = null;
        correoelectronico = "";	
    }
	public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

	public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
