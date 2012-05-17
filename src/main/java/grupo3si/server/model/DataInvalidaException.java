package grupo3si.server.model;

/**
 * 
 * Excecao quando a data eh invalida.
 * Aceita apenas o formato DD/MM/AAAA
 *
 */
public class DataInvalidaException extends Exception {
	
	public DataInvalidaException() {
		super("Data inválida");
	}
}
