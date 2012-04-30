package Grupo3Si1.handles;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import Grupo3Si1.exceptions.*;

/**
 * Classe que controla as negociações de Carona.
 * @author Jeymisson
 *
 */
public class ControladorDeNegociacoes {

	private List<NegociacaoDePontoDeEncontro> sugestoesPontoDeEncontro;
	private List<NegociacaoDePontoDeEncontro> respostasSugestaoPontoEncontro;
	private List<NegociacaoDePontoDeEncontro> solicitacoesDeCarona;
	private AbstractMap<String,NegociacaoDePontoDeEncontro> solicitacoesPendentes;
	private AbstractMap<String,NegociacaoDePontoDeEncontro> solicitacoesConfirmadas;

	public ControladorDeNegociacoes() {
		this.sugestoesPontoDeEncontro = new ArrayList<NegociacaoDePontoDeEncontro>();
		this.respostasSugestaoPontoEncontro = new ArrayList<NegociacaoDePontoDeEncontro>();
		this.solicitacoesDeCarona = new ArrayList<NegociacaoDePontoDeEncontro>();
		this.solicitacoesConfirmadas = new TreeMap<String, NegociacaoDePontoDeEncontro>();
		this.solicitacoesPendentes = new TreeMap<String, NegociacaoDePontoDeEncontro>();
	}
	
	
	public List<String> getSolicitacoesPendentes(String idCarona){
		List<String> listaSolicitacoesPendentes = new ArrayList<String>();
		if(!solicitacoesPendentes.isEmpty()){
			listaSolicitacoesPendentes.add(solicitacoesPendentes.get(idCarona).getIdCarona());
		}
		return listaSolicitacoesPendentes;
	}
	
	public void removerSolicitacoesPendentes(String idCarona){
		
		solicitacoesPendentes.remove(idCarona);
	
	}
	
	public List<String> getSolicitacoesConfirmadas(String idSessao, String idCarona){
		List<String> listaSolicitacoesConfirmadas = new ArrayList<String>();
		if(!solicitacoesConfirmadas.isEmpty())
		listaSolicitacoesConfirmadas.add(solicitacoesConfirmadas.get(idSessao).getId());
		return listaSolicitacoesConfirmadas;
	}


	public String addSugestaoPontoDeEncontro(String idSessao, String idCarona, String pontos) throws PontoInvalidoException {
		NegociacaoDePontoDeEncontro sugestao = new NegociacaoDePontoDeEncontro(idCarona,idSessao,pontos);
		if(naoEstaEmPontosDeEmbarque(sugestao)) throw new PontoInvalidoException();
		sugestoesPontoDeEncontro.add(sugestao);
		
		return sugestao.getId().toString();
	}


	public String responderSugestaoPontoEncontro(String idSessao, String idCarona, String idSugestao, String pontos) {
		NegociacaoDePontoDeEncontro sugestaoResposta = new NegociacaoDePontoDeEncontro(idCarona, idSessao, pontos);
		respostasSugestaoPontoEncontro.add(sugestaoResposta);
		return sugestaoResposta.getId();
	}

	public void removerSolicitacaoAceita(NegociacaoDePontoDeEncontro solicitacao){
		solicitacoesPendentes.values().remove(solicitacao);
	}

	public String addSolicitacaoDeCarona(String idSessao, String idCarona, String ponto) throws PontoInvalidoException {
		NegociacaoDePontoDeEncontro solicitacao = new NegociacaoDePontoDeEncontro(idCarona, idSessao, ponto);
		//TODO if(naoEstaEmPontosDeEmbarque(solicitacao)) throw new PontoInvalidoException();

		solicitacoesDeCarona.add(solicitacao);
		solicitacoesPendentes.put(idCarona, solicitacao);
		
		return solicitacao.getId().toString();
	}

	public List<NegociacaoDePontoDeEncontro> getSugestoesCarona() {
		return sugestoesPontoDeEncontro;
	}

	/**
	 * Método que retorna uma carona buscada pelo id De uma solicitação
	 * @param idSolicitacao
	 * @return Carona
	 */
	public NegociacaoDePontoDeEncontro getSolicitacaoPorId(String idSolicitacao) {

		Iterator<NegociacaoDePontoDeEncontro> solicitacoesIt = solicitacoesDeCarona.iterator();
		NegociacaoDePontoDeEncontro solicitacao = null;
		while(solicitacoesIt.hasNext()){
			NegociacaoDePontoDeEncontro nextSolicitacao = solicitacoesIt.next();
			if(idSolicitacao.equals(nextSolicitacao.getId())){
				solicitacao = nextSolicitacao; break;
			}
		}// TODO acho que ficou melhor do que getCaronaPorIdSolicitacao

		return solicitacao;

	}
	
	public NegociacaoDePontoDeEncontro getSolicitacaoPendentePorId(String idSolicitacao) {

		Iterator<NegociacaoDePontoDeEncontro> solicitacoesIt = solicitacoesPendentes.values().iterator();
		NegociacaoDePontoDeEncontro solicitacao = null;
		while(solicitacoesIt.hasNext()){
			NegociacaoDePontoDeEncontro nextSolicitacao = solicitacoesIt.next();
			if(idSolicitacao.equals(nextSolicitacao.getId().toString())){
				solicitacao = nextSolicitacao; break;
			}
		}
		return solicitacao;
	}	

