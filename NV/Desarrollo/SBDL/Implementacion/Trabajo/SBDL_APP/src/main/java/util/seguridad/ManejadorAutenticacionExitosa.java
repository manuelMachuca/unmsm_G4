package util.seguridad;

import static util.AplicacionUtil.redireccionar;
import static util.seguridad.SeguridadParametros.INICIO_PAGINA_ALUMNO;
import static util.seguridad.SeguridadParametros.ACC_PAGINA_PROFESOR;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import util.AplicacionUtil;


public class ManejadorAutenticacionExitosa implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		Usuario usuario = AplicacionUtil.obtenerUsuario();
		
		if (usuario.getNomPerfil().equals("alumno"))
			redireccionar(INICIO_PAGINA_ALUMNO);
		else 
			if (usuario.getNomPerfil().equals("profesor"))
				redireccionar(ACC_PAGINA_PROFESOR);
	}

}

