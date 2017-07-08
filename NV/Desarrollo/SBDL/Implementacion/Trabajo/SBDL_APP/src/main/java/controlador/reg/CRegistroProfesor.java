/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.reg;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;

import dao.entidad.NivelEstudio;
import dao.entidad.Persona;
import dao.entidad.Usuario;
import excepciones.NegocioExcepcion;
import modelo.DocenteService;
import modelo.PersonaService;
import util.AplicacionUtil;
import util.Constantes;

/**
 *
 * @author Miauuu
 */

public class CRegistroProfesor implements Serializable{
    

    public DocenteService docenteService;
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
     
    
    public CRegistroProfesor(){
        persona = new Persona();
        usuario = new Usuario();
        docenteService = new DocenteService();
        personaService = new PersonaService();
        direccion="";
        fechaNac = null;
        nombres = "";
        apellidos = "";
        telefono = null;
        correoelectronico = "";
    }
    
    
    public void registrarDocente(){
    	 //setear persona
        try{
        	if(personaService.correoNoExiste(correoelectronico)){
	        	persona.setDireccion(direccion);
	        	persona.setFechaNac(fechaNac);
	        	persona.setCorreoelectronico(correoelectronico);
	        	persona.setNombres(nombres);
	        	persona.setApellidos(apellidos);
	        	persona.setTelefono(telefono);
	        	//setear usuario
	        	usuario.setPassword(password);
	
	        	docenteService.registrarDocente(persona,usuario);
	        	limpiarCampos();
	        	AplicacionUtil.mostrarMensajeJSF(Constantes.SEVERIDAD_INFO, "Bienvenido Profesor! ha sido registrado.", "REGISTRADO CORRECTAMENTE");
        	}else{
        		AplicacionUtil.mostrarMensajeJSF(Constantes.SEVERIDAD_WARNING, "CORREO EXISTENTE", "El correo ya se encuentra registrado");
        	}
        }catch(NegocioExcepcion ne){
        	AplicacionUtil.mostrarMensajeJSF(Constantes.SEVERIDAD_ERROR, "ERROR", "REGISTRADO ERRONEAMENTE, " +ne.getMessage());
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
