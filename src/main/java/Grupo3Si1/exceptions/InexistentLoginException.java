package Grupo3Si1.exceptions;

public class InexistentLoginException extends Exception {
	
	public InexistentLoginException() {
		super("Usuário inexistente");
	}

}
