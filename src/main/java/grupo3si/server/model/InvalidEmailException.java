package grupo3si.server.model;

public class InvalidEmailException extends Exception {
	public InvalidEmailException() {
		super("Email inválido");
	}
}
