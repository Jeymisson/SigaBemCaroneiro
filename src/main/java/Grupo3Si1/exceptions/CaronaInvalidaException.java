package Grupo3Si1.exceptions;

/**
 * 
 * Excecao quando carona eh invalida
 * Quando falta algum dos parametros obrigatorios.
 *
 */
public class CaronaInvalidaException extends Exception {

	public CaronaInvalidaException() {
		super("Carona Inválida");
	}
	
}
