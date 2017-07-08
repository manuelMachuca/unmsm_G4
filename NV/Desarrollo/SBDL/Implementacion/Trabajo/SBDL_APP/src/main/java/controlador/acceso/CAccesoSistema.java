package controlador.acceso;

import static util.seguridad.SeguridadParametros.LOGIN_PASSWORD;
import static util.seguridad.SeguridadParametros.LOGIN_USERNAME;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import dao.entidad.Usuario;
import modelo.UsuarioService;
import modelo.seguridad.SAccesoSistema;
import util.AplicacionUtil;
import util.seguridad.SeguridadParametros;


public class CAccesoSistema implements Serializable{

	private static final long serialVersionUID = 1L;

	private String idUsuario = LOGIN_USERNAME;
	
	private String idPassword = LOGIN_PASSWORD;
	
	public SAccesoSistema sAccesoSistema = new SAccesoSistema();
	public UsuarioService usuarioService = new UsuarioService();
	private String usuario;
	private String password;
	
	
	public CAccesoSistema() throws IOException{
		redireccionarSiLogeado();
	}
	
	@PostConstruct
	public void init(){
				
	}
	
	//PARA LOGEARSE AL REGISTRAR
	public void darUsuario(String usuario){
		this.usuario = usuario;
	}
	
	public void darPassword(String password){
		this.password = password;
	}
	
	public void redireccionarSiLogeado() throws IOException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Usuario user = usuarioService.obtenerUsuario(AplicacionUtil.obtenerUsuario().getPersona().getIdpersona());
			if(user.getPerfil().getIdperfil().equals(1))
			 AplicacionUtil.redireccionar(SeguridadParametros.ACC_PAGINA_PROFESOR);
			if(user.getPerfil().getIdperfil().equals(2))
			AplicacionUtil.redireccionar(SeguridadParametros.INICIO_PAGINA_ALUMNO);
		}
	}

	public String doLogin() throws ServletException, IOException{
		
		sAccesoSistema.autenticacion(FacesContext.getCurrentInstance());
		return null;
	}
	@Deprecated
	public void doLogin(ActionEvent ae){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		
		String username = this.usuario;
		String password = this.password;
		
		
		try{
			sAccesoSistema.autenticacion(username, password, request, response);			
		}catch(Exception e){
			e.printStackTrace();
		}
	}


//	@Deprecated
//	public String login(){
//		try{
//			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//			HttpServletRequest httpServletRequest = (HttpServletRequest) externalContext.getRequest();
//			HttpServletResponse httpServletResponse = (HttpServletResponse) externalContext.getResponse();
//			boolean autentico;
//			
//			autentico = sAccesoSistema.login(this.usuario, this.password, httpServletRequest, httpServletResponse);
//			
//			if(autentico){
//				return null;
//			}else{
//				return null;
//			}
//		}catch(NegocioExcepcion neg){
//			AplicacionUtil.mostrarMensajeJSF(3, "ERROR", neg.getMessage());
//			return null;
//		}
//	}
	
	public SAccesoSistema getsAccesoSistema() {
		return sAccesoSistema;
	}

	public void setsAccesoSistema(SAccesoSistema sAccesoSistema) {
		this.sAccesoSistema = sAccesoSistema;
	}


	public String getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getIdPassword() {
		return idPassword;
	}
	
	public void setIdPassword(String idPassword) {
		this.idPassword = idPassword;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
}
