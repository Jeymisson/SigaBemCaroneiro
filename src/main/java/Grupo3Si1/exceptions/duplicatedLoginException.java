package Grupo3Si1.exceptions;

public class duplicatedLoginException extends Exception {
	public duplicatedLoginException() {
		super("Já existe um usuário com este login");
	}
}
