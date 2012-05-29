package grupo3si.server.model;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

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
	 * Construtor do Controlador de Negociacoes
	 */
	public ControladorDeNegociacoes() {
		this.sugestoesPontoDeEncontro = new ArrayList<NegociacaoDePontoDeEncontro>();
		this.respostasSugestaoPontoEncontro = new ArrayList<NegociacaoDePontoDeEncontro>();
		this.solicitacoesDeCarona = new ArrayList<NegociacaoDePontoDeEncontro>();
		this.solicitacoesConfirmadas = new TreeMap<String, NegociacaoDePontoDeEncontro>();
		this.solicitacoesPendentes = new TreeMap<String, NegociacaoDePontoDeEncontro>();
	}
	
	/**
	 * Retorna a lista de solicitacoes pendentes
	 * @param idCarona O id da carona
	 * @param idCarona2 
	 * @return A lista de solicitacoes
	 */
	public List<String> getSolicitacoesPendentes(String idSessao, String idCarona){
		List<String> listaSolicitacoesPendentes = new ArrayList<String>();
		if(!solicitacoesPendentes.isEmpty()){
			listaSolicitacoesPendentes.add(solicitacoesPendentes.get(idSessao).getId());
		}
		return listaSolicitacoesPendentes;
	}
	/**
	 * Remove uma solicitacao pendente da lista
	 * @param idCarona A solicitacao a ser removida
	 */
	public void removerSolicitacoesPendentes(String idCarona){
		
		solicitacoesPendentes.remove(idCarona);
	
	}
	/**
	 * Retorna a lista de solicitacoes confirmadas
	 * @param idSessao O id da sessao do usuario
	 * @param idCarona O id da carona
	 * @return A lista de solicitacoes confirmadas
	 */
	public List<String> getSolicitacoesConfirmadas(String idSessao, String idCarona){
		List<String> listaSolicitacoesConfirmadas = new ArrayList<String>();
		if(!solicitacoesConfirmadas.isEmpty())
			listaSolicitacoesConfirmadas.add(solicitacoesConfirmadas.get(idSessao).getId());
		return listaSolicitacoesConfirmadas;
	}

	/**
	 * Adiciona uma sugestao de um ponto de encontro para uma carona
	 * @param idSessao O id da sessao do usuario dono da sugestao
	 * @param idCarona O id da carona
	 * @param pontos Os pontos que estao sendo sugeridos
	 * @return O id da sugestao
	 * @throws PontoInvalidoException Excecao de PontoInvalido
	 */
	public String addSugestaoPontoDeEncontro(String idSessao, String idCarona, String pontos) throws PontoInvalidoException {
		NegociacaoDePontoDeEncontro sugestao = new NegociacaoDePontoDeEncontro(idCarona,idSessao,pontos);
		if(naoEstaEmPontosDeEmbarque(sugestao)) throw new PontoInvalidoException();
		sugestoesPontoDeEncontro.add(sugestao);
		
		return sugestao.getId();
	}
	/**
	 * Responde uma sugestao de ponto de encontro
	 * @param idSessao O id da sessao do usuario que esta respondendo
	 * @param idCarona O id da carona
	 * @param idSugestao O id da sugestao a ser respondida
	 * @param pontos Os pontos de encontro da resposta
	 * @return O id da resposta
	 */
	public String responderSugestaoPontoEncontro(String idSessao, String idCarona, String idSugestao, String pontos) {
		NegociacaoDePontoDeEncontro sugestaoResposta = new NegociacaoDePontoDeEncontro(idCarona, idSessao, pontos);
		respostasSugestaoPontoEncontro.add(sugestaoResposta);
		return sugestaoResposta.getId();
	}
	/**
	 * Remove uma solicitacao que foi aceita
	 * @param solicitacao a solicitacao a ser removida
	 */
	public void removerSolicitacaoAceita(NegociacaoDePontoDeEncontro solicitacao){
		solicitacoesPendentes.values().remove(solicitacao);
	}
	/**
	 * Adiciona uma solicitacao a lista de solicitacoes
	 * @param idSessao O id da sessao do usuario q fez a solicitacao
	 * @param idCarona O id da carona a qual a solicitacao se refere
	 * @param ponto O ponto de encontro
	 * @return O id da solicitacao
	 * @throws PontoInvalidoException Excecao de PontoInvalido
	 */
	public String addSolicitacaoDeCarona(String idSessao, String idCarona, String ponto) throws PontoInvalidoException {
		NegociacaoDePontoDeEncontro solicitacao = new NegociacaoDePontoDeEncontro(idCarona, idSessao, ponto);

		solicitacoesDeCarona.add(solicitacao);
		solicitacoesPendentes.put(idCarona, solicitacao);
		
		return solicitacao.getId().toString();
	}
	/**
	 * Metodo acessor para a lista de sugestoes
	 * @return A lista de sugestoes
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
	 * Retorna uma solicitacao pendente a partir de um id de solicitacao
	 * @param idSolicitacao O id da solicitacao
	 * @return A solicitacao
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
	 * Retorna o atributo da solicitacao pedido
	 * @param idSolicitacao O id da solicitacao
	 * @param atributo O atributo a ser procurado
	 * @return O valor do atributo
	 * @throws Exception Excecao de atributo invalido
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
	 * Metodo resposavel por efetivar a desistencia de uma requisicao
	 * @param idSessao O id da sessao do desistente
	 * @param idCarona O id da carona
	 * @param idSugestao O id da sugestao
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
	 * Metodo que verifica se existe alguma sugestao nos pontosDeEmbarque, se
	 * ela tem o mesmo id de carona na negociacao que esta sendo procurada e se
	 * ela contem os pontos dessa negociacao
	 * 
	 * @param negociacao A negociacao
	 * @return True caso contenha e False caso contrario
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
	 * Adiciona solicitacoes confirmadas no mapa de solicitacoes
	 * @param idSessao O id da sessao do usuario
	 * @param solicitacao A solicitacao a ser adicionada
	 */
	public void addSolicitacoesConfirmadas(String idSessao,NegociacaoDePontoDeEncontro solicitacao) {
		solicitacoesConfirmadas.put(idSessao, solicitacao);
	}
	/**
	 * Retorna uma String com os pontos de embarque
	 * @param idCarona O id da carona
	 * @return O string dos pontos de embarque
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
	 * Retorna os pontos sugeridos para uma carona
	 * @param idSessao O id da sessao do usuario
	 * @param idCarona O id da carona
	 * @return A lista de pontos sugeridos
	 * @throws Exception Excecao de CaronaInexistente
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
