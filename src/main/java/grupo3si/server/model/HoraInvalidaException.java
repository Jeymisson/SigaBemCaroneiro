package grupo3si.server.model;

/**
 * 
 * Excecao quando a hora eh invalida.
 * Aceita apenas no formato HH:MM, onde horas eh 0 a 23.
 *
 */
public class HoraInvalidaException extends Exception {

	public HoraInvalidaException() {
		super("Hora inválida");
	}
	
}
