package configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import util.seguridad.ManejadorAutenticacionErronea;
import util.seguridad.ManejadorAutenticacionExitosa;
import util.seguridad.ProveedorAutenticacionCTPROFE;

import static util.seguridad.SeguridadParametros.*;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadConfiguracion extends WebSecurityConfigurerAdapter {


//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(PATRON_RECURSOS)
		.and().ignoring().antMatchers("/redirect.jsp");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		 http
		.authorizeRequests()
		//PERFILES ALUMNOS
//		.antMatchers(ACC_INICIO_PAGINA).hasAuthority(PERFIL_ALUMNO)
		.antMatchers(ACC_INICIO_PAGINA).hasAuthority(INICIO_PAGINA)
		.antMatchers(ACC_ALUMNO_FAVORITO).hasAuthority(FAVORITO_ALUMNO)
		.antMatchers(INICIO_PAGINA_ALUMNO).hasAuthority(PRENSENTACION_ALUMNO)
		
		.antMatchers(ACC_PAGINA_PROFESOR).hasAuthority(INICIO_PAGINA_PROFESOR)
		.antMatchers(ACC_DATOS_PROFESOR).hasAuthority(DATOS_PROFESOR)
		.antMatchers(ACC_DATOS_PERSONALES_PROFESOR).hasAuthority(DATOS_PERSONALES_PROFESOR)
		.antMatchers(ACC_CURSOS_PROFESOR).hasAuthority(CURSOS_PROFESOR)
		.antMatchers(ACC_PASSWORD_PROFESOR).hasAuthority(PASSWORD_PROFESOR)
		
		
		.antMatchers(PATRON_ACCESO_PAGINAS).not().authenticated()
		.anyRequest().permitAll()
		.and()
			.formLogin()
				.loginPage(INICIAR_SESION)
				.usernameParameter(LOGIN_USERNAME)
				.passwordParameter(LOGIN_PASSWORD)
				.loginProcessingUrl(LOGIN_URL_AUTENTICACION)
				.successHandler(authenticationSuccessHandler())
				.failureHandler(authenticationFailureHandler())
		.and()
			.logout()
				.logoutSuccessUrl(INICIAR_SESION)
		.and()
			.exceptionHandling()
				.accessDeniedPage(INICIAR_SESION)
		.and()
			.csrf().disable();//DESHABILITAR LA OPCION CSRF
	}


	@Bean(name= "authenticationManagerCTPROFE")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}
	@Bean(name = "proveedorAutenticacionCTPROFE")
	public AuthenticationProvider authenticationProvider() {
		ProveedorAutenticacionCTPROFE proveedorAutenticacionMFTP = new ProveedorAutenticacionCTPROFE();
		System.out.println("XXX");
		return proveedorAutenticacionMFTP;
	}
	
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler(){
		System.out.println("2222");
		return new ManejadorAutenticacionExitosa();
	}
	
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler(){
		ManejadorAutenticacionErronea manejadorAutenticacionErronea = new ManejadorAutenticacionErronea();
		manejadorAutenticacionErronea.setDefaultFailureUrl(INICIAR_SESION);

		return manejadorAutenticacionErronea;
	}
	
	
}
