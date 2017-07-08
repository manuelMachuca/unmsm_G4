/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.alumno;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import dao.entidad.CursoXDocente;
import dao.entidad.CursoXDocenteId;
import modelo.SCursoXDocente;
import util.AplicacionUtil;

/**
 *
 * @author Manuel Machuca
 */

public class CBusquedaAlumno implements Serializable{
    

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<CursoXDocente> listaCurso_Profesor;
    public SCursoXDocente sCursoXDocente;
    
    public CursoXDocente cursoXDocente;
    
    public CBusquedaAlumno(){
    	
    	sCursoXDocente = new SCursoXDocente();
    	
    	listaCurso_Profesor = new ArrayList<>();
//    	listaCurso_Profesor = sCursoXDocente.listarCurso_Profesor();

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
		
		AplicacionUtil.ejecutar("PF('wizardCurProfe').next()");
		
	}


	public CursoXDocente getCursoXDocente() {
		return cursoXDocente;
	}


	public void setCursoXDocente(CursoXDocente cursoXDocente) {
		this.cursoXDocente = cursoXDocente;
	}

   
}
