package grupo3si.server.model;

/**
 * 
 * Excecao quando o destino eh invalido.
 * Quando eh vazio ou null.
 *
 */
public class DestinoInvalidaException extends Exception {

	public DestinoInvalidaException() {
		super("Destino inválido");
	}
	
}
