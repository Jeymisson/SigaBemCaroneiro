package grupo3si.server.model;

public class CaronaSimples extends CaronaAbstract {

	boolean ehMunicipal;
	/**
	 * Construtor de uma carona do tipo Simples
	 * @param origem A origem
	 * @param destino O destino
	 * @param data A data
	 * @param hora A hora
	 * @param vagas A quantidade de vagas
	 * @throws Exception Excecao caso sejam passados dados invalidos
	 */
	public CaronaSimples(String origem, String destino, String data, String hora, Integer vagas) throws Exception {
		super(origem, destino, data, hora, vagas);
		ehMunicipal = false;
	}

	/**
	 * Verificador do tipo de carona 
	 * @return Retorna True caso a carona seja municipal e False caso contrario
	 */
	@Override
	public boolean ehMunicipal() {
		return ehMunicipal;
	}
	

}

