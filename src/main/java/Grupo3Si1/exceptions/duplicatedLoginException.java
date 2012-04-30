package Grupo3Si1.exceptions;

/**
 * 
 * Excecao quando ja existe o login na hora de criar o usuario.
 *
 */
public class duplicatedLoginException extends Exception {
	public duplicatedLoginException() {
		super("Já existe um usuário com este login");
	}
}
