package grupo3si.client.telas;

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
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.VerticalSplitPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.validation.client.constraints.MaxValidatorForNumber;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.DeckPanel;

public class TelaLogado extends Composite {
	private FlexTable mural;
	private int contador=0;
	private TextBox caixaDeTexto;

	public TelaLogado() {
		
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
		PainelLogado.add(fotoPerfil, 24, 41);
		fotoPerfil.setSize("134px", "150px");
		
		TextButton PublicarStatus = new TextButton("Publicar");
		PublicarStatus.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
			
				//Pegar o que foi escrito na caixa de te
				mural.setText(contador++, 0, caixaDeTexto.getValue());
				
				
			}
		});
		PainelLogado.add(PublicarStatus, 88, 458);
		PublicarStatus.setSize("70px", "28px");
		
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
		BotaoEditarPerfil.setSize("134px", "28px");
		
		TextButton BotaoCadastrarCarona = new TextButton("Cadastrar Carona");
		PainelLogado.add(BotaoCadastrarCarona, 24, 266);
		BotaoCadastrarCarona.setSize("134px", "28px");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		PainelLogado.add(verticalPanel, 283, 160);
		verticalPanel.setSize("459px", "536px");
		
		mural = new FlexTable();
		verticalPanel.add(mural);
		mural.setSize("457px", "536px");
		
		caixaDeTexto = new TextBox();
		caixaDeTexto.setDirectionEstimator(true);
		caixaDeTexto.setText("what's up?");
		PainelLogado.add(caixaDeTexto, 24, 413);
		caixaDeTexto.setSize("122px", "31px");
		
		
		
	}
}
