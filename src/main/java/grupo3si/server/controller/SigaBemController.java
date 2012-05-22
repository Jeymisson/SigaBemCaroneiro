package grupo3si.server.controller;

import grupo3si.server.model.AtributosDeCarona;
import grupo3si.server.model.AtributosDePerfil;
import grupo3si.server.model.Carona;
import grupo3si.server.model.CaronaInexistenteException;
import grupo3si.server.model.CaronaInvalidaException;
import grupo3si.server.model.ControladorDeNegociacoes;
import grupo3si.server.model.GerenciaDadosEmXML;
import grupo3si.server.model.IdentificadorCaronaInvalidoException;
import grupo3si.server.model.InexistentAtributeException;
import grupo3si.server.model.InexistenteItemException;
import grupo3si.server.model.InvalidAtributeException;
import grupo3si.server.model.InvalidLoginException;
import grupo3si.server.model.NaoPossuiVagasException;
import grupo3si.server.model.NegociacaoDePontoDeEncontro;
import grupo3si.server.model.PontoDeEncontro;
import grupo3si.server.model.PontoInvalidoException;
import grupo3si.server.model.RepositorioDeUsuarios;
import grupo3si.server.model.SessaoInexistenteException;
import grupo3si.server.model.SessaoInvalidaException;
import grupo3si.server.model.SolicaticaoInexistenteException;
import grupo3si.server.model.TrajetoInexistenteException;
import grupo3si.server.model.TrajetoInvalidoException;
import grupo3si.server.model.Usuario;
import grupo3si.server.model.UsuarioSimples;
import grupo3si.server.model.VagaInvalidaException;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class SigaBemController {

	Usuario user;
	RepositorioDeUsuarios rep;
	ControladorDeNegociacoes controladorDeNegociacoes;
	private final String USERS_FILE = "src/main/resources/usuarios.xml";
	private final String NEGOCIACOES_FILE = "src/main/resources/negociacoes.xml";

	//Map <idUser, User>
	AbstractMap<String, Usuario> sessoesAbertas;
	
	/**
	 * Construtor de SigaBemController
	 */
	public SigaBemController() {
		rep = RepositorioDeUsuarios.getInstance();
		sessoesAbertas  = new TreeMap<String, Usuario>();
		controladorDeNegociacoes = new ControladorDeNegociacoes();
		
	}

	/**
	 * 	Metodo que cria um usuario e adiciona no repositorio.
	 * @param login
	 * @param senha
	 * @param nome
	 * @param endereco
	 * @param email
	 * @throws Exception
	 */
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email)throws Exception{


		user = new UsuarioSimples(login, senha, nome, endereco, email);
		rep.addUser(login, user);

	}
	/**
	 * Metodo que cria um usuario e adiciona no repositorio.
	 * @param login
	 * @param nome
	 * @param endereco
	 * @param email
	 * @throws Exception
	 */
	public void criarUsuario(String login, String nome,
			String endereco, String email) throws Exception{

		criarUsuario(login,"",nome,endereco,email);

	}
	/**
	 * Metodo que cria um usuario sem senha e adiciona no repositorio
	 * @param login
	 * @param nome
	 * @param endereco
	 * @throws Exception
	 */
	public void criarUsuario(String login, String nome,
			String endereco) throws Exception{

		criarUsuario(login,"",nome,endereco,String.valueOf(login.hashCode()));

	}

	/**
	 * Metodo que retorna os atributos da classe carona.
	 * @param idCarona
	 * @param atributo
	 * @return String atributo
	 * @throws Exception
	 */
	public String getAtributoCarona(String idCarona, String atributo)throws Exception {

		if(idCarona == null || idCarona.equals("")) throw new IdentificadorCaronaInvalidoException();
		if(!idCarona.matches("\\d+")) throw new InexistenteItemException();

		if(AtributosDeCarona.PONTO_DE_ENCONTRO.getatribute().equalsIgnoreCase(atributo)){
			return controladorDeNegociacoes.getPontosDeEmbarque(idCarona);
		}
		Carona carona = rep.getCarona(idCarona);
		if(atributo == null || atributo.equals("")){
			throw new InvalidAtributeException();
		}
		
		String resp = null;
		
		if(this == null){
			throw new InexistenteItemException();
		}

		if (AtributosDeCarona.ORIGEM.getatribute().equalsIgnoreCase(atributo)) {
			resp = carona.getOrigem();
		} else if (AtributosDeCarona.DATA.getatribute().equalsIgnoreCase(atributo)) {
			resp = carona.getData();
		} else if (AtributosDeCarona.DESTINO.getatribute().equalsIgnoreCase(atributo)) {
			resp = carona.getDestino();
		}else if (AtributosDeCarona.VAGA.getatribute().equalsIgnoreCase(atributo)) {
			resp = String.valueOf(carona.getVagas());
		}else if(AtributosDeCarona.HORA.getatribute().equalsIgnoreCase(atributo)) {
			resp = String.valueOf(carona.getHora());
		}else if(AtributosDeCarona.EHMUNICIPAL.getatribute().equalsIgnoreCase(atributo)){
			resp = carona.ehMunicipal()+"";
		}else
			throw new InexistentAtributeException();

		return resp;

	}
	/**
	 * Metodo que retorna os atributos da classe usuario
	 * @param login
	 * @param atributo
	 * @return String atributo
	 * @throws Exception
	 */
	public String getAtributoUsuario(String login, String atributo) throws Exception {
	
		Usuario user = rep.getUser(login);
		String resp = "";
		if(atributo == null || atributo.equals("")){
			throw new InvalidAtributeException();
		}
		
		if (AtributosDePerfil.NOME.getAtribute().equalsIgnoreCase(atributo)) {
			resp = user.getNome();
		} else if (AtributosDePerfil.ENDERECO.getAtribute().equalsIgnoreCase(
				atributo)) {
			resp = user.getEndereco();
		} else if (AtributosDePerfil.EMAIL.getAtribute().equalsIgnoreCase(atributo)) {
			resp = user.getEmail();
		}else
			throw new InexistentAtributeException();

		return resp;

	}
	
	/**
	 * Metodo que retorna o trajeto que uma carona vai fazer.
	 * @param idCarona
	 * @return String trajeto
	 * @throws Exception
	 */
	public String getTrajeto(String idCarona) throws Exception {

		if(idCarona == null) throw new TrajetoInvalidoException();
		if(!idCarona.matches("\\d+")) throw new TrajetoInexistenteException();

		Carona carona = rep.getCarona(idCarona);
		return carona.getTrajeto();
	}

	/**
	 * Metodo que retorna o ID de uma carona
	 * @param idCarona
	 * @return String Carona
	 * @throws Exception
	 */
	public String getCarona(String idCarona) throws Exception {

		if(idCarona == null) throw new CaronaInvalidaException();
		if(!idCarona.matches("\\d+")) throw new CaronaInexistenteException();

		Carona carona = rep.getCarona(idCarona);
		return carona.getCarona();
	}

	/**
	 * Metodo que retorna os atributos de uma solicitação.
	 * @param idSolicitacao
	 * @param atributo
	 * @return String Atributo
	 * @throws Exception
	 */
	public String getAtributoSolicitacao(String idSolicitacao, String atributo)throws Exception {
		return controladorDeNegociacoes.getAtributoSolicitacao(idSolicitacao, atributo);
	}
	
	
	/**
	 * Metodo que sugeri um ponto de encontro.
	 * @param idSessao
	 * @param idCarona
	 * @param pontos
	 * @return String sugestao
	 * @throws Exception
	 */
	public String sugerirPontoEncontro(String idSessao, String idCarona, String pontos) throws Exception {
		
		return controladorDeNegociacoes.addSugestaoPontoDeEncontro(idSessao, idCarona, pontos);
	}
	
	/**
	 * Metodo que responde a uma sugestão de ponto de encontro.
	 * @param idSessao
	 * @param idCarona
	 * @param idSugestao
	 * @param pontos
	 * @return String resposta
	 * @throws PontoInvalidoException
	 */
	public String responderSugestaoPontoEncontro(String idSessao, String idCarona, String idSugestao, String pontos) throws PontoInvalidoException {
		if(pontos.equals("")) throw new PontoInvalidoException();

			
		return controladorDeNegociacoes.responderSugestaoPontoEncontro(idSessao, idCarona, idSugestao, pontos);
	}
	
	/**
	 * Metodo que solicita uma vaga em um ponto de encontro determinado.
	 * @param idSessao
	 * @param idCarona
	 * @param ponto
	 * @return String solicitacao
	 * @throws Exception
	 */
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona, String ponto)throws Exception {
		
		return controladorDeNegociacoes.addSolicitacaoDeCarona(idSessao, idCarona, ponto);
	}

	/**
	 * Metodo que solicita vaga em uma carona.
	 * @param idSessao
	 * @param idCarona
	 * @return String solicitacao
	 * @throws PontoInvalidoException
	 */
	public String solicitarVaga(String idSessao, String idCarona) throws PontoInvalidoException{
		return controladorDeNegociacoes.addSolicitacaoDeCarona(idSessao, idCarona, "");
	}
	/**
	 * Metodo que aceita uma solicitação feita.
	 * @param idSessao
	 * @param idSolicitacao
	 * @throws Exception
	 */
	public void aceitarSolicitacao(String idSessao, String idSolicitacao) throws Exception{
		aceitarSolicitacaoPontoEncontro(idSessao, idSolicitacao);
	}
	/**
	 * 
	 * @param idSessao Sessao do Usuario a verificar os pontos de encontro
	 * @param idCarona Id da Carona do usuario a qual os pontos sugeridos serao buscados
	 * @return String com pontos sugeridos da forma [PontodeEncontro1;PontodeEncontro2]
	 * @throws Exception
	 */
	public String getPontosSugeridos(String idSessao, String idCarona) throws Exception{
		List<PontoDeEncontro> listaPontosSugeridos = controladorDeNegociacoes.getPontosSugeridos(idSessao, idCarona);
		return listaPontos(listaPontosSugeridos);	
	}

	/**
	 * Metodo auxiliar que gera pontos de encontros.
	 * @param listaPontosDeEncontro
	 * @return String lista de pontos
	 */
	private String listaPontos(List<PontoDeEncontro> listaPontosDeEncontro) {
		String respPontos = "";
		Iterator<PontoDeEncontro> pontosIt = listaPontosDeEncontro.iterator();
		while(pontosIt.hasNext()){
			PontoDeEncontro nextPonto = pontosIt.next();
			if(pontosIt.hasNext()){	
				respPontos += nextPonto.getNome()+";";
			}else
				respPontos += nextPonto.getNome();
		}
		return respPontos;
	}
	
	/**
	 * Metodo que retorna uma string com pontos de encontro.
	 * @param idSessao
	 * @param idCarona
	 * @return String pontos
	 * @throws Exception
	 */
	public String getPontosEncontro(String idSessao, String idCarona)throws Exception{
		return getPontosSugeridos(idSessao, idCarona);

	}

	/**
	 * Metodo que rejeita uma solicitação feita.
	 * @param idSessao
	 * @param idSolicitacao
	 * @throws SolicaticaoInexistenteException
	 */
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws SolicaticaoInexistenteException{
		NegociacaoDePontoDeEncontro solicitacao = controladorDeNegociacoes.getSolicitacaoPendentePorId(idSolicitacao);
		if(solicitacao == null) throw new SolicaticaoInexistenteException();

		controladorDeNegociacoes.removerSolicitacaoAceita(solicitacao);


	}
	
	/**
	 * Metodo que aceita uma solicitação em um ponto de encontro.
	 * @param idSessao
	 * @param idSolicitacao
	 * @throws Exception
	 */
	public void aceitarSolicitacaoPontoEncontro(String idSessao, String idSolicitacao) throws Exception{
		NegociacaoDePontoDeEncontro solicitacao = controladorDeNegociacoes.getSolicitacaoPendentePorId(idSolicitacao);
		if(solicitacao == null) throw new SolicaticaoInexistenteException();

		Usuario usuarioDonoDaSolicitacao = rep.getUserPorId(solicitacao.getIdSessao());
		Carona carona = rep.getCarona(solicitacao.getIdCarona());

		usuarioDonoDaSolicitacao.getPerfil().addMeuHistorico(carona);

		if(rep.getDonoDe(carona.getId()).getUserID().equals(idSessao)){
			carona.preencheVagas();
			controladorDeNegociacoes.removerSolicitacoesPendentes(carona.getId());
			controladorDeNegociacoes.addSolicitacoesConfirmadas(idSessao,solicitacao);
		}else
			throw new SolicaticaoInexistenteException();

	}
	/**
	 * Metodo que resiste de uma vaga em carona.
	 * @param idSessao
	 * @param idCarona
	 * @param idSugestao
	 */
	public void desistirRequisicao(String idSessao, String idCarona, String idSugestao){
		controladorDeNegociacoes.desistirRequisicao(idSessao, idCarona, idSugestao);
	}


	/**
	 * Metodo que retorna uma carona de um usuario.
	 * @param idSessao
	 * @param index
	 * @return String de caronas
	 */
	public Carona getCaronaUsuario(String idSessao, int index){
		return rep.getUserPorId(idSessao).getPerfil().getHistoricoDeCaronas().get(index - 1);//index - 1,pois o array comeca em 0.
	}
	
	/**
	 * Metodo que retorna uma String formatada com todas as carona de um usuario.
	 * @param idSessao
	 * @return String Caronas
	 */
	public String getTodasCaronasUsuario(String idSessao){
		List<String> listaIdsCarona = new ArrayList<String>();
		List<Carona> listaCaronas = rep.getUserPorId(idSessao).getPerfil().getHistoricoDeCaronas();

		for (Carona caronas : listaCaronas) {
			listaIdsCarona.add(caronas.getId());
		}
		return listaCaronas.toString().replace("[", "{").replace("]", "}").replace(" ", "");
	}
	
	/**
	 * Metodo que retorna as solicitaçoes pendentes de uma carona.
	 * @param idCarona
	 * @return String solicitações pendentes
	 */
	public String getSolicitacoesPendentes(String idCarona){
		return (controladorDeNegociacoes.getSolicitacoesPendentes(idCarona).toString()).replace("[", "{").replace("]", "}");
	}

	/**
	 * Metodo que retorna as solicitaçoes confirmadas de uam carona.
	 * @param idSessao
	 * @param idCarona
	 * @return String solicitações confirmadas
	 */
	public String  getSolicitacoesConfirmadas(String idSessao, String idCarona){
		return (controladorDeNegociacoes.getSolicitacoesConfirmadas(idSessao,idCarona ).toString()).replace("[","").replace("]","");
	}
	//Final de métodos de negóciação de carona

	/**
	 * Método que zera o sistema, deleta tudo que existe no arquivo de dados
	 */
	public void zerarSistema(){
		rep.clear();
		sessoesAbertas.clear();
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
		rep.clear();
		sessoesAbertas.clear();
	}

	/**
	 * Método que reinicia sistema e carrega os dados de um arquivo XML
	 */
	public void reiniciarSistema(){
		GerenciaDadosEmXML gerenciaDadosEmXML = new GerenciaDadosEmXML();
		rep.setRepositorio(gerenciaDadosEmXML.getRepositorioUsuarios(USERS_FILE));
		controladorDeNegociacoes = gerenciaDadosEmXML.getControladorDeNegociacoes(NEGOCIACOES_FILE);
	}

	/**
	 * Metodo que abre uma sessão.
	 * @param login
	 * @param senha
	 * @return String id
	 * @throws Exception
	 */
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

	/**
	 * Metodo que encerra uma sessão.
	 * @param login
	 * @throws Exception
	 */
	public void encerrarSessao(String login) throws Exception {
		Usuario user = rep.getUser(login);
		sessoesAbertas.remove(user.getUserID());

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

	/**
	 * Metodo que localiza uma carona 
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @return String caronas
	 * @throws Exception
	 */
	public String localizarCarona(String idSessao, String origem, String destino) throws Exception{
		Usuario user = sessoesAbertas.get(idSessao);
		List<Carona> caronas = user.localizaCarona(origem, destino);
		return caronas.toString().replace("[", "{").replace("]", "}").replace(" ", "");
	}

	/**
	 * Metodo que retorna os atributos de uma carona.
	 * @param login
	 * @param atributo
	 * @return String atributo
	 * @throws Exception
	 */
	public String getAtributoPerfil(String login, String atributo) throws Exception{
		Usuario user = rep.getUser(login);
		String resp = "";
		if(atributo == null || atributo.equals("")){
			throw new InvalidAtributeException();
		}
		
		if (AtributosDePerfil.NOME.getAtribute().equalsIgnoreCase(atributo)) {
			resp = user.getNome();
		} else if (AtributosDePerfil.ENDERECO.getAtribute().equalsIgnoreCase(
				atributo)) {
			resp = user.getEndereco();
		} else if (AtributosDePerfil.EMAIL.getAtribute().equalsIgnoreCase(atributo)) {
			resp = user.getEmail();
		}else  if(AtributosDePerfil.CARONASNAOFUNCIONOU.getAtribute().equalsIgnoreCase(atributo)){
			resp = user.getCaronafaltosas()+"";
		}else if(AtributosDePerfil.CARONASSEGURAS.getAtribute().equalsIgnoreCase(atributo)){
			resp = user.getCaronasSeguras()+"";
		}else if(AtributosDePerfil.FALTAEMVAGASDECARONAS.getAtribute().equalsIgnoreCase(atributo)){
			resp = user.getFaltaEmVagaDeCarona()+"";
		}else if(AtributosDePerfil.HISTORICODECARONAS.getAtribute().equalsIgnoreCase(atributo)){
			resp = (user.getHistoricoDeCaronas()+"").replaceAll(" ","");
		}else if (AtributosDePerfil.HISTORICOVAGASCARONAS.getAtribute().equalsIgnoreCase(atributo)){
			resp = (user.getHistoricoEmVagasDeCaronas()+"").replaceAll(" ", "");
		}else if(AtributosDePerfil.PRESENCAEMVAGASDECARONA.getAtribute().equalsIgnoreCase(atributo)){
			resp = user.getPresencaEmVagaDeCarona()+"";
		}else
			throw new InexistentAtributeException();

		return resp;
	}

	/**
	 *Metodo que vizualiza um perfil. 
	 * @param idsessao
	 * @param login
	 * @throws InvalidLoginException
	 */
	public void visualizarPerfil(String idsessao, String login) throws InvalidLoginException{
		Usuario user = rep.getUserPorId(idsessao);
		if(!user.getLogin().trim().equals(login.trim())){
			throw new InvalidLoginException();
		}
	}
	
	/**
	 * Metodo que adiciona um comentario sobre uma carona.
	 * @param idSessao
	 * @param idCarona
	 * @param review
	 * @throws Exception
	 */
	public void reviewCarona(String idSessao, String idCarona, String review) throws Exception{

		Usuario usuario = rep.getDonoDe(idCarona);

		if(usuario != null){
			List<String> list = controladorDeNegociacoes.getSolicitacoesConfirmadas(usuario.getUserID(), idCarona);
			if(!list.contains(idSessao))
				throw new NaoPossuiVagasException();
			usuario.getPerfil().reviewVagaEmCarona(review);

		}
	}

	/**
	 * Metodo que adiciona um comentario em uma carona que o usuario pegou.
	 * @param idSessao
	 * @param idCarona
	 * @param loginCaroneiro
	 * @param review
	 * @throws Exception
	 */
	public void reviewVagaEmCarona(String idSessao, String idCarona, String loginCaroneiro, String review) throws Exception{

		Usuario userReview = rep.getUser(loginCaroneiro);
		List<String> vagasConfirmadas = controladorDeNegociacoes.getSolicitacoesConfirmadas(idSessao, idCarona);
		List<String> idsSolicitacoes = new ArrayList<String>();

		for (int i = 0; i < vagasConfirmadas.size(); i++) {
			idsSolicitacoes.add(controladorDeNegociacoes.getSolicitacaoPorId(vagasConfirmadas.get(i).toString()).getIdSessao());
		}

		if(!idsSolicitacoes.contains(userReview.getUserID()))
			throw new NaoPossuiVagasException();

		userReview.getPerfil().reviewVagaEmCarona(review);

	}

	/**
	 * Metodo que cadastra uma carona Municipal
	 * @param idSessao
	 * @param origem
	 * @param destino
	 * @param cidade
	 * @param data
	 * @param hora
	 * @param vagas
	 * @return String id
	 * @throws Exception
	 */
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