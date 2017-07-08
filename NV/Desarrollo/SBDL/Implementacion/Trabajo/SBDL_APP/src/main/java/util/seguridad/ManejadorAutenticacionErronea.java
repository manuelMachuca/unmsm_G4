package util.seguridad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class ManejadorAutenticacionErronea extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		
		 HttpSession session = request.getSession(false);
		
		 if(session != null){
			session.setAttribute("LoginFailureMessage", exception.getMessage()); 
		 }
		 
		 super.onAuthenticationFailure(request, response, exception);
		
	}

}
