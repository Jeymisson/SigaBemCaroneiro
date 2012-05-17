package grupo3si.server.model;

/**
 * Excecao quando carona nao existe no sistema.
 * 
 */
public class CaronaInexistenteException extends Exception {

	public CaronaInexistenteException() {
		super("Carona Inexistente");
	}
	
}
