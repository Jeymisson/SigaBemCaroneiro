package Grupo3Si1.exceptions;

public class InvalidEmailException extends Exception {
	public InvalidEmailException() {
		super("Email inválido");
	}
}
