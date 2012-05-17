package grupo3si.server.model;

public class InvalidNameException extends Exception {
	public InvalidNameException() {
		super("Nome inválido");
	}
}
