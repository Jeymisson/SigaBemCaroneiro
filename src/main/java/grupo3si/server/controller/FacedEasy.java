package grupo3si.server.controller;

import grupo3si.server.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		/*
		if (idCarona == null || idCarona.equals(""))
			throw new IdentificadorCaronaInvalidoException();
		if (!idCarona.matches("\\d+"))
			throw new InexistenteItemException();

		if (AtributosDeCarona.PONTO_DE_ENCONTRO.getatribute().equalsIgnoreCase(
				atributo)) {
			return controladorDeNegociacoes.getPontosDeEmbarque(idCarona);
		}
		Carona carona = rep.getCarona(idCarona);
		if (atributo == null || atributo.equals("")) {
			throw new InvalidAtributeException();
		}

		String resp = null;

		if (this == null) {
			throw new InexistenteItemException();
		}

		if (AtributosDeCarona.ORIGEM.getatribute().equalsIgnoreCase(atributo)) {
			resp = carona.getOrigem();
		} else if (AtributosDeCarona.DATA.getatribute().equalsIgnoreCase(
				atributo)) {
			resp = carona.getData();
		} else if (AtributosDeCarona.DESTINO.getatribute().equalsIgnoreCase(
				atributo)) {
			resp = carona.getDestino();
		} else if (AtributosDeCarona.VAGA.getatribute().equalsIgnoreCase(
				atributo)) {
			resp = String.valueOf(carona.getVagas());
		} else if (AtributosDeCarona.HORA.getatribute().equalsIgnoreCase(
				atributo)) {
			resp = String.valueOf(carona.getHora());
		} else if (AtributosDeCarona.EHMUNICIPAL.getatribute()
				.equalsIgnoreCase(atributo)) {
			resp = carona.ehMunicipal() + "";
		} else
			throw new InexistentAtributeException();

		return resp;
		*/
		return controller.getAtributoCarona(idCarona, atributo);
	}



	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
		/*
		Usuario user = rep.getUser(login);
		String resp = "";
		if (atributo == null || atributo.equals("")) {
			throw new InvalidAtributeException();
		}

		if (AtributosDePerfil.NOME.getAtribute().equalsIgnoreCase(atributo)) {
			resp = user.getNome();
		} else if (AtributosDePerfil.ENDERECO.getAtribute().equalsIgnoreCase(
				atributo)) {
			resp = user.getEndereco();
		} else if (AtributosDePerfil.EMAIL.getAtribute().equalsIgnoreCase(
				atributo)) {
			resp = user.getEmail();
		} else
			throw new InexistentAtributeException();

		return resp;
	*/
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

	public String getSolicitacoesPendentes(String idCarona) {
		return (controller.getSolicitacoesPendentes(idCarona)
				.toString()).replace("[", "{").replace("]", "}");
	}

	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) {
		return (controller.getSolicitacoesConfirmadas(idSessao, idCarona).toString()).replace("[", "").replace("]", "");
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


	public String getAtributoPerfil(String login, String atributo)throws Exception {
			/*
		Usuario user = rep.getUser(login);
		String resp = "";
		if (atributo == null || atributo.equals("")) {
			throw new InvalidAtributeException();
		}

		if (AtributosDePerfil.NOME.getAtribute().equalsIgnoreCase(atributo)) {
			resp = user.getNome();
		} else if (AtributosDePerfil.ENDERECO.getAtribute().equalsIgnoreCase(
				atributo)) {
			resp = user.getEndereco();
		} else if (AtributosDePerfil.EMAIL.getAtribute().equalsIgnoreCase(
				atributo)) {
			resp = user.getEmail();
		} else if (AtributosDePerfil.CARONASNAOFUNCIONOU.getAtribute()
				.equalsIgnoreCase(atributo)) {
			resp = user.getCaronafaltosas() + "";
		} else if (AtributosDePerfil.CARONASSEGURAS.getAtribute()
				.equalsIgnoreCase(atributo)) {
			resp = user.getCaronasSeguras() + "";
		} else if (AtributosDePerfil.FALTAEMVAGASDECARONAS.getAtribute()
				.equalsIgnoreCase(atributo)) {
			resp = user.getFaltaEmVagaDeCarona() + "";
		} else if (AtributosDePerfil.HISTORICODECARONAS.getAtribute()
				.equalsIgnoreCase(atributo)) {
			resp = (user.getHistoricoDeCaronas() + "").replaceAll(" ", "");
		} else if (AtributosDePerfil.HISTORICOVAGASCARONAS.getAtribute()
				.equalsIgnoreCase(atributo)) {
			resp = (user.getHistoricoEmVagasDeCaronas() + "").replaceAll(" ",
					"");
		} else if (AtributosDePerfil.PRESENCAEMVAGASDECARONA.getAtribute()
				.equalsIgnoreCase(atributo)) {
			resp = user.getPresencaEmVagaDeCarona() + "";
		} else
			throw new InexistentAtributeException();

		return resp;
		*/
		return controller.getAtributoPerfil(login, atributo);
	}
	public void visualizarPerfil(String idsessao, String login)throws InvalidLoginException {
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


}
