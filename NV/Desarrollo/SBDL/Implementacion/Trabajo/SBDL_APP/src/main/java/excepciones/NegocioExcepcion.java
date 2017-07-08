package excepciones;

public class NegocioExcepcion extends RuntimeException{
	public NegocioExcepcion() {
        // TODO Auto-generated constructor stub
    }
	public NegocioExcepcion(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

	public NegocioExcepcion(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
