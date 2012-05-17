package grupo3si.server.model;

public class InexistentLoginException extends Exception {
	
	public InexistentLoginException() {
		super("Usuário inexistente");
	}

}
