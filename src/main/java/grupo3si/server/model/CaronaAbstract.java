package grupo3si.server.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class CaronaAbstract implements Carona {

	static int contador = 0;
	String origem, destino, data, hora;
	Integer vagas;
	int id;
	boolean ehMunicipal;
	String cidade;

	/**
	 * Construtor de Carona
	 * 
	 * @param origem
	 *            A origem
	 * @param destino
	 *            O destino
	 * @param data
	 *            A data
	 * @param hora
	 *            O horario
	 * @param vagas
	 *            A quantidade de vagas
	 * @throws Exception
	 *             Excecao caso os dados inseridos sejam invalidos
	 */
	public CaronaAbstract(String origem, String destino, String data,
			String hora, Integer vagas, boolean ehMunicipal, String cidade) throws Exception {

		verificaDados(origem, destino, data, hora, vagas);

		contador++;
		this.origem = origem;
		this.destino = destino;
		this.data = data;
		this.hora = hora;
		this.vagas = vagas;
		this.cidade = cidade;
		this.id = contador;
		this.ehMunicipal = ehMunicipal;
	}

	/**
	 * Verifica se a carona eh do tipo municipal
	 * 
	 * @return True caso a carona seja municipal e False caso contrario
	 */

	public boolean ehMunicipal() {
		return ehMunicipal;

	}

	// Metodos Get's
	/**
	 * Metodo acessor para a origem da carona
	 */
	public String getOrigem() {
		return origem;
	}

	/**
	 * Metodo acessor para o destino da carona
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * Metodo acessor para a data da carona
	 */
	public String getData() {
		return data;
	}

	public String getCidade(){
		return cidade;
	}
	
	/**
	 * Metodo acessor para o hroario da carona
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Metodo acessor para a quantidade de vagas da carona
	 */
	public Integer getVagas() {
		return vagas;
	}

	/**
	 * Metodo acessor para o id da carona
	 */
	public String getId() {
		return String.valueOf(id);
	}

	/**
	 * ToString de Carona - Retorna apenas o id da carona
	 */
	public String toString() {
		return String.valueOf(id);
	}

	/**
	 * String no formato: Origem - Destino
	 */
	public String getTrajeto() {
		return this.getOrigem() + " - " + this.getDestino();
	}

	/**
	 * String no formato: "origem" para "destino" no dia "data" as "hora"
	 */
	public String getCarona() {
		return this.getOrigem() + " para " + this.getDestino() + ", no dia "
				+ this.getData() + ", as " + this.getHora();
	}

	/**
	 * Diminui em 1 o numero de vagas na carona
	 */
	public void preencheVagas() {
		this.vagas--;
	}

	/**
	 * Compara duas caronas
	 */
	public boolean equals(Carona obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaronaAbstract other = (CaronaAbstract) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (hora == null) {
			if (other.hora != null)
				return false;
		} else if (!hora.equals(other.hora))
			return false;
		if (origem == null) {
			if (other.origem != null)
				return false;
		} else if (!origem.equals(other.origem))
			return false;
		if (!vagas.equals(other.vagas))
			return false;
		return true;
	}

	// Metodos privados
	/**
	 * Verifica os dados passados para instanciar uma carona
	 * 
	 * @param origem
	 *            A origem
	 * @param destino
	 *            O destino
	 * @param data
	 *            A data
	 * @param hora
	 *            O horario
	 * @param vagas
	 *            A quantidade de vagas
	 * @throws Exception
	 *             Excecao caso exista dados errados
	 */
	private void verificaDados(String origem, String destino, String data,
			String hora, Integer vagas) throws Exception {
		if (origem == null || origem.trim().equals("")) {
			throw new OrigemInvalidaException();
		}
		if (destino == null || destino.trim().equals("")) {
			throw new DestinoInvalidaException();
		}
		
		checaData(data, hora);
		
		if (vagas == null || vagas <= 0) {
			throw new VagaInvalidaException();
		}

	}

	/**
	 * Checa a data e a hora
	 * 
	 * @param data
	 *            A data
	 * @param hora
	 *            A hora
	 * @return True caso esteja OK e False caso contrario
	 * @throws DataInvalidaException 
	 * @throws HoraInvalidaException 
	 */
	private void checaData(String data, String hora) throws DataInvalidaException, HoraInvalidaException {
		SimpleDateFormat format = null;
		String dataHora = data + " " + hora;
		Date date =  null;  
		
		format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
		try {  
		    date = format.parse(data);
		} catch (Exception e) {  
			throw new DataInvalidaException();
		}
		
		format = new SimpleDateFormat("HH:mm");
		format.setLenient(false);
		try {
			date = format.parse(hora);
		} catch (Exception e) {
			throw new HoraInvalidaException();
		}
		
		format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		format.setLenient(false);
		try {
			date = format.parse(dataHora);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(date.before(new Date())) throw new DataInvalidaException();
		

	}

}
