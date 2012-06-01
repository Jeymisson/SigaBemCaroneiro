package grupo3si.server.controller;

import grupo3si.server.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.InvalidNameException;
import javax.naming.directory.InvalidAttributeValueException;

public class FacedEasy {
	
	SigaBemController controller;
	
	public FacedEasy(SigaBemController controller ) {
		this.controller = controller;
	}
	
	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws Exception {
		controller.criarUsuario(login, senha, nome, endereco, email);
	}

	public void criarUsuario(String login, String nome, String endereco, String email) throws Exception {
		controller.criarUsuario(login, "", nome, endereco, email);
	}

	public void criarUsuario(String login, String nome, String endereco)throws Exception {
		controller.criarUsuario(login, nome, endereco);
	}


	public String getAtributoCarona(String idCarona, String atributo)
			throws Exception {
		return controller.getAtributoCarona(idCarona, atributo);
	}



	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
		
		return controller.getAtributoUsuario(login, atributo);

	}
	public String getTrajeto(String idCarona) throws Exception {
		return controller.getTrajeto(idCarona);
	}

	
	public String getCarona(String idCarona) throws Exception {
		return controller.getCarona(idCarona).getCarona();
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo)throws Exception {
		return controller.getAtributoSolicitacao(idSolicitacao, atributo);
	}

	public String sugerirPontoEncontro(String idSessao, String idCarona,String pontos) throws Exception {
		return controller.sugerirPontoEncontro(idSessao, idCarona, pontos).toString();
	}

	public String responderSugestaoPontoEncontro(String idSessao,String idCarona, String idSugestao, String pontos)throws PontoInvalidoException {
		return controller.responderSugestaoPontoEncontro(idSessao, idCarona, idSugestao, pontos);
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,String ponto) throws Exception {
		return controller.solicitarVagaPontoEncontro(idSessao, idCarona, ponto);
	}

	public String solicitarVaga(String idSessao, String idCarona)
			throws PontoInvalidoException {
		return controller.solicitarVaga(idSessao, idCarona);
	}
	public void aceitarSolicitacao(String idSessao, String idSolicitacao)
			throws Exception {
		controller.aceitarSolicitacaoPontoEncontro(idSessao, idSolicitacao);
	}

	public String getPontosSugeridos(String idSessao, String idCarona)throws Exception {
		List<PontoDeEncontro> listaPontosSugeridos = controller.getPontosSugeridos(idSessao, idCarona);
		return listaPontos(listaPontosSugeridos);
	}
	
	protected String listaPontos(List<PontoDeEncontro> listaPontosDeEncontro) {
		String respPontos = "";
		Iterator<PontoDeEncontro> pontosIt = listaPontosDeEncontro.iterator();
		while (pontosIt.hasNext()) {
			PontoDeEncontro nextPonto = pontosIt.next();
			if (pontosIt.hasNext()) {
				respPontos += nextPonto.getNome() + ";";
			} else
				respPontos += nextPonto.getNome();
		}
		return respPontos;
	}


	public String getPontosEncontro(String idSessao, String idCarona)
			throws Exception {
		return getPontosSugeridos(idSessao, idCarona);

	}

	
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao)throws SolicaticaoInexistenteException {
		controller.rejeitarSolicitacao(idSessao, idSolicitacao);
	}

	public void aceitarSolicitacaoPontoEncontro(String idSessao, String idSolicitacao) throws Exception {
		controller.aceitarSolicitacaoPontoEncontro(idSessao, idSolicitacao);
	}

	public void desistirRequisicao(String idSessao, String idCarona, String idSugestao) {
		controller.desistirRequisicao(idSessao, idCarona, idSugestao);
	}

	
	public Carona getCaronaUsuario(String idSessao, int index) {
		return controller.getCaronaUsuario(idSessao, index);
	}

	
	public String getTodasCaronasUsuario(String idSessao) {
		List<String> listaIdsCarona = new ArrayList<String>();
		List<Carona> listaCaronas = controller.getTodasCaronasUsuario(idSessao);

		for (Carona caronas : listaCaronas) {
			listaIdsCarona.add(caronas.getId());
		}
		return listaCaronas.toString().replace("[", "{").replace("]", "}")
				.replace(" ", "");
	}

	public String getSolicitacoesPendentes(String idSessao, String idCarona) {
		String result = controller.getSolicitacoesPendentes(idSessao, idCarona).toString();
		return result.length() > 2 ? result.replace("[", "{").replace("]", "}") : result.replace("[", "").replace("]", "");
	}

	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) {
		String result = controller.getSolicitacoesConfirmadas(idSessao, idCarona).toString();
		return result.length() > 2 ? result.replace("[", "{").replace("]", "}") : result.replace("[", "").replace("]", "");
	}


	public void zerarSistema() {
		controller.zerarSistema();

	}

	public void encerrarSistema() {
		controller.encerrarSistema();
	}

	public void reiniciarSistema() {
		controller.reiniciarSistema();
	}

	public String abrirSessao(String login, String senha) throws Exception {
		return controller.abrirSessao(login, senha);
	}

	public void encerrarSessao(String login) throws Exception {
		controller.encerrarSessao(login);
	}

	public int cadastrarCarona(String id, String origem, String destino,String data, String hora, String vagas) throws Exception {
		return controller.cadastrarCarona(id, origem, destino, data, hora, vagas);
	}

	public String localizarCarona(String idSessao, String origem, String destino)throws Exception {
		List<Carona> caronas = controller.localizarCarona(idSessao, origem, destino);
		return caronas.toString().replace("[", "{").replace("]", "}").replace(" ", "");
	}
	
	public String localizarCaronaMunicipal(String idSessao, String cidade, String origem, String destino) throws CidadeInexistenteException, OrigemInvalidaException, DestinoInvalidaException{
		List<Carona> caronas = controller.localizarCaronaMunicipal(idSessao,cidade,origem,destino);
		return caronas.toString().replace("[", "{").replace("]", "}").replace(" ", "");
	}

	public String localizarCaronaMunicipal(String idSessao, String cidade) throws CidadeInexistenteException, OrigemInvalidaException, DestinoInvalidaException{
		List<Carona> caronas = controller.localizarCaronaMunicipal(idSessao,cidade,"","");
		return caronas.toString().replace("[", "{").replace("]", "}").replace(" ", "");
	}

	public String getAtributoPerfil(String login, String atributo)throws Exception {
		return controller.getAtributoPerfil(login, atributo);
	}
	public void visualizarPerfil(String idsessao, String login)throws InvalidAttributeValueException {
		controller.visualizarPerfil(idsessao, login);
	}

	
	public void reviewCarona(String idSessao, String idCarona, String review)throws Exception {
		controller.reviewCarona(idSessao, idCarona, review);
	}

	
	public void reviewVagaEmCarona(String idSessao, String idCarona, String loginCaroneiro, String review) throws Exception {
		controller.reviewVagaEmCarona(idSessao, idCarona, loginCaroneiro, review);
	}

	
	public int cadastrarCaronaMunicipal(String idSessao, String origem, String destino, String cidade, String data, String hora,String vagas) throws Exception {
		return controller.cadastrarCaronaMunicipal(idSessao, origem, destino, cidade, data, hora, vagas);
	}
	
	public String cadastrarInteresse(String idSessao, String origem, String destino, String data, String horaInicio, String horaFim) throws Exception {
		return controller.cadastrarInteresse(idSessao, origem, destino, data, horaInicio, horaFim);
	}
	
	public String verificarMensagensPerfil(String idSessao) throws Exception {
		List<String> mensagens = controller.verificarMensagensPerfil(idSessao);
		return mensagens.toString();
	}
	
	public String enviarEmail(String idSessao, String destino, String message) throws InvalidNameException, NullPointerException, InexistentLoginException{
		return controller.enviarEmail(idSessao, destino, message) + "";
	}
}
