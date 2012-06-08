package grupo3si.client.telas;
import grupo3si.client.SigaBemServerAsync;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

import com.google.gwt.user.datepicker.client.DatePicker;

public class TelaLogado extends Composite {

	private SigaBemServerAsync controllerServer;
	private int contador;
	private TextBox caixaDeTexto;
	private ArrayList<String> stocks = new ArrayList<String>();
	private String textoParaMural;
	private CaptionPanel  painelCadastrarCarona;
	private VerticalPanel verticalPanel;
	private FlexTable mural,solicitacoes;
	private TextButton botaoVoltar;
	private Label lblBemVindo;

	private final String login;
	private ListBox listCaronaVagas;
	private TextBox boxCaronaHora;
	private TextBox boxCaronaData;
	private TextBox boxCaronaDestino;
	private TextBox boxCaronaOrigem;

	public TelaLogado(SigaBemServerAsync controller, final String login) {
		this.login = login;
		controllerServer = controller;
		lblBemVindo = new Label();

		controller.getAtributoDeUsuario(login,"nome", new AsyncCallback<String>() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			public void onSuccess(String result) {
				lblBemVindo.setText("Bem Vindo " + result);
			}


		});

		/*
		 * Criando painel principal
		 */
		AbsolutePanel PainelLogado = new AbsolutePanel();
		initWidget(PainelLogado);
		PainelLogado.setSize("1024px", "720px");

		PainelLogado.add(lblBemVindo, 60, 10);

