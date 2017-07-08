package dao.entidad;
// Generated 27/09/2016 02:04:24 PM by Hibernate Tools 4.3.1


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * CursoXDocente generated by hbm2java
 */
public class CursoXDocente  implements java.io.Serializable {


     private CursoXDocenteId id;
     private Curso curso;
     private Docente docente;
     private Integer valoraciones;
     private Set comentarios = new HashSet(0);
     private Set cursoFavoritos = new HashSet(0);
     
     //Para el promedio del curso
     private double promedioValoracion;
     private ArrayList<Integer> select;
     private ArrayList<Integer> noSelect;

    public CursoXDocente() {
    }

	
    public CursoXDocente(CursoXDocenteId id, Curso curso, Docente docente) {
        this.id = id;
        this.curso = curso;
        this.docente = docente;
    }
    public CursoXDocente(CursoXDocenteId id, Curso curso, Docente docente, Set comentarios, Set cursoFavoritos) {
       this.id = id;
       this.curso = curso;
       this.docente = docente;
       this.comentarios = comentarios;
       this.cursoFavoritos = cursoFavoritos;
    }
   
    public CursoXDocenteId getId() {
        return this.id;
    }
    
    public void setId(CursoXDocenteId id) {
        this.id = id;
    }
    public Curso getCurso() {
        return this.curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public Docente getDocente() {
        return this.docente;
    }
    
    public void setDocente(Docente docente) {
        this.docente = docente;
    }
    public Set getComentarios() {
        return this.comentarios;
    }
    
    public void setComentarios(Set comentarios) {
        this.comentarios = comentarios;
    }
    public Set getCursoFavoritos() {
        return this.cursoFavoritos;
    }
    
    public void setCursoFavoritos(Set cursoFavoritos) {
        this.cursoFavoritos = cursoFavoritos;
    }

	public Integer getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(Integer valoraciones) {
		this.valoraciones = valoraciones;
	}


	public double getPromedioValoracion() {
		return promedioValoracion;
	}


	public void setPromedioValoracion(double promedioValoracion) {
		this.promedioValoracion = promedioValoracion;
	}


	public ArrayList<Integer> getSelect() {
		return select;
	}


	public void setSelect(ArrayList<Integer> select) {
		this.select = select;
	}


	public ArrayList<Integer> getNoSelect() {
		return noSelect;
	}


	public void setNoSelect(ArrayList<Integer> noSelect) {
		this.noSelect = noSelect;
	}


	@Override
	public String toString() {
		return "CursoXDocente [id=" + id + ", curso=" + curso + ", docente=" + docente +",valoraciones=" + valoraciones 
				+ ", comentarios=" + comentarios + "]";
	}

}


