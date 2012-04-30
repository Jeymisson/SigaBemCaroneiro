package Grupo3Si1.handles;

import Grupo3Si1.exceptions.*;


public abstract class CaronaAbstract implements Carona {
	
	static int contador = 0;
	String origem, destino, data, hora;
	Integer vagas;
	int id;
	
	/**
	 * Construtor de Carona
	 * @param origem A origem
	 * @param destino O destino
	 * @param data A data
	 * @param hora O horario
	 * @param vagas A quantidade de vagas
	 * @throws Exception Excecao caso os dados inseridos sejam invalidos
	 */
	public CaronaAbstract(String origem, String destino, String data, String hora, Integer vagas) throws Exception{
		
		verificaDados(origem, destino, data, hora, vagas);
		
		contador++;
		this.origem = origem;
		this.destino = destino;
		this.data = data;
		this.hora = hora;
		this.vagas = vagas;
		this.id = contador;
	}
	/**
	 * Verifica se a carona eh do tipo municipal
	 * @return True caso a carona seja municipal e False caso contrario
	 */
	public abstract boolean ehMunicipal();
	

	//Metodos Get's
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
		return this.getOrigem() + " para " + this.getDestino() + ", no dia " + this.getData() + ", as " + this.getHora();
	}

	/**
	 * Retorna o atributo pedido da carona
	 */
	public String getAtributo(String atributo) throws Exception {
		
		if(atributo == null || atributo.equals("")){
			throw new InvalidAtributeException();
		}
		
		String resp = null;
		
		if(this == null){
			throw new InexistenteItemException();
		}

		if (AtributosDeCarona.ORIGEM.getatribute().equalsIgnoreCase(atributo)) {
			resp = getOrigem();
		} else if (AtributosDeCarona.DATA.getatribute().equalsIgnoreCase(atributo)) {
			resp = getData();
		} else if (AtributosDeCarona.DESTINO.getatribute().equalsIgnoreCase(atributo)) {
			resp = getDestino();
		}else if (AtributosDeCarona.VAGA.getatribute().equalsIgnoreCase(atributo)) {
			resp = String.valueOf(getVagas());
		}else if(AtributosDeCarona.HORA.getatribute().equalsIgnoreCase(atributo)) {
			resp = String.valueOf(getHora());
		}else if(AtributosDeCarona.EHMUNICIPAL.getatribute().equalsIgnoreCase(atributo)){
			resp = ehMunicipal()+"";
		}else
			throw new InexistentAtributeException();

		return resp;
	}



	/**
	 * Diminui em 1 o numero de vagas na carona
	 */
	public void preencheVagas() {
			this.vagas --;
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
	
	//Metodos privados
	/**
	 * Verifica os dados passados para instanciar uma carona
	 * @param origem A origem
	 * @param destino O destino
	 * @param data A data
	 * @param hora O horario
	 * @param vagas A quantidade de vagas
	 * @throws Exception Excecao caso exista dados errados
	 */
	private void verificaDados(String origem, String destino, String data, String hora, Integer vagas) throws Exception {
		if(origem == null || origem.trim().equals("")){
			throw new OrigemInvalidaException();
		}
		if(destino == null || destino.trim().equals("")){
			throw new DestinoInvalidaException();
		}
		if(data == null || !checaDataHora(data, hora)){
			throw new DataInvalidaException();
		}
		if(!Data.isHoraValida(hora)){
			throw new HoraInvalidaException();
		}
		if(vagas==null || vagas<=0){
			throw new VagaInvalidaException();
		}
		
		
	}
	
	/**
	 * Checa a data e a hora
	 * @param data A data
	 * @param hora A hora
	 * @return True caso esteja OK e False caso contrario
	 */
	private boolean checaDataHora(String data, String hora) {
		return Data.isDataValida(data, hora);
	}

}

