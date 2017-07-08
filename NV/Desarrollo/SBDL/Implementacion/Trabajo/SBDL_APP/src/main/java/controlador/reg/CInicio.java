/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.reg;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import dao.entidad.Alumno;
import dao.entidad.CursoXDocente;
import dao.entidad.CursoXDocenteId;
import dao.entidad.Informaciondetalle;
import dao.entidad.InformaciondetalleId;
import modelo.AlumnoService;
import modelo.SCursoXDocente;
import modelo.alumno.SInformacionDetalle;
import util.AplicacionUtil;
import util.seguridad.SeguridadParametros;

/**
 *
 * @author Manuel Machuca
 */

public class CInicio implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<CursoXDocente> listaCurso_Profesor;
	public ArrayList<CursoXDocente> listaCurso_Profesor_Paginacion;
    public SCursoXDocente sCursoXDocente;
    public SInformacionDetalle sInformacionDetalle;
    
    public CursoXDocente cursoXDocente;
    
    private String palabraBuscada;
    
    //Inicializando valores 
    Integer paginacion = 1;
    Integer contador = 0;
    Integer tamPagina = 5;
    Integer cantidadMostrada = 0;
    
    public boolean atras;
    public boolean adelante;
    
    public CInicio(){
    }
    
    public void noLogeado(){
    	AplicacionUtil.mostrarMensajeJSF(1, "", "Inicie sesión para calificar!");
    }
    
    public void irSearch(String value) throws IOException{
    	    	
    	paginacion = 1;
        contador = 0;
        tamPagina = 5;
        cantidadMostrada = 0;
        
        this.palabraBuscada = value;
    	
        sCursoXDocente = new SCursoXDocente();
    	listaCurso_Profesor = new ArrayList<>();    	
    	listaCurso_Profesor = sCursoXDocente.listarCurso_Profesor(value);
    	
    	//Inicializando
    	setAtras(false);
    	if(listaCurso_Profesor.size()> tamPagina )
    		setAdelante(true);
    	else
    		setAdelante(false);
    	
    	
    	mostrarLista();
    	
    	AplicacionUtil.redireccionar(SeguridadParametros.ACC_INICIO_PAGINA);
    }
    

	public void irSearchProfe(String value) throws IOException {

		paginacion = 1;
		contador = 0;
		tamPagina = 5;
		cantidadMostrada = 0;

		sCursoXDocente = new SCursoXDocente();
		listaCurso_Profesor = new ArrayList<>();
		listaCurso_Profesor = sCursoXDocente.listarCurso_Profesor(value);

		setAtras(false);
		setAdelante(true);

		mostrarLista();

		AplicacionUtil.redireccionar(SeguridadParametros.ACC_PAGINA_PROFESOR);
	}
	
	public void irSearchInvitado(String value) throws IOException {

		paginacion = 1;
		contador = 0;
		tamPagina = 5;
		cantidadMostrada = 0;

		sCursoXDocente = new SCursoXDocente();
		listaCurso_Profesor = new ArrayList<>();
		listaCurso_Profesor = sCursoXDocente.listarCurso_Profesor(value);

		setAtras(false);
		setAdelante(true);

		mostrarLista();

		AplicacionUtil.redireccionar(SeguridadParametros.SEACR_INIT_INVITADO);
	}
	
    public void mostrarLista(){
    	//Lista para paginacion por bloques
    	
    	
    	listaCurso_Profesor_Paginacion = new ArrayList<>();
    	
    	for (int i = contador ; i < listaCurso_Profesor.size() ;i++) {
    		if(contador < tamPagina * paginacion){
    			listaCurso_Profesor_Paginacion.add(listaCurso_Profesor.get(i));
    			contador++;
    			//que me mostro ultimamente para poder restar y regresar ala pagina anterior
    			cantidadMostrada++;
    		}
		}
    }

    public void adelante(){
    	
    	//Lista para paginacion por bloques
    	paginacion ++;
    	//ultimo valor que se mostro para poder restar cuando usamos regresar()
    	cantidadMostrada = 0;
    	mostrarLista();
    	
    	if(paginacion > 1){
    		setAtras(true);
    	}
    	if(contador  == listaCurso_Profesor.size()){
    		setAdelante(false);
    	}
    	
    }
    public void regresar(){
    	
    	paginacion --;
    	//con esto se desde donde comienza a buscar
    	contador = contador - (tamPagina + cantidadMostrada);
    	System.out.println("contador es: " +contador);
    	cantidadMostrada = 0;
    	mostrarLista();
    	
    	if(paginacion == 1){
    		setAtras(false);
    		setAdelante(true);
    	}   	
    }
	public ArrayList<CursoXDocente> getListaCurso_Profesor() {
		return listaCurso_Profesor;
	}


	public void setListaCurso_Profesor(ArrayList<CursoXDocente> listaCurso_Profesor) {
		this.listaCurso_Profesor = listaCurso_Profesor;
	}
	/**
	 * Este metodo sirve para poder ver los detalle del Curso de un Docente Seleccionado
	 */
	public void verDetalles(CursoXDocente cursoXDocente){
		
		this.cursoXDocente = cursoXDocente;
		Informaciondetalle infoDetalle = new Informaciondetalle();
		sInformacionDetalle = new SInformacionDetalle();
		
		Integer idPersona = AplicacionUtil.obtenerUsuario().getPersona().getIdpersona();
		AlumnoService alu = new AlumnoService();
		Alumno alumno = alu.buscarAlumno(idPersona);
		
		if(alumno != null){
			InformaciondetalleId id = new InformaciondetalleId();
			id.setIdcurso(cursoXDocente.getCurso().getIdcurso());
			id.setIddocente(cursoXDocente.getDocente().getIddocente());
			id.setIdalumno(alumno.getIdalumno());
			
			infoDetalle.setId(id);
			infoDetalle.setCurso(cursoXDocente.getCurso());
			infoDetalle.setDocente(cursoXDocente.getDocente());
			infoDetalle.setAlumno(alumno);
			infoDetalle.setInfoDetalle("PRUEB");
			infoDetalle.setFecha(new Date());
			
			if(sInformacionDetalle.noExisteRegistrado(id))
				sInformacionDetalle.insertarInformacionDetalle(infoDetalle);
		}
		
		AplicacionUtil.ejecutar("PF('wizardCurProfe').next()");
		
	}
	
	
	
	public void verDetallesAlumInv(CursoXDocente cursoXDocente){
		this.cursoXDocente = cursoXDocente;
		AplicacionUtil.ejecutar("PF('wizardCurProfe').next()");
	}

	public CursoXDocente getCursoXDocente() {
		return cursoXDocente;
	}


	public void setCursoXDocente(CursoXDocente cursoXDocente) {
		this.cursoXDocente = cursoXDocente;
	}

	public ArrayList<CursoXDocente> getListaCurso_Profesor_Paginacion() {
		return listaCurso_Profesor_Paginacion;
	}

	public void setListaCurso_Profesor_Paginacion(ArrayList<CursoXDocente> listaCurso_Profesor_Paginacion) {
		this.listaCurso_Profesor_Paginacion = listaCurso_Profesor_Paginacion;
	}

	public boolean isAtras() {
		return atras;
	}

	public void setAtras(boolean atras) {
		this.atras = atras;
	}

	public boolean isAdelante() {
		return adelante;
	}

	public void setAdelante(boolean adelante) {
		this.adelante = adelante;
	}

	public String getPalabraBuscada() {
		return palabraBuscada;
	}

	public void setPalabraBuscada(String palabraBuscada) {
		this.palabraBuscada = palabraBuscada;
	}

   
}
