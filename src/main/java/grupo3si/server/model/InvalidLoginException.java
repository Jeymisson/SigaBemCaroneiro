package grupo3si.server.model;

public class InvalidLoginException extends Exception {

	public InvalidLoginException() {
		super("Login inválido");
	}

}
