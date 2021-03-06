package dao.entidad;
// Generated 27/09/2016 02:04:24 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
* Ubigeo generated by hbm2java
*/
public class Ubigeo  implements java.io.Serializable {


  private Integer idubigeo;
  private String distrito;
  private String provincia;
  private String region;
  private String pais;
  private Set personas = new HashSet(0);

 public Ubigeo() {
 }

 public Ubigeo(String distrito, String provincia, String region, String pais, Set personas) {
    this.distrito = distrito;
    this.provincia = provincia;
    this.region = region;
    this.pais = pais;
    this.personas = personas;
 }

 public Integer getIdubigeo() {
     return this.idubigeo;
 }
 
 public void setIdubigeo(Integer idubigeo) {
     this.idubigeo = idubigeo;
 }
 public String getDistrito() {
     return this.distrito;
 }
 
 public void setDistrito(String distrito) {
     this.distrito = distrito;
 }
 public String getProvincia() {
     return this.provincia;
 }
 
 public void setProvincia(String provincia) {
     this.provincia = provincia;
 }
 public String getRegion() {
     return this.region;
 }
 
 public void setRegion(String region) {
     this.region = region;
 }
 public String getPais() {
     return this.pais;
 }
 
 public void setPais(String pais) {
     this.pais = pais;
 }
 public Set getPersonas() {
     return this.personas;
 }
 
 public void setPersonas(Set personas) {
     this.personas = personas;
 }
	
 @Override
	public String toString() {
		return "Ubigeo [idubigeo=" + idubigeo + ", distrito=" + distrito + ", provincia=" + provincia + ", region="
				+ region + ",pais " + pais + "]";
	}

}


