package grupo3si.server.model;

public class SessaoInexistenteException extends Exception {

	public SessaoInexistenteException() {
		super("Sessão inexistente");
	}
	
}
