package grupo3si.server.model;

public class NaoPossuiVagasException extends Exception {
	public NaoPossuiVagasException() {
		super("Usuário não possui vaga na carona.");
	}
}
