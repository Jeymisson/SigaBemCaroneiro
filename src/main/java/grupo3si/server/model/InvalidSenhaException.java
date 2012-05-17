package grupo3si.server.model;

public class InvalidSenhaException extends Exception {
	public InvalidSenhaException() {
		super("Senha Inválida");
	}
}
