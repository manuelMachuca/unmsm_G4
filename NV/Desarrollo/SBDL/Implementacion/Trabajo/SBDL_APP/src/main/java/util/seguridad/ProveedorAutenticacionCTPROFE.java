package util.seguridad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import dao.entidad.Perfil;
import dao.entidad.Persona;
import excepciones.ConexionExcepcion;
import modelo.seguridad.SValidarUsuario;

/**
 * Manuelazo
 */
 

public class ProveedorAutenticacionCTPROFE implements AuthenticationProvider {
//
//	public final static String DATA_SOURCE_URL = "jdbc:oracle:thin:IP:PUERTO:SID";
//	public final static String DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
	ArrayList<String> autoridadesAlumno;
	ArrayList<String> autoridadesDocente;
	
	@PostConstruct
	public void initialize() {
		autoridadesAlumno = new ArrayList<>();
		autoridadesAlumno.add(SeguridadParametros.INICIO_PAGINA);
		autoridadesAlumno.add(SeguridadParametros.PRENSENTACION_ALUMNO);
		autoridadesAlumno.add(SeguridadParametros.FAVORITO_ALUMNO);
		
		autoridadesDocente = new ArrayList<>();
		autoridadesDocente.add(SeguridadParametros.INICIO_PAGINA_PROFESOR);
		autoridadesDocente.add(SeguridadParametros.DATOS_PROFESOR);
		autoridadesDocente.add(SeguridadParametros.DATOS_PERSONALES_PROFESOR);
		autoridadesDocente.add(SeguridadParametros.CURSOS_PROFESOR);
		autoridadesDocente.add(SeguridadParametros.PASSWORD_PROFESOR);
	}

	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		return autentificar(authentication);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (authentication
				.isAssignableFrom(UsernamePasswordAuthenticationToken.class));
	}
	
	private Authentication autentificar(Authentication authentication){
				
		String username = (String) authentication.getName();
		String password = (String) authentication.getCredentials();
		
		SValidarUsuario sValidarUsuario = new SValidarUsuario();
		
		dao.entidad.Usuario  userBD = sValidarUsuario.validarUsuario(username,password);
		
		if(userBD != null){
			
			Perfil nroPerfil = userBD.getPerfil();
			Persona persona = userBD.getPersona();
			
			System.out.println(nroPerfil.getIdperfil());
			List<GrantedAuthority> roles = getAuthorities(String.valueOf(nroPerfil.getIdperfil()));
			
			Usuario usuarioMFTP = crearUsuario(username,
					password, roles, nroPerfil.getNombreperfil(),userBD.getIdusuario(),persona);
			
			UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
					authentication.getName(),
					authentication.getCredentials(), roles);
			System.out.println("Autoridades: " + userToken.getAuthorities());
			userToken.setDetails(usuarioMFTP);
	
			return userToken;
			
		}else{
			throw new ConexionExcepcion("Usuario o Contraseña incorrecto");
		}

	}
	private Usuario crearUsuario(String username, String password,
			List<GrantedAuthority> roles,String area,Integer idUsuario,Persona persona) {
		Usuario usuarioCPTPROF = null;

		Collection<? extends GrantedAuthority> authorities = roles;
		
		usuarioCPTPROF = new Usuario(username, password, authorities, area,idUsuario,persona);
	
		return usuarioCPTPROF;
	}

	public List<GrantedAuthority> getAuthorities(String nroPerfil) {
		
		List<GrantedAuthority> LISTAUTHORITIES = new ArrayList<GrantedAuthority>();
		
		if(nroPerfil.equals("2"))
			for (int i = 0; i < autoridadesAlumno.size(); i++) {
				LISTAUTHORITIES.add(new SimpleGrantedAuthority(autoridadesAlumno.get(i)));
			}
		else
			if(nroPerfil.equals("1")){
				for (int i = 0; i < autoridadesDocente.size(); i++) {
					LISTAUTHORITIES.add(new SimpleGrantedAuthority(autoridadesDocente.get(i)));
				}
			}
			
		return LISTAUTHORITIES;
	}

	
	
}