	public String getAtributoSolicitacao(String idSolicitacao, String atributo)throws Exception{

		NegociacaoDePontoDeEncontro solicitacao = getSolicitacaoPorId(idSolicitacao);
		System.out.println("idCarona: "+solicitacao.getIdCarona());
//		try{
//			System.out.println("id Solici: " + solicitacao.getId() );
//		}catch(Exception e){
//			System.out.println("merda na solicitacao");
//		}
		Carona carona = RepositorioDeUsuarios.getInstance().getCarona(solicitacao.getIdCarona());
		System.out.println("depois: " +carona.getId());
//		try{
//			System.out.println("carona id: " + carona.getId());
//		}catch(Exception e){
//			System.out.println("merda na carona");
//		}
		Usuario usuarioDonoCarona = RepositorioDeUsuarios.getInstance().getDonoDe(solicitacao.getIdCarona());
		Usuario usuarioDonoSolicitacao = null;
		
		Iterator<Usuario> userIt = RepositorioDeUsuarios.getInstance().getUsuarios();
		while(userIt.hasNext()){
			Usuario usuario = userIt.next();
			if(usuario.getUserID().equals(solicitacao.getIdSessao())){
				usuarioDonoSolicitacao = usuario;
				break;
			}
		}
//		try{
//			System.out.println("user id: " + usuarioDonoCarona.getUserID());
//		}catch(Exception e){
//			System.out.println("merda no dono");
//		}

		if(atributo == null || atributo.equals("")){
			throw new InvalidAtributeException();
		}

		String resp = null;

		if (AtributosDeSolicitacao.ORIGEM.getatribute().equalsIgnoreCase(atributo)) {
			resp = carona.getOrigem();
		} else if (AtributosDeSolicitacao.DESTINO.getatribute().equalsIgnoreCase(atributo)) {
			resp = carona.getDestino();
		} else if (AtributosDeSolicitacao.DONO_DA_CARONA.getatribute().equalsIgnoreCase(atributo)) {
			resp = usuarioDonoCarona.getNome();
		}else if (AtributosDeSolicitacao.DONO_DA_SOLICITACAO.getatribute().equalsIgnoreCase(atributo)) {
			resp = usuarioDonoSolicitacao.getNome();
		}else if(AtributosDeSolicitacao.PONTO_DE_DESTINO.getatribute().equalsIgnoreCase(atributo)) {
			resp = solicitacao.getPontosDeEncontro().toString().replace("[", "").replace("]", "");
		}else
			throw new InexistentAtributeException();

		return resp;
	}

	public void desistirRequisicao(String idSessao, String idCarona, String idSugestao) {

		Iterator<NegociacaoDePontoDeEncontro> sugestaoIt = sugestoesPontoDeEncontro.iterator();
		NegociacaoDePontoDeEncontro sugestao = null, nextSugestao = null;
		while(sugestaoIt.hasNext()){
			nextSugestao = sugestaoIt.next();
			if(nextSugestao.getId().equals(idSugestao)){
				sugestao = nextSugestao;
				break;
			}
		}

		if(sugestao != null){
			solicitacoesConfirmadas.values().remove(sugestao);
		}
	}

	private boolean naoEstaEmPontosDeEmbarque(NegociacaoDePontoDeEncontro negociacao){
		for(NegociacaoDePontoDeEncontro npe : respostasSugestaoPontoEncontro){
			if(npe.getIdCarona().equals(negociacao.getIdCarona())){
				if(!npe.contains(negociacao)){
					return true;
				}
			}
		}
		return false;
	}

	public void addSolicitacoesConfirmadas(String idSessao,NegociacaoDePontoDeEncontro solicitacao) {
		solicitacoesConfirmadas.put(idSessao, solicitacao);
	}
	
	public String getPontosDeEmbarque(String idCarona) {
		String result = "";
		for(NegociacaoDePontoDeEncontro npe : solicitacoesConfirmadas.values()){
			if(npe.getIdCarona().equals(idCarona)){
				for(PontoDeEncontro pe : npe.getPontosDeEncontro()){
					result += pe.getNome() + ", ";
				}
				break;
			}
		}
		return result.length() > 0 ? result.substring(0, result.length()-2) : result;
	}


	public List<PontoDeEncontro> getPontosSugeridos(String idSessao,
			String idCarona)throws Exception {

		NegociacaoDePontoDeEncontro pontosSugeridos = null;
		Iterator<NegociacaoDePontoDeEncontro> negociacaoIt = sugestoesPontoDeEncontro.iterator();
		while(negociacaoIt.hasNext()){
			NegociacaoDePontoDeEncontro nextNegociacao = negociacaoIt.next();
			if(nextNegociacao.getIdCarona().equals(idCarona)){
				pontosSugeridos = nextNegociacao; break;
			}
		}
		
		if(pontosSugeridos == null){
			throw new CaronaInexistenteException();
		}
		
		return pontosSugeridos.getPontosDeEncontro();		
		
	}
	
	public void clear(){
//		
//		sugestoesPontoDeEncontro.clear();
//		respostasSugestaoPontoEncontro.clear();
//		solicitacoesDeCarona.clear();
//		solicitacoesPendentes.clear();
//		solicitacoesConfirmadas.clear();
	}
	
}
