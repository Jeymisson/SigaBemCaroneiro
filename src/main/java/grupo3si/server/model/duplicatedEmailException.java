package grupo3si.server.model;
/**
 * 
 * Excecao quando ao tentar criar um usuario ja existe seu email.
 *
 */
public class duplicatedEmailException extends Exception {
	public duplicatedEmailException() {
		super("Já existe um usuário com este email");
	}

}
