package dao.entidad;
// Generated 27/09/2016 02:04:24 PM by Hibernate Tools 4.3.1



/**
 * CursoFavoritoId generated by hbm2java
 */
public class ValorCursoXDocenteId  implements java.io.Serializable {


     private int idvalorAluCur;
     private int idalumno;
     private int idcurDoc;
     private int idcurso;
     private int iddocente;

    public ValorCursoXDocenteId() {
    }

    public ValorCursoXDocenteId(int idvalorAluCur, int idalumno, int idcurDoc, int idcurso, int iddocente) {
       this.idvalorAluCur = idvalorAluCur;
       this.idalumno = idalumno;
       this.idcurDoc = idcurDoc;
       this.idcurso = idcurso;
       this.iddocente = iddocente;
    }
   

    public int getIdvalorAluCur() {
		return idvalorAluCur;
	}

	public void setIdvalorAluCur(int idvalorAluCur) {
		this.idvalorAluCur = idvalorAluCur;
	}

	public int getIdalumno() {
        return this.idalumno;
    }
    
    public void setIdalumno(int idalumno) {
        this.idalumno = idalumno;
    }
    public int getIdcurDoc() {
        return this.idcurDoc;
    }
    
    public void setIdcurDoc(int idcurDoc) {
        this.idcurDoc = idcurDoc;
    }
    public int getIdcurso() {
        return this.idcurso;
    }
    
    public void setIdcurso(int idcurso) {
        this.idcurso = idcurso;
    }
    public int getIddocente() {
        return this.iddocente;
    }
    
    public void setIddocente(int iddocente) {
        this.iddocente = iddocente;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ValorCursoXDocenteId) ) return false;
		 ValorCursoXDocenteId castOther = ( ValorCursoXDocenteId ) other; 
         
		 return (this.getIdvalorAluCur()==castOther.getIdvalorAluCur())
 && (this.getIdalumno()==castOther.getIdalumno())
 && (this.getIdcurDoc()==castOther.getIdcurDoc())
 && (this.getIdcurso()==castOther.getIdcurso())
 && (this.getIddocente()==castOther.getIddocente());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdvalorAluCur();
         result = 37 * result + this.getIdalumno();
         result = 37 * result + this.getIdcurDoc();
         result = 37 * result + this.getIdcurso();
         result = 37 * result + this.getIddocente();
         return result;
   }

	@Override
	public String toString() {
		return "ValorCursoXDocenteId [idvalorAluCur=" + idvalorAluCur + ", idalumno=" + idalumno + ", idcurDoc=" + idcurDoc
				+ ", idcurso=" + idcurso + ", iddocente=" + iddocente + "]";
	}

}


