package configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import util.AplicacionUtil;

@Configuration
//@PropertySource(value = "classpath:application.properties")
@Import(value = {SeguridadConfiguracion.class,})
public class AplicacionConfiguracion {

//	@Bean
//	public AplicacionUtil aplicacionUtil(){
//		AplicacionUtil ap = new AplicacionUtil();
//		return ap;
//	}

}
