package grupo3si.client.telas;

import grupo3si.client.SigaBemServerAsync;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class TelaInicial extends Composite{
	
	private SigaBemServerAsync controllerServer;
	private TextBox textBoxNome;
	private TextBox textBoxLogin,textlogin;
	private TextBox textBoxEndereco;
	private TextBox textBoxEmail;
	private PasswordTextBox passwordTextBox,textsenha;
	
	public TelaInicial(SigaBemServerAsync controller) {
		
		controllerServer = controller;
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("1024px", "600px");
		
		Label lblNewLabel = new Label("Login");
		absolutePanel.add(lblNewLabel, 766, 10);
		
		Label lblSenha = new Label("Senha");
		absolutePanel.add(lblSenha, 877, 10);
		
		textlogin = new TextBox();
		absolutePanel.add(textlogin, 766, 30);
		textlogin.setSize("85px", "16px");
		
		textsenha = new PasswordTextBox();
		absolutePanel.add(textsenha, 877, 30);
		textsenha.setSize("85px", "16px");
		
		CheckBox chckbxLembrarme = new CheckBox("Lembrar-me");
		absolutePanel.add(chckbxLembrarme, 766, 59);
		
		Hyperlink hprlnkRecuperarSenha = new Hyperlink("Recuperar senha", false, "newHistoryToken");
		absolutePanel.add(hprlnkRecuperarSenha, 877, 62);
		
		TextButton btnEntrar = new TextButton("Entrar");
		btnEntrar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				final String login = textlogin.getText();
				controllerServer.entrar(login, textsenha.getText(), new AsyncCallback<Void>() {

					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
						
					}

					public void onSuccess(Void result) {
						textlogin.setText("");
						textsenha.setText("");
						TelaLogado telaLogado = new TelaLogado(controllerServer,login);
						telaLogado.setVisible(true);
						RootPanel.get("centro").clear();
						RootPanel.get("centro").add(telaLogado);
						
					}

				});
				
				
			}
		});
		absolutePanel.add(btnEntrar, 766, 84);
		
		Image image = new Image("imagens/carona.jpg");
		absolutePanel.add(image, 32, 94);
		image.setSize("600px", "416px");
		
		FlexTable flexTable = new FlexTable();
		absolutePanel.add(flexTable, 700, 148);
		flexTable.setSize("253px", "202px");
		
		Label lblCadastrese = new Label("Cadastre-se");
		flexTable.setWidget(0, 0, lblCadastrese);
		
		Label lblNome = new Label("Nome:");
		flexTable.setWidget(1, 0, lblNome);
		
		textBoxNome = new TextBox();
		flexTable.setWidget(1, 1, textBoxNome);
		
		Label lblUsuario = new Label("Login:");
		flexTable.setWidget(2, 0, lblUsuario);
		
		textBoxLogin = new TextBox();
		flexTable.setWidget(2, 1, textBoxLogin);
		
		Label lblEndereo = new Label("Endere\u00E7o:");
		flexTable.setWidget(3, 0, lblEndereo);
		
		textBoxEndereco = new TextBox();
		flexTable.setWidget(3, 1, textBoxEndereco);
		
		Label lblEmail = new Label("E-mail:");
		flexTable.setWidget(4, 0, lblEmail);
		
		textBoxEmail = new TextBox();
		flexTable.setWidget(4, 1, textBoxEmail);
		
		Label lblNewLabel_1 = new Label("Senha:");
		flexTable.setWidget(5, 0, lblNewLabel_1);
		
		passwordTextBox = new PasswordTextBox();
		flexTable.setWidget(5, 1, passwordTextBox);
		
		TextButton txtbtnCadastra = new TextButton("Cadastrar");
		flexTable.setWidget(6, 1, txtbtnCadastra);
		txtbtnCadastra.addClickHandler(new ClickHandler() {
			

			public void onClick(ClickEvent event) {
				controllerServer.criarUsuario(textBoxLogin.getText(), passwordTextBox.getText(), 
						textBoxNome.getText(),textBoxEndereco.getText(),textBoxEmail.getText(), new AsyncCallback<Void>() {
							public void onSuccess(Void result) {
								Window.alert("Usu�rio cadastrado com sucesso!");
								limparTextBox(textBoxNome, textBoxLogin, textBoxEndereco,textBoxEmail, passwordTextBox);
							}
							public void onFailure(Throwable caught) {
							  Window.alert("Dados inv�lidos");
							}
						});
			}
		});
		txtbtnCadastra.setSize("131px", "28px");
		flexTable.getCellFormatter().setHorizontalAlignment(6, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
	}
	
	private void limparTextBox(TextBox nome,TextBox login, TextBox endereco, TextBox email,PasswordTextBox senha){
		nome.setText("");
		endereco.setText("");
		email.setText("");
		login.setText("");
		senha.setText("");
		
	}	
}