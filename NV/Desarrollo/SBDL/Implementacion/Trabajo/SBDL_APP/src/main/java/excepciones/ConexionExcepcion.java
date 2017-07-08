package excepciones;

import org.springframework.security.core.AuthenticationException;

public class ConexionExcepcion extends AuthenticationException {

	public ConexionExcepcion(String msg) {
		super(msg);
	}

}
