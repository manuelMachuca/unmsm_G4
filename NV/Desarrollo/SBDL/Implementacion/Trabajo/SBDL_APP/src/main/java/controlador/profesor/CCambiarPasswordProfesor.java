package controlador.profesor;

import java.io.IOException;
import java.io.Serializable;

import dao.entidad.Usuario;
import excepciones.NegocioExcepcion;
import modelo.UsuarioService;
import util.AplicacionUtil;
import util.seguridad.SeguridadParametros;

public class CCambiarPasswordProfesor implements Serializable {

	private UsuarioService usuarioService;
	private Usuario user;

	private String claveActual = "";
	private String claveNueva = "";
	private String claveConfirmacion = "";

	public CCambiarPasswordProfesor() {
		user = new Usuario();
		usuarioService = new UsuarioService();
		int personaId = AplicacionUtil.obtenerUsuario().getPersona().getIdpersona();
		user = usuarioService.obtenerUsuario(personaId);
		// inicializarUsuarioTemp();
	}

	// public void inicializarUsuarioTemp(){
	// userTemporal = new Usuario();
	// userTemporal.setPassword("");
	// }

	public boolean validarCoincidenciaClave() {
		if (claveNueva.equals(claveConfirmacion)) {
			return true;
		}
		return false;
	}

	public boolean validarClaveActual() {
		if (claveActual.equals(user.getPassword())) {
			return true;
		}
		return false;
	}

	public void actualizarPassword() {
		int severidad = 1;
		String titulo;
		String descripcion;

		if (validarClaveActual()) {
			if (validarCoincidenciaClave()) {

				try {
					user.setPassword(getClaveNueva());
					usuarioService.actualizarUsuario(user);
					titulo = "ÉXITO!";
					descripcion = "SE HAN ACTUALIZADO LA CONTRASEÑA DEL PROFESOR";
					AplicacionUtil.mostrarMensajeJSF(severidad, titulo, descripcion);
				} catch (NegocioExcepcion ne) {
					AplicacionUtil.mostrarMensajeJSF(3, "ERROR", "NO SE ACTUALIZO EL PROFESOR");
				}
			} else {
				AplicacionUtil.mostrarMensajeJSF(2, "ADVERTENCIA!", "LA CONTRASEÑAS NO COINCIDEN");
			}
		} else {
			AplicacionUtil.mostrarMensajeJSF(2, "ADVERTENCIA!", "LA CLAVE ACTUAL NO ES LA CORRECTA");
		}
	}
	
	public void redireccionar() throws IOException{
		  AplicacionUtil.redireccionar(SeguridadParametros.ACC_PASSWORD_PROFESOR);
		}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getClaveConfirmacion() {
		return claveConfirmacion;
	}

	public void setClaveConfirmacion(String claveConfirmacion) {
		this.claveConfirmacion = claveConfirmacion;
	}

	public String getClaveActual() {
		return claveActual;
	}

	public void setClaveActual(String claveActual) {
		this.claveActual = claveActual;
	}

	public String getClaveNueva() {
		return claveNueva;
	}

	public void setClaveNueva(String claveNueva) {
		this.claveNueva = claveNueva;
	}

}
