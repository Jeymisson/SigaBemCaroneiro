package grupo3si.server.model;

public class TrajetoInexistenteException extends Exception {
	public TrajetoInexistenteException() {
		super("Trajeto Inexistente");
	}
}
