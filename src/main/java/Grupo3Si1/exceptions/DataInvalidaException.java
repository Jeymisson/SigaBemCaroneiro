package Grupo3Si1.exceptions;

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
