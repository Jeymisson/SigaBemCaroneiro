package grupo3si.client.telas;

import java.util.List;

import grupo3si.client.SigaBemServerAsync;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.user.client.ui.Image;

import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.logical.shared.AttachEvent;

public class TelaPerfilPublico extends Composite {
	
	private AbsolutePanel painelCaronasPegas,painelMotorista,mural,informacoes;
	private DecoratedTabPanel painelHistorico,painelMural,painelInformacoes;
	private SigaBemServerAsync controllerServer;
	private final String login;
	
	public TelaPerfilPublico(SigaBemServerAsync controller, String login) {
		this.login = login;
		this.controllerServer = controller;
		
		/*
		 * Criando painel principal
		 */
		AbsolutePanel PainelLogado = new AbsolutePanel();
		initWidget(PainelLogado);
		PainelLogado.setSize("1024px", "720px");
		
		Label lblBemVindo = new Label("Bem Vindo");
		PainelLogado.add(lblBemVindo, 60, 10);
		
		
		/*
		 *Definindo imagem 
		 */
		Image fotoPerfil = new Image("imagens/fEIA.jpg");
		PainelLogado.add(fotoPerfil, 24, 41);
		fotoPerfil.setSize("142px", "161px");
	
	
	
	TextButton botaoFotos = new TextButton("Fotos");
	PainelLogado.add(botaoFotos, 24, 283);
	botaoFotos.setSize("142px", "28px");
	
	
	
	painelHistorico = new DecoratedTabPanel();
	painelHistorico.setVisible(false);
	PainelLogado.add(painelHistorico, 283, 145);
	painelHistorico.setSize("452px", "517px");
	
	painelMural = new DecoratedTabPanel();
	PainelLogado.add(painelMural, 283, 145);
	painelHistorico.setSize("452px", "517px");
	
	painelInformacoes = new DecoratedTabPanel();
	painelInformacoes.setVisible(false);
	PainelLogado.add(painelInformacoes, 283, 145);
	painelInformacoes.setSize("452px", "517px");
	
	mural = new AbsolutePanel();
	painelMural.add(mural, "Mural", false);
	mural.setSize("450px", "515px");
	
	informacoes = new AbsolutePanel();
	painelInformacoes.add(informacoes, "Informa�›es", false);
	informacoes.setSize("450px", "515px");
	
	painelCaronasPegas = new AbsolutePanel();
	painelHistorico.add(painelCaronasPegas, "Caroneiro", false);
	painelCaronasPegas.setSize("450px", "515px");
	
	painelMotorista = new AbsolutePanel();
	painelHistorico.add(painelMotorista, "Motorista", false);
	painelMotorista.setSize("450px", "515px");
	caronasPainelMotorista();
	
	DatePicker calendario = new DatePicker();
	PainelLogado.add(calendario, 24, 461);
	calendario.setSize("132px", "169px");
	
	/*
	 * Botoes e eventos
	 */
	
	TextButton botaoHistrico = new TextButton("Hist\u00F3rico");
	botaoHistrico.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			painelMural.setVisible(false);
			painelHistorico.setVisible(true);
			painelInformacoes.setVisible(false);
		}
	});
	PainelLogado.add(botaoHistrico, 24, 328);
	botaoHistrico.setSize("142px", "28px");
	
	/*
	 * Botoes e eventos
	 */
	TextButton botaoMural = new TextButton("Mural");
	botaoMural.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			painelMural.setVisible(true);
			painelHistorico.setVisible(false);
			painelInformacoes.setVisible(false);
		}
	});
	PainelLogado.add(botaoMural, 24, 238);
	botaoMural.setSize("142px", "28px");
	
	/*
	 * Botoes e eventos
	 */
	TextButton botaoNewButton = new TextButton("Informa\u00E7\u00F5es");
	botaoNewButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			painelMural.setVisible(false);
			painelHistorico.setVisible(false);
			painelInformacoes.setVisible(true);
		}
	});
	PainelLogado.add(botaoNewButton, 24, 374);
	botaoNewButton.setSize("142px", "28px");
	
	}
	
	private void caronasPainelMotorista(){
		controllerServer.getMensagensMotorista(this.login, new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> result) {
				addPainelMotorista(result);
			}
			
			public void onFailure(Throwable caught) {
				Window.alert("falhou visualizar caronas motoristas.");
				
			}
		});
	}
	
	private void addPainelMotorista(List<String> mensagens){
		Label labelMensagem;
		System.out.println("metodo");
		for (String mensagem : mensagens) {
			System.out.println(mensagens);
			labelMensagem = new Label(mensagem);
			painelMotorista.add(labelMensagem);
		}
	}
}
