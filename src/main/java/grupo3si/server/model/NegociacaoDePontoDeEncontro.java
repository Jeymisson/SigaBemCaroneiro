package grupo3si.server.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class NegociacaoDePontoDeEncontro {
	
	private static Integer cont=0;
	private String idCarona;
	private String idSessao;
	private List<PontoDeEncontro> pontosDeEncontro;
	private String id;
	
	/**
	 * Construtor 
	 * @param idCarona
	 * @param idSessao
	 * @param pontosDeEncontro
	 */
	public NegociacaoDePontoDeEncontro(String idCarona, String idSessao, String pontosDeEncontro) {
		this.idCarona = idCarona;
		this.idSessao = idSessao;
		this.id = (++cont).toString();
		this.geraPontosDeEncontro(pontosDeEncontro);
	}
	/**
	 * Metodo que retorna o Id da carona
	 * @return
	 */
	public String getIdCarona() {
		return idCarona;
	}
	/**
	 * metodo que retorna o ID da sessao
	 * @return String IdSessao
	 */
	public String getIdSessao() {
		return idSessao;
	}
	/**
	 * Metodo que retorna uma lista de pontos de encontro
	 * @return List<PontoDeEncontro> pontos
	 */
	public List<PontoDeEncontro> getPontosDeEncontro() {
		return pontosDeEncontro;
	}
	/**
	 * Metodo que retorna o ID da negocioacao
	 * @return ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * Metodo que gera ponto de encontro
	 * @param pontosDeEncontro
	 */
	private void geraPontosDeEncontro(String pontosDeEncontro){
		this.pontosDeEncontro = new ArrayList<PontoDeEncontro>();
		String[] array = pontosDeEncontro.split("; ");
		for(String ponto : array){
			this.pontosDeEncontro.add(new PontoDeEncontro(ponto));
		}
	}
	/**
	 * Metodo que compara duas negociacaoes
	 */
	@Override
	public boolean equals(Object obj) {
		NegociacaoDePontoDeEncontro negociacao = null;
		if(obj instanceof NegociacaoDePontoDeEncontro){
			negociacao = (NegociacaoDePontoDeEncontro) obj;
		}

		if(negociacao == null) return false;
		
		if(pontosDeEncontro.size() != negociacao.getPontosDeEncontro().size()) return false;
		
		List<PontoDeEncontro> thisPontos = new ArrayList<PontoDeEncontro>(pontosDeEncontro);
		List<PontoDeEncontro> otherPontos = new ArrayList<PontoDeEncontro>(negociacao.getPontosDeEncontro());
		Collections.sort(thisPontos);
		Collections.sort(otherPontos);
		
		for(int i=0; i<thisPontos.size(); i++){
			if(!thisPontos.get(i).equals(otherPontos.get(i))){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Metodo que verifica se uma negociacao contem outra
	 * @param negociacao
	 * @return boolean
	 */
	public boolean contains(NegociacaoDePontoDeEncontro negociacao) {
		
		List<PontoDeEncontro> thisPontos = new ArrayList<PontoDeEncontro>(pontosDeEncontro);
		List<PontoDeEncontro> otherPontos = new ArrayList<PontoDeEncontro>(negociacao.getPontosDeEncontro());
		Collections.sort(thisPontos);
		Collections.sort(otherPontos);
		
		if(otherPontos.size() > thisPontos.size()) return false;
		for(int i=0; i<otherPontos.size(); i++){
			if(!thisPontos.get(i).equals(otherPontos.get(i))){
				return false;
			}
		}
		
		return true;
	}

}