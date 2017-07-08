package dao.entidad;
// Generated 27/09/2016 02:04:24 PM by Hibernate Tools 4.3.1



/**
 * InformaciondetalleId generated by hbm2java
 */
public class InformaciondetalleId  implements java.io.Serializable {


     private int idcurso;
     private int iddocente;
     private int idalumno;

    public InformaciondetalleId() {
    }

    public InformaciondetalleId(int idcurso, int idalumno,int iddocente) {
       this.idcurso = idcurso;
       this.idalumno = idalumno;
       this.iddocente = iddocente;
    }
   
    public int getIdcurso() {
        return this.idcurso;
    }
    
    public void setIdcurso(int idcurso) {
        this.idcurso = idcurso;
    }
    public int getIdalumno() {
        return this.idalumno;
    }
    
    public void setIdalumno(int idalumno) {
        this.idalumno = idalumno;
    }

   public int getIddocente() {
		return iddocente;
	}

	public void setIddocente(int iddocente) {
		this.iddocente = iddocente;
	}

public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InformaciondetalleId) ) return false;
		 InformaciondetalleId castOther = ( InformaciondetalleId ) other; 
         
		 return (this.getIdcurso()==castOther.getIdcurso())
				 && (this.getIdalumno()==castOther.getIdalumno())
				 && (this.getIddocente()==castOther.getIddocente());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdcurso();
         result = 37 * result + this.getIdalumno();
         result = 37 * result + this.getIddocente();
         
         return result;
   }

	@Override
	public String toString() {
		return "InformaciondetalleId [idcurso=" + idcurso + ", iddocente=" + iddocente + ", idalumno=" + idalumno + "]";
	}


}


