package Grupo3Si1.handles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class NegociacaoDePontoDeEncontro {
	
	private static Integer cont=0;
	private String idCarona;
	private String idSessao;
	private List<PontoDeEncontro> pontosDeEncontro;
	private String id;
	
	public NegociacaoDePontoDeEncontro(String idCarona, String idSessao, String pontosDeEncontro) {
		this.idCarona = idCarona;
		this.idSessao = idSessao;
		this.id = (++cont).toString();
		this.geraPontosDeEncontro(pontosDeEncontro);
	}
	
	public String getIdCarona() {
		return idCarona;
	}
	
	public String getIdSessao() {
		return idSessao;
	}
	
	public List<PontoDeEncontro> getPontosDeEncontro() {
		return pontosDeEncontro;
	}
	
	public String getId() {
		return id;
	}
	
	private void geraPontosDeEncontro(String pontosDeEncontro){
		this.pontosDeEncontro = new ArrayList<PontoDeEncontro>();
		String[] array = pontosDeEncontro.split("; ");
		for(String ponto : array){
			this.pontosDeEncontro.add(new PontoDeEncontro(ponto));
		}
	}

	@Override
	public boolean equals(Object obj) {
		NegociacaoDePontoDeEncontro negociacao = null;
		if(obj instanceof NegociacaoDePontoDeEncontro){
			negociacao = (NegociacaoDePontoDeEncontro) obj;
		}

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

	public boolean contains(NegociacaoDePontoDeEncontro negociacao) {
		
		List<PontoDeEncontro> thisPontos = new ArrayList<PontoDeEncontro>(pontosDeEncontro);
		List<PontoDeEncontro> otherPontos = new ArrayList<PontoDeEncontro>(negociacao.getPontosDeEncontro());
		Collections.sort(thisPontos);
		Collections.sort(otherPontos);
		
		Integer min = thisPontos.size() < otherPontos.size() ? thisPontos.size() : otherPontos.size();
		
		for(int i=0; i<min; i++){
			if(!thisPontos.get(i).equals(otherPontos.get(i))){
				return false;
			}
		}
		
		return true;
	}

}
