package Grupo3Si1.handles;

public interface Carona {
	
	public String getOrigem();
	public String getDestino();
	public String getData();
	public String getHora();
	public Integer getVagas();
	public String getId();
	public String getTrajeto();
	public String getCarona();

	public String getAtributo(String atributo)throws Exception;

	public void preencheVagas();
	public boolean equals(Carona carona);
}
