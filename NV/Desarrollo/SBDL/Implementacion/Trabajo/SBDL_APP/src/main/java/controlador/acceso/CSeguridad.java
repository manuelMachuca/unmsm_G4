package controlador.acceso;

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import util.AplicacionUtil;
import util.seguridad.SeguridadParametros;

public class CSeguridad {
	private util.seguridad.Usuario usuario;
	private String palabraBuscada;
	
	public CSeguridad() {
		usuario = AplicacionUtil.obtenerUsuario();
	}

	public util.seguridad.Usuario getUsuario() {
		return usuario;
	}

	public String getPalabraBuscada() {
		return palabraBuscada;
	}

	public void setPalabraBuscada(String palabraBuscada) {
		this.palabraBuscada = palabraBuscada;
	}

	public void setUsuario(util.seguridad.Usuario usuario) {
		this.usuario = usuario;
	}

	public void cerrarSesion() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		SecurityContextHolder.clearContext();
		AplicacionUtil.redireccionar(SeguridadParametros.INICIAR_SESION);
	}
	public void irSearch() throws IOException{
		AplicacionUtil.redireccionar(SeguridadParametros.ACC_INICIO_PAGINA);
	}

}
