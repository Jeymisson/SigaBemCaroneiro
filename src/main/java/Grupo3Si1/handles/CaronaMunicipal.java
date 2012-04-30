package Grupo3Si1.handles;

public class CaronaMunicipal extends CaronaAbstract {

	boolean ehMunicipal = false;
	String cidade;
	/**
	 * 
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param vagas
	 * @param donoDaCarona
	 * @param cidade
	 * @throws Exception
	 */
	public CaronaMunicipal(String origem, String destino, String data,String hora, Integer vagas, Usuario donoDaCarona, String cidade) throws Exception {
		super(origem, destino, data, hora, vagas, donoDaCarona);
		ehMunicipal = true;
		this.cidade = cidade;
	}
	/**
	 * 
	 */
	@Override
	public boolean ehMunicipal() {
		return ehMunicipal;
	}

}
