package grupo3si.server.model;

public class CidadeInexistenteException extends Exception {
	public CidadeInexistenteException() {
		super("Cidade inexistente");
	}
}
