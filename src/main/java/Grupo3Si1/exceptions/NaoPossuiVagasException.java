package Grupo3Si1.exceptions;

public class NaoPossuiVagasException extends Exception {
	public NaoPossuiVagasException() {
		super("Usuário não possui vaga na carona.");
	}
}
