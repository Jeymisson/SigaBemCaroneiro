package grupo3si.server.model;

public class CaronaMunicipal extends CaronaAbstract {

	boolean ehMunicipal = false;
	String cidade;
	/**
	 * Construtor de uma carona do tipo Municipal
	 * @param origem A origem
	 * @param destino O destino
	 * @param data A data
	 * @param hora O horario
	 * @param vagas A quantidade de vagas
	 * @param cidade A cidade
	 * @throws Exception Excecao caso dados invavalidos sejam passados
	 */
	public CaronaMunicipal(String origem, String destino, String data,String hora, Integer vagas, String cidade) throws Exception {
		super(origem, destino, data, hora, vagas);
		ehMunicipal = true;
		this.cidade = cidade;
	}
	/**
	 * Verificador de tipo de carona
	 * @return True caso a carona seja Municipal e False caso contrario
	 */
	@Override
	public boolean ehMunicipal() {
		return ehMunicipal;
	}

}
