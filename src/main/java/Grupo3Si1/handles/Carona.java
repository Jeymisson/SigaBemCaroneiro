package Grupo3Si1.handles;

public interface Carona {
	/**
	 * 
	 * @return
	 */
	public String getOrigem();
	/**
	 * 
	 * @return
	 */
	public String getDestino();
	/**
	 * 
	 * @return
	 */
	public String getData();
	/**
	 * 
	 * @return
	 */
	public String getHora();
	/**
	 * 
	 * @return
	 */
	public Integer getVagas();
	/**
	 * 
	 * @return
	 */
	public String getId();
	/**
	 * 
	 * @return
	 */
	public String getTrajeto();
	/**
	 * 
	 * @return
	 */
	public String getCarona();
	/**
	 * 
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getAtributo(String atributo)throws Exception;
	/**
	 * 
	 */
	public void preencheVagas();
	/**
	 * 
	 * @param carona
	 * @return
	 */
	public boolean equals(Carona carona);
}
