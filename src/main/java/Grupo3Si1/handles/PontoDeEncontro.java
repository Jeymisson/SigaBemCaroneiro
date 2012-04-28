package Grupo3Si1.handles;

public class PontoDeEncontro implements Comparable<PontoDeEncontro> {
	
	private String nome;
	
	public PontoDeEncontro(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString(){
		return this.nome;
	}
	
	
	public boolean equals(Object obj) {
		PontoDeEncontro other = null;
		if(obj instanceof PontoDeEncontro){
			other = (PontoDeEncontro) obj;
		}
		return this.getNome().equals(other.getNome());
	}
	
	
	public int compareTo(PontoDeEncontro o) {
		return this.getNome().compareTo(o.getNome());
	}
	
}
