package grupo3si.server.model;

/**
 * 
 * Execacao quando o identificador da carona eh invalido
 * Ex: id = vazio.
 *
 */
public class IdentificadorCaronaInvalidoException extends Exception {

	public IdentificadorCaronaInvalidoException() {
		super("Identificador do carona é inválido");
	}
	
}
