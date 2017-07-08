package modelo.seguridad;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import static util.seguridad.SeguridadParametros.*;


public class SAccesoSistema{

	@Autowired
	@Qualifier("authenticationManagerCTPROFE")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	
	@Autowired
	private AuthenticationFailureHandler failureHandler;
	
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	

	public boolean login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		System.err.println("autenManager: "+authenticationManager);
		Authentication requestToken = new UsernamePasswordAuthenticationToken(username, password);
		System.err.println("ENTRO A AUTENTICARME");
		Authentication responseToken = authenticationManager.authenticate(requestToken);
		SecurityContextHolder.getContext().setAuthentication(responseToken);
		return true;
	}
	

	public void autenticacion(String username, String password, HttpServletRequest request,	HttpServletResponse response) {
		try{
			Authentication requestToken = new UsernamePasswordAuthenticationToken(username, password);
			Authentication responseToken = authenticationManager.authenticate(requestToken);
			SecurityContextHolder.getContext().setAuthentication(responseToken);
			autenticacionExitosa(request, response, responseToken);
		}catch(AuthenticationException authenticationException){
			autenticacionFallida(request, response, authenticationException);
		}
	}

	private void autenticacionExitosa(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
		try {
			successHandler.onAuthenticationSuccess(request, response, authentication);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	private void autenticacionFallida(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception){
		try {
			failureHandler.onAuthenticationFailure(request, response, exception);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		
		}
	}


	public void autenticacion(FacesContext facesContext) throws ServletException, IOException{
		ExternalContext extenalContext = facesContext.getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest)extenalContext.getRequest()).getRequestDispatcher(LOGIN_URL_AUTENTICACION);
		dispatcher.forward((ServletRequest)extenalContext.getRequest(), (ServletResponse)extenalContext.getResponse());
		facesContext.responseComplete();
	}



}