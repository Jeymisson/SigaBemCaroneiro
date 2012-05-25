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
				
				
			}
		});
		PainelLogado.add(txtbtnSair, 914, 10);
		
		Image fotoPerfil = new Image("imagens/fEIA.jpg");
		PainelLogado.add(fotoPerfil, 24, 41);
		fotoPerfil.setSize("134px", "150px");
		
		Hyperlink hprlnkEditarPerfil = new Hyperlink("Editar Perfil", false, "newHistoryToken");
		hprlnkEditarPerfil.addAttachHandler(new Handler() {
			public void onAttachOrDetach(AttachEvent event) {
				System.out.println("entrou no evento click");
				TelaEditarPerfil editarperfil = new TelaEditarPerfil();
				editarperfil.setVisible(true);
				RootPanel.get("centro").clear();
				RootPanel.get("centro").add(editarperfil);
				
			}
		});
		PainelLogado.add(hprlnkEditarPerfil, 24, 231);
		
		Hyperlink hprlnkCadastrarCarona = new Hyperlink("Cadastrar Carona", false, "newHistoryToken");
		PainelLogado.add(hprlnkCadastrarCarona, 23, 252);
		
		TextArea TextAreaStatus = new TextArea();
		PainelLogado.add(TextAreaStatus, 283, 41);
		TextAreaStatus.setSize("453px", "39px");
		
		TextButton PublicarStatus = new TextButton("Publicar");
		PainelLogado.add(PublicarStatus, 672, 93);
		
		Tree treeHistorico = new Tree();
		PainelLogado.add(treeHistorico, 24, 280);
		treeHistorico.setSize("100px", "100px");
		
		TreeItem trtmHistrico = new TreeItem("Hist\u00F3rico");
		treeHistorico.addItem(trtmHistrico);
		
		
		
	}
}
