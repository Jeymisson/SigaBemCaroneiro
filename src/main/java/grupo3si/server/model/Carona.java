package grupo3si.server.model;

public interface Carona {
	/**
	 * Metodo acessor para atributo origem da carona
	 * @return A origem
	 */
	public String getOrigem();
	/**
	 * Metodo acessor para o destino da carona
	 * @return O destino
	 */
	public String getDestino();
	/**
	 * Metodo acessor para a data da carona
	 * @return A data
	 */
	public String getData();
	/**
	 * Metodo acessor para a hora da carona
	 * @return A hora
	 */
	public String getHora();
	/**
	 * Metodo acessor para a quantidade de vagas na carona
	 * @return A quantidade de vagas na carona
	 */
	public Integer getVagas();
	/**
	 * Metodo acessor para o id da carona
	 * @return O id
	 */
	public String getId();
	/**
	 * Metodo acessor para o trajeto da carona
	 * @return O trajeto
	 */
	public String getTrajeto();
	/**
	 * Descricao de carona
	 * @return "origem" para "destino" no dia "data" as "hora"
	 */
	public String getCarona();
	/**
	 * Retorna valor do atributo escolhido
	 * @param atributo O atributo
	 * @return O valor armazenado no atributo
	 * @throws Exception Excecao de atributo invalido
	 */
	public String getAtributo(String atributo)throws Exception;
	/**
	 * Diminui uma vaga na carona
	 */
	public void preencheVagas();
	/**
	 * Compara duas caronas
	 * @param carona A carona a ser comparada
	 * @return True caso sejam iguais ou False caso contrario
	 */
	public boolean equals(Carona carona);
}
