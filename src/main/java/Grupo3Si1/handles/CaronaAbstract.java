package Grupo3Si1.handles;

import Grupo3Si1.exceptions.*;


public abstract class CaronaAbstract implements Carona {
	
	static int contador = 0;
	String origem, destino, data, hora;
	Integer vagas;
	int id;
	
	/**
	 * 
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param vagas
	 * @param donoDaCarona
	 * @throws Exception
	 */
	public CaronaAbstract(String origem, String destino, String data, String hora, Integer vagas, Usuario donoDaCarona) throws Exception{
		
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
	 * 
	 * @return
	 */
	public abstract boolean ehMunicipal();
	

	//Metodos Get's
	/**
	 * 
	 */
	public String getOrigem() {
		return origem;
	}

	/**
	 * 
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * 
	 */
	public String getData() {
		return data;
	}

	/**
	 * 
	 */
	public String getHora() {
		return hora;
	}
	
	/**
	 * 
	 */
	public Integer getVagas() {
		return vagas;
	}
	/**
	 * 
	 */
	public String getId() {
		return String.valueOf(id);
	}
	
	/**
	 * 
	 */
	public String toString() {
		return String.valueOf(id);
	}

	/**
	 * 
	 */
	public String getTrajeto() {
		return this.getOrigem() + " - " + this.getDestino();
	}

	/**
	 * 
	 */
	public String getCarona() {
		return this.getOrigem() + " para " + this.getDestino() + ", no dia " + this.getData() + ", as " + this.getHora();
	}

	/**
	 * 
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
	 * 
	 */
	public void preencheVagas() {
			this.vagas --;
	}
	
	/**
	 * 
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
	 * 
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param vagas
	 * @throws Exception
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
	 * 
	 * @param data
	 * @param hora
	 * @return
	 */
	private boolean checaDataHora(String data, String hora) {
		return Data.isDataValida(data, hora);
	}

}

