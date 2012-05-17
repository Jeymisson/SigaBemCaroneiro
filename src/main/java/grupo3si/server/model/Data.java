package grupo3si.server.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Data {

	/**
	 * Construtor de Data
	 */
	public Data(){
	}

	//Metodos validadores
	/**
	 * Verifica se o formato de uma data passada eh valido
	 * @param inDate A data
	 * @param hora A hora
	 * @return True caso a data seja valida e False caso contrario
	 */
	public static boolean isDataValida(String inDate, String hora) {
		if (inDate == null) {
			return false;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		if (inDate.trim().length() != dateFormat.toPattern().length()) {
			return false;
		}
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return !isPassado(inDate,hora);

	}
	/**
	 * Verifica se o formato de uma hora eh valido
	 * @param horaRecebida A hora
	 * @return True caso a hora seja valida e false caso contrario
	 */
	public static boolean isHoraValida(String horaRecebida){
		if(horaRecebida == null)
			return false;
		if(horaRecebida.matches("(([0-1]{1}[0-9]{1})?(2{1}[0-3]{1})?){1}:([0-5]{1}[0-9]{1})")){
		return true;
		}else return false;
	}
	
	//Metodos privatos
	/**
	 * Verifica se uma hora eh passada
	 * @param horarioRecebida A hora a ser verificada
	 * @return True caso a hora seja passada e False caso contrario
	 */
	private static boolean isHoraPassado(String horarioRecebida){
		
		int horaAtual = new GregorianCalendar().get(GregorianCalendar.HOUR_OF_DAY);
		int minutoAtual = new GregorianCalendar().get(GregorianCalendar.MINUTE);
		
		String[] arrayHoraRecebida = horarioRecebida.split(":");
		
		int horaRecebido = Integer.parseInt(arrayHoraRecebida[0]);
		int minutoRecebido = Integer.parseInt(arrayHoraRecebida[1]);
		
		if(horaRecebido < horaAtual){
			return true;
		}else if(horaRecebido == horaAtual && minutoRecebido < minutoAtual){
			return true;
	
		}else return false;
	}

	/**
	 * Verifica se uma data eh passada da data de hoje
	 * @param data A data
	 * @param hora A hora
	 * @return True caso a data seja passada e False caso contrario
	 */
	private static boolean isPassado(String data,String hora){
		
		DateFormat dataAtual = DateFormat.getDateInstance();
		
		String[] arrayDataRecebida = data.split("/");
		String[] arrayDataAtual = dataAtual.format(new Date()).split("/");
		
	    int diaRecebido = Integer.parseInt(arrayDataRecebida[0]);
		int mesRecebido = Integer.parseInt(arrayDataRecebida[1]);
		int anoRecebido = Integer.parseInt(arrayDataRecebida[2]);
		
		int diaAtual = Integer.parseInt(arrayDataAtual[0]);
		int mesAtual = Integer.parseInt(arrayDataAtual[1]);
		int anoAtual = Integer.parseInt(arrayDataAtual[2]);
		
		if(anoRecebido < anoAtual){
			return true;
		
		}else if((anoRecebido == anoAtual) && (mesRecebido < mesAtual)){
			return true;
		
		}else if((anoRecebido == anoAtual) && (mesRecebido == mesAtual) && (diaRecebido < diaAtual)){
			return true;
		
		}else if((anoRecebido == anoAtual) && (mesRecebido == mesAtual) && (diaRecebido == diaAtual)){
			return isHoraPassado(hora);
		
		}else return false;
			
		
	}

	
}