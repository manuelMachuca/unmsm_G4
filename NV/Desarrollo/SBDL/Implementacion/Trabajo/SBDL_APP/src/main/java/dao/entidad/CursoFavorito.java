package dao.entidad;
// Generated 27/09/2016 02:04:24 PM by Hibernate Tools 4.3.1



/**
 * CursoFavorito generated by hbm2java
 */
public class CursoFavorito  implements java.io.Serializable {


     private CursoFavoritoId id;
     private Alumno alumno;
     private CursoXDocente cursoXDocente;

    public CursoFavorito() {
    }

    public CursoFavorito(CursoFavoritoId id, Alumno alumno, CursoXDocente cursoXDocente) {
       this.id = id;
       this.alumno = alumno;
       this.cursoXDocente = cursoXDocente;
    }
   
    public CursoFavoritoId getId() {
        return this.id;
    }
    
    public void setId(CursoFavoritoId id) {
        this.id = id;
    }
    public Alumno getAlumno() {
        return this.alumno;
    }
    
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    public CursoXDocente getCursoXDocente() {
        return this.cursoXDocente;
    }
    
    public void setCursoXDocente(CursoXDocente cursoXDocente) {
        this.cursoXDocente = cursoXDocente;
    }

	@Override
	public String toString() {
		return "CursoFavorito [id=" + id + ", alumno=" + alumno + ", cursoXDocente=" + cursoXDocente + "]";
	}

}


