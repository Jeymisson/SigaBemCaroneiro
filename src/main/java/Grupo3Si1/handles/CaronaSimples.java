package Grupo3Si1.handles;

public class CaronaSimples extends CaronaAbstract {

	boolean ehMunicipal;
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
	public CaronaSimples(String origem, String destino, String data, String hora, Integer vagas) throws Exception {
		super(origem, destino, data, hora, vagas);
		ehMunicipal = false;
	}

	/**
	 * 
	 */
	@Override
	public boolean ehMunicipal() {
		return ehMunicipal;
	}
	

}

