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

	/**
	 * 
	 */
	public ControladorDeNegociacoes() {
		this.sugestoesPontoDeEncontro = new ArrayList<NegociacaoDePontoDeEncontro>();
		this.respostasSugestaoPontoEncontro = new ArrayList<NegociacaoDePontoDeEncontro>();
		this.solicitacoesDeCarona = new ArrayList<NegociacaoDePontoDeEncontro>();
		this.solicitacoesConfirmadas = new TreeMap<String, NegociacaoDePontoDeEncontro>();
		this.solicitacoesPendentes = new TreeMap<String, NegociacaoDePontoDeEncontro>();
	}
	
	/**
	 * 
	 * @param idCarona
	 * @return
	 */
	public List<String> getSolicitacoesPendentes(String idCarona){
		List<String> listaSolicitacoesPendentes = new ArrayList<String>();
		if(!solicitacoesPendentes.isEmpty()){
			listaSolicitacoesPendentes.add(solicitacoesPendentes.get(idCarona).getIdCarona());
		}
		return listaSolicitacoesPendentes;
	}
	/**
	 * 
	 * @param idCarona
	 */
	public void removerSolicitacoesPendentes(String idCarona){
		
		solicitacoesPendentes.remove(idCarona);
	
	}
	/**
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return
	 */
	public List<String> getSolicitacoesConfirmadas(String idSessao, String idCarona){
		List<String> listaSolicitacoesConfirmadas = new ArrayList<String>();
		if(!solicitacoesConfirmadas.isEmpty())
		listaSolicitacoesConfirmadas.add(solicitacoesConfirmadas.get(idSessao).getId());
		return listaSolicitacoesConfirmadas;
	}

	/**
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param pontos
	 * @return
	 * @throws PontoInvalidoException
	 */
	public String addSugestaoPontoDeEncontro(String idSessao, String idCarona, String pontos) throws PontoInvalidoException {
		NegociacaoDePontoDeEncontro sugestao = new NegociacaoDePontoDeEncontro(idCarona,idSessao,pontos);
		if(naoEstaEmPontosDeEmbarque(sugestao)) throw new PontoInvalidoException();
		sugestoesPontoDeEncontro.add(sugestao);
		
		return sugestao.getId().toString();
	}
	/**
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param idSugestao
	 * @param pontos
	 * @return
	 */
	public String responderSugestaoPontoEncontro(String idSessao, String idCarona, String idSugestao, String pontos) {
		NegociacaoDePontoDeEncontro sugestaoResposta = new NegociacaoDePontoDeEncontro(idCarona, idSessao, pontos);
		respostasSugestaoPontoEncontro.add(sugestaoResposta);
		return sugestaoResposta.getId();
	}
	/**
	 * 
	 * @param solicitacao
	 */
	public void removerSolicitacaoAceita(NegociacaoDePontoDeEncontro solicitacao){
		solicitacoesPendentes.values().remove(solicitacao);
	}
	/**
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param ponto
	 * @return
	 * @throws PontoInvalidoException
	 */
	public String addSolicitacaoDeCarona(String idSessao, String idCarona, String ponto) throws PontoInvalidoException {
		NegociacaoDePontoDeEncontro solicitacao = new NegociacaoDePontoDeEncontro(idCarona, idSessao, ponto);

		solicitacoesDeCarona.add(solicitacao);
		solicitacoesPendentes.put(idCarona, solicitacao);
		
		return solicitacao.getId().toString();
	}
	/**
	 * 
	 * @return
	 */
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
		}

		return solicitacao;

	}
	/**
	 * 
	 * @param idSolicitacao
	 * @return
	 */
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
	/**
	 * 
	 * @param idSolicitacao
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getAtributoSolicitacao(String idSolicitacao, String atributo)throws Exception{

		NegociacaoDePontoDeEncontro solicitacao = getSolicitacaoPorId(idSolicitacao);
		Carona carona = RepositorioDeUsuarios.getInstance().getCarona(solicitacao.getIdCarona());
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
	/**
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param idSugestao
	 */
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
	/**
	 * 
	 * @param negociacao
	 * @return
	 */
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
	/**
	 * 
	 * @param idSessao
	 * @param solicitacao
	 */
	public void addSolicitacoesConfirmadas(String idSessao,NegociacaoDePontoDeEncontro solicitacao) {
		solicitacoesConfirmadas.put(idSessao, solicitacao);
	}
	/**
	 * 
	 * @param idCarona
	 * @return
	 */
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

	/**
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @return
	 * @throws Exception
	 */
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

}
