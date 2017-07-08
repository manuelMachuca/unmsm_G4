package excepciones;

public class ControladorExcepcion extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ControladorExcepcion() {
        // TODO Auto-generated constructor stub
    }
	public ControladorExcepcion(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

	public ControladorExcepcion(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
