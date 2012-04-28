package Grupo3Si1.exceptions;

public class duplicatedEmailException extends Exception {
	public duplicatedEmailException() {
		super("Já existe um usuário com este email");
	}

}
