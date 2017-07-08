package dao.entidad;
// Generated 27/09/2016 02:04:24 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Nivel generated by hbm2java
 */
public class Nivel  implements java.io.Serializable {


     private Integer idnivel;
     private String nombrenivel;
     private Set cursos = new HashSet(0);

    public Nivel() {
    }

    public Nivel(String nombrenivel, Set cursos) {
       this.nombrenivel = nombrenivel;
       this.cursos = cursos;
    }
   
    public Integer getIdnivel() {
        return this.idnivel;
    }
    
    public void setIdnivel(Integer idnivel) {
        this.idnivel = idnivel;
    }
    public String getNombrenivel() {
        return this.nombrenivel;
    }
    
    public void setNombrenivel(String nombrenivel) {
        this.nombrenivel = nombrenivel;
    }
    public Set getCursos() {
        return this.cursos;
    }
    
    public void setCursos(Set cursos) {
        this.cursos = cursos;
    }

	@Override
	public String toString() {
		return "Nivel [idnivel=" + idnivel + ", nombrenivel=" + nombrenivel + "]";
	}
}


