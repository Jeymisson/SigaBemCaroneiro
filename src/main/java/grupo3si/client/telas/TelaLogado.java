package grupo3si.client.telas;
import java.util.ArrayList;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.VerticalSplitPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.validation.client.constraints.MaxValidatorForNumber;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.DeckPanel;
import com.smartgwt.client.widgets.IButton;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;



import java.util.ArrayList;
import com.google.gwt.user.datepicker.client.DatePicker;

public class TelaLogado extends Composite {
	private int contador;
	private TextBox caixaDeTexto;
	private ArrayList<String> stocks = new ArrayList<String>();
	private String textoParaMural;
	private CaptionPanel  painelCadastrarCarona;
	private VerticalPanel verticalPanel;
	private FlexTable mural;

	public TelaLogado() {
	
		/*
		 * Criando painel principal
		 */
		AbsolutePanel PainelLogado = new AbsolutePanel();
		initWidget(PainelLogado);
		PainelLogado.setSize("1024px", "720px");
		
		Label lblBemVindo = new Label("Bem Vindo");
		PainelLogado.add(lblBemVindo, 60, 10);
		
		TextButton txtbtnSair = new TextButton("Sair");
		txtbtnSair.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				TelaInicial inicio = new TelaInicial();
				inicio.setVisible(true);
				RootPanel.get("centro").clear();
				RootPanel.get("centro").add(inicio);
				
			}
		});
		PainelLogado.add(txtbtnSair, 916, 41);
		
		Image fotoPerfil = new Image("imagens/fEIA.jpg");
		fotoPerfil.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				TelaPerfilPublico perfilpublico = new TelaPerfilPublico();
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
		PainelLogado.add(PublicarStatus, 88, 386);
		PublicarStatus.setSize("78px", "28px");
		
		
		
		TextButton BotaoEditarPerfil = new TextButton("Editar Perfil");
		BotaoEditarPerfil.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				TelaEditarPerfil perfil = new TelaEditarPerfil();
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
		painelCadastrarCarona.setCaptionHTML("Cadastrar");
		PainelLogado.add(painelCadastrarCarona, 357, 145);
		painelCadastrarCarona.setSize("331px", "376px");
		
		FlexTable flexTable = new FlexTable();
		painelCadastrarCarona.setContentWidget(flexTable);
		flexTable.setSize("335px", "358px");
		
		Label lblNewLabel = new Label("Origem:");
		flexTable.setWidget(0, 0, lblNewLabel);
		
		TextBox textBox = new TextBox();
		flexTable.setWidget(0, 1, textBox);
		
		Label lblDestino = new Label("Destino:");
		flexTable.setWidget(1, 0, lblDestino);
		
		TextBox textBox_1 = new TextBox();
		flexTable.setWidget(1, 1, textBox_1);
		
		Label lblData = new Label("Data:");
		flexTable.setWidget(2, 0, lblData);
		
		TextBox textBox_2 = new TextBox();
		flexTable.setWidget(2, 1, textBox_2);
		
		Label lblHora = new Label("Hora:");
		flexTable.setWidget(3, 0, lblHora);
		
		TextBox textBox_3 = new TextBox();
		flexTable.setWidget(3, 1, textBox_3);
		
		Label lblVagas = new Label("Vagas:");
		flexTable.setWidget(4, 0, lblVagas);
		
		ListBox listBox = new ListBox();
		listBox.addItem("1");
		listBox.addItem("2");
		listBox.addItem("3");
		listBox.addItem("4");
		listBox.addItem("5");
		flexTable.setWidget(4, 1, listBox);
		listBox.setVisibleItemCount(1);
		
		TextButton txtbtnCadastrar = new TextButton("Cadastrar");
		txtbtnCadastrar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
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
		flexTable.setWidget(5, 1, txtbtnCadastrar);
		flexTable.getCellFormatter().setHorizontalAlignment(5, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		caixaDeTexto = new TextBox();
		caixaDeTexto.setDirectionEstimator(true);
		caixaDeTexto.setText("what's up?");
		PainelLogado.add(caixaDeTexto, 24, 341);
		caixaDeTexto.setSize("134px", "31px");
		textoParaMural = caixaDeTexto.getText().toUpperCase();
		stocks.add(textoParaMural);
		
		
		mural = new FlexTable();
		verticalPanel.add(mural);
		mural.setSize("457px", "536px");
		
		DatePicker datePicker = new DatePicker();
		PainelLogado.add(datePicker, 24, 459);
		datePicker.setSize("132px", "169px");
		
	}
}