package grupo3si.client.telas;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.user.client.ui.Image;
import com.smartgwt.client.widgets.IButton;

public class TelaInicial extends Composite{
	public TelaInicial() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("1024px", "600px");
		
		Label lblNewLabel = new Label("Usuario");
		absolutePanel.add(lblNewLabel, 766, 10);
		
		Label lblSenha = new Label("Senha");
		absolutePanel.add(lblSenha, 877, 10);
		
		TextBox textBox = new TextBox();
		absolutePanel.add(textBox, 766, 30);
		textBox.setSize("85px", "16px");
		
		TextBox textBox_1 = new TextBox();
		absolutePanel.add(textBox_1, 877, 30);
		textBox_1.setSize("85px", "16px");
		
		CheckBox chckbxLembrarme = new CheckBox("Lembrar-me");
		absolutePanel.add(chckbxLembrarme, 766, 59);
		
		Hyperlink hprlnkRecuperarSenha = new Hyperlink("Recuperar senha", false, "newHistoryToken");
		absolutePanel.add(hprlnkRecuperarSenha, 877, 62);
		
		Button btnEntrar = new Button("Entrar");
		btnEntrar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				TelaLogado telaLogado = new TelaLogado();
				telaLogado.setVisible(true);
				RootPanel.get("centro").clear();
				RootPanel.get("centro").add(telaLogado);
			}
		});
		absolutePanel.add(btnEntrar, 766, 84);
		
		Label lblCadastrese = new Label("Cadastre-se");
		absolutePanel.add(lblCadastrese, 682, 132);
		
		Label lblNome = new Label("Nome:");
		absolutePanel.add(lblNome, 682, 166);
		
		Label lblEndereo = new Label("Endere\u00E7o:");
		absolutePanel.add(lblEndereo, 682, 231);
		
		Label lblEmail = new Label("E-mail:");
		absolutePanel.add(lblEmail, 682, 264);
		
		Label lblNewLabel_1 = new Label("Senha:");
		absolutePanel.add(lblNewLabel_1, 682, 297);
		
		TextBox textBox_2 = new TextBox();
		absolutePanel.add(textBox_2, 747, 154);
		
		TextBox textBox_3 = new TextBox();
		absolutePanel.add(textBox_3, 747, 187);
		
		TextBox textBox_4 = new TextBox();
		absolutePanel.add(textBox_4, 747, 219);
		
		TextBox textBox_5 = new TextBox();
		absolutePanel.add(textBox_5, 747, 253);
		
		TextBox textBox_6 = new TextBox();
		absolutePanel.add(textBox_6, 747, 285);
		
		Label lblUsuario = new Label("Usuario:");
		absolutePanel.add(lblUsuario, 682, 199);
		
		TextButton txtbtnCadastra = new TextButton("Cadastre-se");
		absolutePanel.add(txtbtnCadastra, 766, 340);
		
		Image image = new Image("imagens/carona.jpg");
		absolutePanel.add(image, 32, 94);
		image.setSize("600px", "416px");
		
		
//		IButton stretchButton = new IButton("Stretch Button");  
//        stretchButton.setWidth(150);  
//        stretchButton.setShowRollOver(true);  
//        stretchButton.setShowDisabled(true);  
//        stretchButton.setShowDown(true);  
//        stretchButton.setTitleStyle("stretchTitle");  
//        absolutePanel.add(stretchButton,0,0);
		
		
	}
}
