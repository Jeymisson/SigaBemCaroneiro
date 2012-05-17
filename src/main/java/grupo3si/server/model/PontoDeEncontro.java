package grupo3si.server.model;

public class PontoDeEncontro implements Comparable<PontoDeEncontro> {
	
	private String nome;
	/**
	 * Metodo que muda um ponto de encontro
	 * @param nome
	 */
	public PontoDeEncontro(String nome) {
		this.nome = nome;
	}
	/**
	 * Metodo que retorna o local de encontro
	 * @return String
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * metodo que muda o local de uma carona
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Metodo que retorna o toString de um ponto de encontro
	 */
	public String toString(){
		return this.nome;
	}
	
	/**
	 * metodo que retorna se dois pontos de encontro são iguais.
	 */
	public boolean equals(Object obj) {
		PontoDeEncontro other = null;
		if(obj instanceof PontoDeEncontro){
			other = (PontoDeEncontro) obj;
		}
		return this.getNome().equals(other.getNome());
	}
	
	/**
	 * metodo que compara dois pontos de encontro.
	 */
	public int compareTo(PontoDeEncontro o) {
		return this.getNome().compareTo(o.getNome());
	}
	
}