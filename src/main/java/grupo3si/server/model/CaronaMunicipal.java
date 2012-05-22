package grupo3si.server.model;

public class CaronaMunicipal extends CaronaAbstract {

	boolean ehMunicipal = false;
	private String cidade;
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
	public CaronaMunicipal(String origem, String destino, String data,String hora, Integer vagas, String cidade,boolean ehMunicipal) throws Exception {
		super(origem, destino, data, hora, vagas, ehMunicipal);
		this.setCidade(cidade);
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
