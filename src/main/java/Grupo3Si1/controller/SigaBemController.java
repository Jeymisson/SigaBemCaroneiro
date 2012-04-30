package Grupo3Si1.controller;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;


import Grupo3Si1.exceptions.*;
import Grupo3Si1.handles.*;

public class SigaBemController {

	Usuario user;
	RepositorioDeUsuarios rep;
	ControladorDeNegociacoes controladorDeNegociacoes;
	private final String USERS_FILE = "src/main/resources/usuarios.xml";
	private final String NEGOCIACOES_FILE = "src/main/resources/negociacoes.xml";
	
	//Map <idUser, User>
	AbstractMap<String, Usuario> sessoesAbertas;
	//GetAtributos getAtb;

	public SigaBemController() {
		rep = RepositorioDeUsuarios.getInstance();
		sessoesAbertas  = new TreeMap<String, Usuario>();
		controladorDeNegociacoes = new ControladorDeNegociacoes();
		//getAtb = new GetAtributos();
	}

	//Metodos Construtores de usuario
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email)throws Exception{


		user = new UsuarioSimples(login, senha, nome, endereco, email);
		rep.addUser(login, user);

	}

	public void criarUsuario(String login, String nome,
			String endereco, String email) throws Exception{

		criarUsuario(login,"",nome,endereco,email);

	}

	public void criarUsuario(String login, String nome,
			String endereco) throws Exception{

		criarUsuario(login,"",nome,endereco,String.valueOf(login.hashCode()));

	}

	//Metodos Get's
	public String getAtributoCarona(String idCarona, String atributo)throws Exception {

		if(idCarona == null || idCarona.equals("")) throw new IdentificadorCaronaInvalidoException();
		if(!idCarona.matches("\\d+")) throw new InexistenteItemException();

		if(AtributosDeCarona.PONTO_DE_ENCONTRO.getatribute().equalsIgnoreCase(atributo)){
			return controladorDeNegociacoes.getPontosDeEmbarque(idCarona);
		}
		
		Carona carona = rep.getCarona(idCarona);
		return carona.getAtributo(atributo);

	}

	public String getAtributoUsuario(String login, String atributo) throws Exception {
		Usuario user = rep.getUser(login);
		return user.getAtributo(atributo);

	}

	public String getTrajeto(String idCarona) throws Exception {

		if(idCarona == null) throw new TrajetoInvalidoException();
		if(!idCarona.matches("\\d+")) throw new TrajetoInexistenteException();

		Carona carona = rep.getCarona(idCarona);
		return carona.getTrajeto();
	}

	public String getCarona(String idCarona) throws Exception {

		if(idCarona == null) throw new CaronaInvalidaException();
		if(!idCarona.matches("\\d+")) throw new CaronaInexistenteException();

		Carona carona = rep.getCarona(idCarona);
		return carona.getCarona();
	}


	//Métodos de negociação de carona
	public String getAtributoSolicitacao(String idSolicitacao, String atributo)throws Exception {
		return controladorDeNegociacoes.getAtributoSolicitacao(idSolicitacao, atributo);
	}

	public String sugerirPontoEncontro(String idSessao, String idCarona, String pontos) throws Exception {
		//Carona carona = rep.getCarona(idCarona);
		return controladorDeNegociacoes.addSugestaoCarona(idSessao, idCarona, pontos);
	}

	public String responderSugestaoPontoEncontro(String idSessao, String idCarona, String idSugestao, String pontos) throws PontoInvalidoException {
		if(pontos.equals("")) throw new PontoInvalidoException();

		//Carona carona = rep.getCarona(idCarona);	
		return controladorDeNegociacoes.responderSugestaoPontoEncontro(idSessao, idCarona, idSugestao, pontos);
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona, String ponto)throws Exception {
		//Carona carona = rep.getCarona(idCarona);
		return controladorDeNegociacoes.addSolicitacaoDeCarona(idSessao, idCarona, ponto);
	}

	public String solicitarVaga(String idSessao, String idCarona) throws PontoInvalidoException{
		return controladorDeNegociacoes.addSolicitacaoDeCarona(idSessao, idCarona, "");//TODO VER UM JEITO BONITO PRA ESSE PONTO VAZIO.
	}
	public void aceitarSolicitacao(String idSessao, String idSolicitacao) throws Exception{
		aceitarSolicitacaoPontoEncontro(idSessao, idSolicitacao);
	}
	/**
	 * 
	 * @param idSessao Sessao do Usuario a verificar os pontos de encontro
	 * @param idCarona Id da Carona do usuario a qual os pontos sugeridos serao buscados
	 * @return String com pontos sugeridos da forma {PontodeEncontro1;PontodeEncontro2}
	 * @throws Exception
	 */
	public String getPontosSugeridos(String idSessao, String idCarona) throws Exception{
		String respPontos = "{";
		List<PontoDeEncontro> listaPontosSugeridos = controladorDeNegociacoes.getPontosSugeridos(idSessao, idCarona);
		Iterator<PontoDeEncontro> pontosIt = listaPontosSugeridos.iterator();
		while(pontosIt.hasNext()){
			PontoDeEncontro nextPonto = pontosIt.next();
			if(pontosIt.hasNext()){					//verifica se está no fim da lista para mudar a concatenação
				respPontos += nextPonto.getNome()+";";
			}else
				respPontos += nextPonto.getNome()+"}"; // se está no fim da lista concatena de forma diferente.
		}
		return respPontos;
		
	}

	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws SolicaticaoInexistenteException{
		NegociacaoDePontoDeEncontro solicitacao = controladorDeNegociacoes.getSolicitacaoPendentePorId(idSolicitacao);
		if(solicitacao == null) throw new SolicaticaoInexistenteException();
		
		controladorDeNegociacoes.removerSolicitacaoAceita(solicitacao);


	}
	public void aceitarSolicitacaoPontoEncontro(String idSessao, String idSolicitacao) throws Exception{
		NegociacaoDePontoDeEncontro solicitacao = controladorDeNegociacoes.getSolicitacaoPendentePorId(idSolicitacao);
		if(solicitacao == null) throw new SolicaticaoInexistenteException();
		
		Usuario usuarioDonoDaSolicitacao = rep.getUserPorId(solicitacao.getIdSessao());
		Carona carona = rep.getCarona(solicitacao.getIdCarona());
		
		usuarioDonoDaSolicitacao.getPerfil().addMeuHistorico(carona);
		
		if(rep.getDonoDe(carona.getId()).getUserID().equals(idSessao)){
			carona.preencheVagas();
//			controladorDeNegociacoes.removerSolicitacaoAceita(solicitacao);
			controladorDeNegociacoes.removerSolicitacoesPendentes(carona.getId());
			controladorDeNegociacoes.addSolicitacoesConfirmadas(idSessao,solicitacao);
		}else
			throw new SolicaticaoInexistenteException();

	}

	public void desistirRequisicao(String idSessao, String idCarona, String idSugestao){
		controladorDeNegociacoes.desistirRequisicao(idSessao, idCarona, idSugestao);
	}

	
	
	public Carona getCaronaUsuario(String idSessao, int index){
		return rep.getUserPorId(idSessao).getPerfil().getCaronas().get(index - 1);//index - 1,pois o array comeca em 0.
	}
	public String getTodasCaronasUsuario(String idSessao){
		List<String> listaIdsCarona = new ArrayList<String>();
		List<Carona> listaCaronas = rep.getUserPorId(idSessao).getPerfil().getCaronas();
		
		for (Carona caronas : listaCaronas) {
			listaIdsCarona.add(caronas.getId());
		}
		return listaCaronas.toString().replace("[", "{").replace("]", "}").replace(" ", "");
	}

	public String getSolicitacoesPendentes(String idCarona){
		return (controladorDeNegociacoes.getSolicitacoesPendentes(idCarona).toString()).replace("[", "{").replace("]", "}");
	}
	
	public String  getSolicitacoesConfirmadas(String idSessao, String idCarona){
		return (controladorDeNegociacoes.getSolicitacoesConfirmadas(idSessao,idCarona ).toString()).replace("[","").replace("]","");
	}
	//Final de métodos de negóciação de carona

	/**
	 * Método que zera o sistema, deleta tudo que existe no arquivo de dados
	 */
	public void zerarSistema(){
		rep.clear();
		GerenciaDadosEmXML gerenciaDadosEmXML = new GerenciaDadosEmXML();
		gerenciaDadosEmXML.zeraArquivo(USERS_FILE);
		gerenciaDadosEmXML.zeraArquivo(NEGOCIACOES_FILE);

	}

	/**
	 * Método que encerra o sistema e salva os dados em arquivo
	 */
	public void encerrarSistema(){
		GerenciaDadosEmXML gerenciadorDeDados = new GerenciaDadosEmXML();
		gerenciadorDeDados.salvaUsuariosXML(USERS_FILE, rep.getRepositorio());
		gerenciadorDeDados.salvaNegociacoesXML(NEGOCIACOES_FILE, controladorDeNegociacoes);
	}

	/**
	 * Método que reinicia sistema e carrega os dados de um arquivo XML
	 */
	public void reiniciarSistema(){
		GerenciaDadosEmXML gerenciaDadosEmXML = new GerenciaDadosEmXML();
		rep.setRepositorio(gerenciaDadosEmXML.getRepositorioUsuarios(USERS_FILE));
		controladorDeNegociacoes = gerenciaDadosEmXML.getControladorDeNegociacoes(NEGOCIACOES_FILE);
	}

	public String abrirSessao(String login, String senha)
			throws Exception {

		Usuario user = rep.getUser(login);
		String id = null;

		if (senha.equals(user.getSenha()) || user.getSenha().equals("")) {
			id = user.getUserID();
			sessoesAbertas.put(id,user);

		}else
			throw new InvalidLoginException();

		return id;
	}

	public void encerrarSessao(String login) throws Exception {
		//TODO TRATAR ENTRADA PARA USUARIO NAO LOGADO
		Usuario user = rep.getUser(login);
		sessoesAbertas.remove(user.getUserID());

		//refatorando
		/*Iterator<Usuario> iterator = sessoesAbertas.values().iterator();
		  while(iterator.hasNext()){
		   Usuario usuario = iterator.next();
		   if(usuario.getLogin().equals(login)){
		    String id = usuario.getUserID();
		    sessoesAbertas.remove(id);
		    break;
		   }
		  }
		 */
	}

	/**
	 * Cadastra uma Carona
	 * @param id ID da sessão do Usuário
	 * @param origem Origem da Carona
	 * @param destino Destino da Carona
	 * @param data Data da Carona
	 * @param hora Hora da Carona
	 * @param vagas Vagas da Carona
	 * @return ID da Carona Cadastrada
	 * @throws Exception
	 */
	public int cadastrarCarona(String id, String origem, String destino, String data, String  hora, String vagas) throws Exception{
		if (id == null || id.equals("")) throw new SessaoInvalidaException();
		if(vagas == null) throw new VagaInvalidaException();
		if(!vagas.matches("\\d+")) throw new VagaInvalidaException();

		Usuario user = sessoesAbertas.get(id);
		if(user == null) throw new SessaoInexistenteException();
		return user.cadastraCarona(origem,destino,data,hora,Integer.valueOf(vagas));
	}

	public String localizarCarona(String idSessao, String origem, String destino) throws Exception{
		Usuario user = sessoesAbertas.get(idSessao);
		List<Carona> caronas = user.localizaCarona(origem, destino);
		return caronas.toString().replace("[", "{").replace("]", "}").replace(" ", "");
	}

	public String getAtributoPerfil(String login, String atributo) throws Exception{
		Usuario user = rep.getUser(login);
		if(user == null){
			return "";
		}
		
		return user.getPerfil().getAtributoPerfil(atributo);
	}

	public void visualizarPerfil(String idsessao, String login) throws InvalidLoginException{
		Usuario user = rep.getUserPorId(idsessao);
		if(!user.getLogin().trim().equals(login.trim())){
			throw new InvalidLoginException();
		}
	}
	public void reviewCarona(String idSessao, String idCarona, String review) throws Exception{
	
		Usuario usuario = rep.getDonoDe(idCarona);
		
		if(usuario != null){
			List<String> list = controladorDeNegociacoes.getSolicitacoesConfirmadas(usuario.getUserID(), idCarona);
			if(!list.contains(idSessao))
				throw new NaoPossuiVagasException();
			usuario.getPerfil().reviewVagaEmCarona(review);

		}
	}
	
	public void reviewVagaEmCarona(String idSessao, String idCarona, String loginCaroneiro, String review) throws Exception{
		
		Usuario userReview = rep.getUser(loginCaroneiro);
		List<String> vagasConfirmadas = controladorDeNegociacoes.getSolicitacoesConfirmadas(idSessao, idCarona);

		if(!vagasConfirmadas.contains(userReview.getUserID()))
			throw new NaoPossuiVagasException();
		
		userReview.getPerfil().reviewVagaEmCarona(review);//TODO metodo usa 4 parametros mais foi feito com apenas 2
														  //usa os outros parametros para tratar ?
	}
	public int cadastrarCaronaMunicipal(String idSessao, String origem,String destino, String cidade,String data,String hora,String vagas) throws Exception{
		if (idSessao == null || idSessao.equals("")){
			throw new SessaoInvalidaException();
		}
		if(vagas == null){
			throw new VagaInvalidaException();
		}
		if(!vagas.matches("\\d+")) throw new VagaInvalidaException();

		Usuario user = sessoesAbertas.get(idSessao);
		if(user == null) throw new SessaoInexistenteException();
		
		return user.cadastraCarona(idSessao, origem, destino, cidade, data, hora, vagas);
		
	}
}
