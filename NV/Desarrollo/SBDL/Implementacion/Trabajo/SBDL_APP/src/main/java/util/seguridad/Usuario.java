package util.seguridad;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import dao.entidad.Perfil;
import dao.entidad.Persona;

public class Usuario extends User{
	
	
	private static final long serialVersionUID = 1L;
	
	private String codigoEmpleado;
	private String codigoArea;
	private String nomPerfil;
	private String apPaterno;
	private String apMaterno;
	private String nombres;
	private String dni;
	/*Perfil*/
	private String perfil;
	private Integer idUsuario;
	private Persona persona;
	
	public Usuario(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	public Usuario(String username, String password, Collection<? extends GrantedAuthority> authorities,
			String nomPerfil,Integer idUsuario,Persona persona) {
		super(username, password, authorities);
		this.nomPerfil = nomPerfil;
		this.perfil = perfil;
		this.idUsuario = idUsuario;
		this.persona = persona;
	}

	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	public String getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	public String getNomPerfil() {
		return nomPerfil;
	}

	public void setNomPerfil(String nomPerfil) {
		this.nomPerfil = nomPerfil;
	}

	public String getApPaterno() {
		return apPaterno;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	public String getApMaterno() {
		return apMaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	@Override
	public String toString() {
		return "UsuarioMFTP [codigoEmpleado=" + codigoEmpleado + ", codigoArea=" + codigoArea + ", nomPerfil="
				+ nomPerfil + ", apPaterno=" + apPaterno + ", apMaterno=" + apMaterno + ", nombres=" + nombres
				+ ", dni=" + dni + "]";
	}

	
	
}
