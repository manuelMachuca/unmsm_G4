/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import dao.entidad.Persona;
import modelo.PersonaService;

public class RegistroAlumno{

    public PersonaService personaService;
    public Persona persona;
    public String usuario;
    public String texto = "oli";
    
   public RegistroAlumno(){
       usuario = "";
       persona = new Persona();
       personaService = new PersonaService();
   }

   
   public void registrar(){
        persona.setDireccion(usuario);
//        personaService.registrarPersona(persona);
   }
   
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    
    
 
    
   
    
}
