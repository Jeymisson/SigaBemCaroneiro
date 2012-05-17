package grupo3si.server.model;

public class SolicaticaoInexistenteException extends Exception {
	public SolicaticaoInexistenteException(){
		super("Solicitação inexistente");
	}
}
