package Grupo3Si1.exceptions;

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
