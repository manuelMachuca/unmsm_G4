    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.seguridad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Miauuu
 */
public class SeguridadParametros {
    
	public static final String LOGIN_USERNAME = "login_username";
	public static final String LOGIN_PASSWORD = "login_password";
	public static final String LOGIN_URL_AUTENTICACION = "/login_autenticacion";
	/* PARAMETROS DE CONFIGURACION PARA LA SEGURIDAD DE LA APLICACION */
	public static final String PATRON_RECURSOS = "/resources/**";
	public static final String PATRON_ACCESO_PAGINAS = "/SEARCH/**";
	
	public static final String INICIAR_SESION = "/index.xhtml";
//	public static final String INICIO_PAGINA = "SEARCH/search_Init.xhtml";
	public static final String INICIO_PAGINA_ALUMNO = "/ALUMNO/presentacion_Alumno.xhtml";
	public static final String ACC_ALUMNO_FAVORITO = "/ALUMNO/favorito_alumno.xhtml";
	
//	PROFESOR
	public static final String ACC_PAGINA_PROFESOR = "/PROFESOR/presentacion_Profesor.xhtml";
	public static final String ACC_DATOS_PROFESOR = "/PROFESOR/datos_Profesor.xhtml";
	public static final String ACC_DATOS_PERSONALES_PROFESOR = "/PROFESOR/datosPersonales_Profesor.xhtml";
	public static final String ACC_CURSOS_PROFESOR = "/PROFESOR/cursos_Profesor.xhtml";
	public static final String ACC_PASSWORD_PROFESOR = "/PROFESOR/cambioPassword_Profesor.xhtml";
	
	public static final String ACC_FAVORITO_ALUMNO = "/ALUMNO/favorito_alumno.xhtml";
	public static final String ACC_INICIO_PAGINA = "/SEARCH/search_Init.xhtml";
	
	//Invitado
	public static final String SEACR_INIT_INVITADO = "/SEARCH/search_Invited.xhtml";
			
	//Alumno
	public static final String INICIO_PAGINA = "INICIO_PAGINA";
	
	//Profesor
	public static final String INICIO_PAGINA_PROFESOR = "INICIA_PAGINA_PROFESOR";
	public static final String DATOS_PROFESOR = "DATOS_PROFESOR";
	public static final String DATOS_PERSONALES_PROFESOR = "DATOS_PERSONALES_PROFESOR";
	public static final String CURSOS_PROFESOR = "CURSOS_PROFESOR";
	public static final String PASSWORD_PROFESOR = "PASSWORD_PROFESOR";
	
	//Alumno
	public static final String PRENSENTACION_ALUMNO= "PRENSENTACION_ALUMNO";
	public static final String FAVORITO_ALUMNO= "FAVORITO_ALUMNO";
	
 
	
	
	
	public static String PERFIL_ALUMNO = "PERFIL_ALUMNO";
	public static String PERFIL_PROFE = "PERFIL_PROFE";
	public static String PERFIL_INVITADO = "PERFIL_INVITADO";	
	
//	public final static Map<String,String> OPCION_ACC = new HashMap<String, String>(){
//		{
//			put("01S",VER_CURSOS);
//			put("02S",AGREGAR_CURSOS);
//			put("03S",MODIFICAR_PROFE);
//			put("03S",MODIFICAR_ALUMNO);
//		}
//	};
	public final static Map<String,String> OPCION_ACC = new HashMap<String, String>(){
		{
			put("1",PERFIL_PROFE);
			put("2",PERFIL_ALUMNO);
			put("3",PERFIL_INVITADO);
		}
	};


}
