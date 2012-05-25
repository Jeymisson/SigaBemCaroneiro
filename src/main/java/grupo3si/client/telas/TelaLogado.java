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

public class TelaLogado extends Composite {

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
		
		TextArea TextAreaStatus = new TextArea();
		TextAreaStatus.setEnabled(false);
		TextAreaStatus.setDirectionEstimator(false);
		PainelLogado.add(TextAreaStatus, 283, 41);
		TextAreaStatus.setSize("453px", "39px");
		
		TextButton PublicarStatus = new TextButton("Publicar");
		PainelLogado.add(PublicarStatus, 672, 93);
		
		Tree treeHistorico = new Tree();
		PainelLogado.add(treeHistorico, 24, 363);
		treeHistorico.setSize("100px", "100px");
		
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
		
		
		
	}
}