		TextButton txtbtnSair = new TextButton("Sair");
		txtbtnSair.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				TelaInicial inicio = new TelaInicial(controllerServer);
				inicio.setVisible(true);
				RootPanel.get("centro").clear();
				RootPanel.get("centro").add(inicio);

			}
		});
		PainelLogado.add(txtbtnSair, 916, 41);

		Image fotoPerfil = new Image("imagens/fEIA.jpg");
		fotoPerfil.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				TelaPerfilPublico perfilpublico = new TelaPerfilPublico(controllerServer, login);
				perfilpublico.setVisible(true);
				RootPanel.get("centro").clear();
				RootPanel.get("centro").add(perfilpublico);
			}
		});
		PainelLogado.add(fotoPerfil, 24, 41);
		fotoPerfil.setSize("142px", "152px");


		/*
		 * Momento em que o usuario publica ou remove alguma coisa no mural
		 */
		TextButton PublicarStatus = new TextButton("Publicar");
		PublicarStatus.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				textoParaMural = caixaDeTexto.getText().toUpperCase();
				stocks.add(textoParaMural);


				//Pegar o que foi escrito na caixa de texto
				mural.setText(contador++, 0, textoParaMural);

				//Logica pra remover um elemento do mural
				Button removerMural = new Button("remover");
				int contadorAux = contador-1; 
				mural.setWidget(contadorAux, 1, removerMural);
				removerMural.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						int removeIndex = stocks.indexOf(textoParaMural);
						stocks.remove(removeIndex);
						mural.removeRow(removeIndex+1);
					}
				});

			}
		});
		PainelLogado.add(PublicarStatus, 88, 408);
		PublicarStatus.setSize("78px", "28px");



		TextButton BotaoEditarPerfil = new TextButton("Editar Perfil");
		BotaoEditarPerfil.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				TelaEditarPerfil perfil = new TelaEditarPerfil(controllerServer);
				perfil.setVisible(true);
				RootPanel.get("centro").clear();
				RootPanel.get("centro").add(perfil);
			}
		});




		PainelLogado.add(BotaoEditarPerfil, 24, 232);
		BotaoEditarPerfil.setSize("142px", "28px");

		TextButton BotaoCadastrarCarona = new TextButton("Cadastrar Carona");
		BotaoCadastrarCarona.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				verticalPanel.setVisible(false);
				painelCadastrarCarona.setVisible(true);


			}
		});
		PainelLogado.add(BotaoCadastrarCarona, 24, 266);
		BotaoCadastrarCarona.setSize("142px", "28px");

		verticalPanel = new VerticalPanel();
		PainelLogado.add(verticalPanel, 283, 160);
		verticalPanel.setSize("459px", "536px");

		painelCadastrarCarona = new CaptionPanel("New panel");
		painelCadastrarCarona.setVisible(false);

		solicitacoes = new FlexTable();
		PainelLogado.add(solicitacoes, 285, 121);
		solicitacoes.setVisible(false);
		solicitacoes.setSize("457px", "536px");


		painelCadastrarCarona.setCaptionHTML("Cadastrar");
		PainelLogado.add(painelCadastrarCarona, 357, 145);
		painelCadastrarCarona.setSize("331px", "376px");

		FlexTable flexTable = new FlexTable();
		painelCadastrarCarona.setContentWidget(flexTable);
		flexTable.setSize("335px", "358px");

		Label lblNewLabel = new Label("Origem:");
		flexTable.setWidget(0, 0, lblNewLabel);

		boxCaronaOrigem = new TextBox();
		flexTable.setWidget(0, 1, boxCaronaOrigem);

		Label lblDestino = new Label("Destino:");
		flexTable.setWidget(1, 0, lblDestino);

		boxCaronaDestino = new TextBox();
		flexTable.setWidget(1, 1, boxCaronaDestino);

		Label lblData = new Label("Data:");
		flexTable.setWidget(2, 0, lblData);

		boxCaronaData = new TextBox();
		flexTable.setWidget(2, 1, boxCaronaData);

		Label lblHora = new Label("Hora:");
		flexTable.setWidget(3, 0, lblHora);

		boxCaronaHora = new TextBox();
		flexTable.setWidget(3, 1, boxCaronaHora);

		Label lblVagas = new Label("Vagas:");
		flexTable.setWidget(4, 0, lblVagas);

		listCaronaVagas = new ListBox();
		listCaronaVagas.addItem("1");
		listCaronaVagas.addItem("2");
		listCaronaVagas.addItem("3");
		listCaronaVagas.addItem("4");
		listCaronaVagas.addItem("5");
		flexTable.setWidget(4, 1, listCaronaVagas);
		listCaronaVagas.setVisibleItemCount(1);

		TextButton botaoCadastrarCarona = new TextButton("Cadastrar");
		botaoCadastrarCarona.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				controllerServer.cadastrarCarona(login, boxCaronaOrigem.getText(), boxCaronaDestino.getText(),boxCaronaData.getText(), boxCaronaHora.getText(), listCaronaVagas.getSelectedIndex(), new AsyncCallback<Void>() {
					public void onFailure(Throwable caught) {
						Window.alert("Falhou");
					}

					public void onSuccess(Void result) {
						Window.alert("Cadastrado com sucesso");
					}
				});
				painelCadastrarCarona.setVisible(false);
				verticalPanel.setVisible(true);
			}
		});

		TextButton txtbtnCancelar = new TextButton("Cancelar");
		txtbtnCancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				painelCadastrarCarona.setVisible(false);
				verticalPanel.setVisible(true);
			}
		});
		flexTable.setWidget(5, 0, txtbtnCancelar);
		flexTable.setWidget(5, 1, botaoCadastrarCarona);
		flexTable.getCellFormatter().setHorizontalAlignment(5, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_LEFT);

		caixaDeTexto = new TextBox();
		caixaDeTexto.setDirectionEstimator(true);
		caixaDeTexto.setText("what's up?");
		PainelLogado.add(caixaDeTexto, 24, 363);
		caixaDeTexto.setSize("134px", "31px");
		textoParaMural = caixaDeTexto.getText().toUpperCase();
		stocks.add(textoParaMural);


		mural = new FlexTable();
		verticalPanel.add(mural);
		mural.setSize("457px", "536px");

		DatePicker datePicker = new DatePicker();
		PainelLogado.add(datePicker, 24, 481);
		datePicker.setSize("132px", "169px");

		TextButton botaoSolicitacoes = new TextButton("Solicitacoes");
		botaoSolicitacoes.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mural.setVisible(false);
				solicitacoes.setVisible(true);
				painelCadastrarCarona.setVisible(false);
				botaoVoltar.setVisible(true);

				textoParaMural = caixaDeTexto.getText().toUpperCase();



				//Pegar o que foi escrito na caixa de texto
				solicitacoes.setText(contador++, 0, textoParaMural);

				//Logica pra remover um elemento do mural
				Button aceitarSolicitacao = new Button("aceitar");
				Button recusarSolicitacao = new Button("recusar");
				int contadorAux = contador-1; 
				solicitacoes.setWidget(contadorAux, 1, aceitarSolicitacao);
				solicitacoes.setWidget(contadorAux, 2, recusarSolicitacao);
				aceitarSolicitacao.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						int removeIndex = stocks.indexOf(textoParaMural);
						solicitacoes.removeRow(removeIndex+1);
					}
				});

			}
		});
		PainelLogado.add(botaoSolicitacoes, 24, 300);
		botaoSolicitacoes.setSize("142px", "28px");

		botaoVoltar = new TextButton("Voltar");
		botaoVoltar.setVisible(false);
		botaoVoltar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mural.setVisible(true);
				solicitacoes.setVisible(false);
				painelCadastrarCarona.setVisible(false);
			}
		});
		PainelLogado.add(botaoVoltar, 283, 682);

	}
}