import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;

import dao.entidad.Usuario;
import dao.implement.UserDAO;
import dao.interfaces.IUserDAO;
import modelo.UsuarioService;

public class UsuarioTest {

UsuarioService usuarioService = new UsuarioService();
UserDAO userDAO = new UserDAO();
	

	@Test
	@Ignore
	@Transactional
	public void obtenerUsuario() {
		
		
		
		Usuario user = new Usuario();
		user = usuarioService.obtenerUsuario(41);

						
		System.out.println(user.getPassword());

		
	}
	
	@Test
 //   @Ignore
	@Transactional
	public void validarUser(){
		userDAO.ValidarUsuario("dfdf@gmail.com","123456");
	}
	
	@Test
//    @Ignore
	@Transactional
	public void actualizarUser(){
		Usuario user = new Usuario();
		String contraNueva = "654321";
		user = usuarioService.obtenerUsuario(30);
		user.setPassword(contraNueva);
		usuarioService.actualizarUsuario(user);
		System.out.println(user.getPassword());
	}
	
}
