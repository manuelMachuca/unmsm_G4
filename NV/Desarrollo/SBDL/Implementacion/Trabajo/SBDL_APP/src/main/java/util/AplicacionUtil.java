package util;



import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import dao.entidad.Usuario;


public class AplicacionUtil {

	/**
	 * Este metodo publica un mensaje en la vista JSF
	 * 
	 * @param severidad
	 *            : INFO=1, WARN=2, ERROR=3, FATAL=4.
	 * @param titulo
	 *            : nombre del mensaje.
	 * @param descripcion
	 *            : cuerpo del mensaje.
	 */
		public static void mostrarMensajeJSF(int severidad, String titulo, String descripcion) {
		FacesMessage facesMessage = null;
		switch (severidad) {
		case 1:
			facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo,
					descripcion);
			break;
		case 2:
			facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo,
					descripcion);
			break;
		case 3:
			facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					titulo, descripcion);
			break;
		case 4:
			facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					titulo, descripcion);
			break;
		default:
			break;
		}
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}


	public static void ejecutar(String sentencia) {
		RequestContext.getCurrentInstance().execute(sentencia);
	}
	public static void redireccionar(String pagina) throws IOException {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
		pagina = (pagina.startsWith("/")?pagina: "/"+pagina);
		ctx.redirect(ctxPath + pagina); 
	}
	
	public static boolean estaLogeado() {

        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
        	System.out.println("es truee");
            return true;
        }
        return false;
    }
	

	public static util.seguridad.Usuario obtenerUsuario() {
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			return (util.seguridad.Usuario) SecurityContextHolder.getContext().getAuthentication().getDetails();
		} else {
			return null;
		}
	}
	public static void actualizarPagina() throws IOException{
		
		 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		 ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		 
	}
}