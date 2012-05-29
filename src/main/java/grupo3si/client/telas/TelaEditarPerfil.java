package grupo3si.client.telas;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.text.client.IntegerRenderer;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;

public class TelaEditarPerfil extends Composite {
	
	private Image imagePerfil;
	private FileUpload fileUpload;

	public TelaEditarPerfil() {
		
		
		
		AbsolutePanel PainelEditarPerfil = new AbsolutePanel();
		initWidget(PainelEditarPerfil);
		PainelEditarPerfil.setSize("1024px", "720px");
		
		imagePerfil = new Image("imagens/fEIA.jpg");
		PainelEditarPerfil.add(imagePerfil, 45, 52);
		imagePerfil.setSize("125px", "146px");
		
		
		fileUpload = new FileUpload();
		fileUpload.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				imagePerfil.setUrl(fileUpload.getFilename());
			}
		});
		
		imagePerfil.setUrl("imagens/fEIA.jpg");
		
		PainelEditarPerfil.add(fileUpload, 45, 214);
		fileUpload.setSize("196px", "18px");
		
		InlineLabel nlnlblNome = new InlineLabel("Nome");
		PainelEditarPerfil.add(nlnlblNome, 330, 52);
		nlnlblNome.setSize("38px", "18px");
		
		IntegerBox nome = new IntegerBox();
		PainelEditarPerfil.add(nome, 330, 76);
		nome.setSize("332px", "13px");
		
		InlineLabel nlnlblEmail = new InlineLabel("Email");
		PainelEditarPerfil.add(nlnlblEmail, 330, 230);
		
		IntegerBox email = new IntegerBox();
		PainelEditarPerfil.add(email, 330, 251);
		email.setSize("332px", "13px");
		
		InlineLabel nlnlblLogin = new InlineLabel("Cidade onde mora");
		PainelEditarPerfil.add(nlnlblLogin, 331, 289);
		
		IntegerBox cidadeOndeMora = new IntegerBox();
		PainelEditarPerfil.add(cidadeOndeMora, 330, 310);
		cidadeOndeMora.setSize("332px", "13px");
		
		InlineLabel nlnlblEstado = new InlineLabel("Estado");
		PainelEditarPerfil.add(nlnlblEstado, 330, 413);
		
		ListBox comboBoxEstados = new ListBox();
		PainelEditarPerfil.add(comboBoxEstados, 330, 434);
		comboBoxEstados.setSize("169px", "18px");
		comboBoxEstados.addItem("Acre - AC");
		comboBoxEstados.addItem("Alagoas - AL");
		comboBoxEstados.addItem("Amazonas - AM");
		comboBoxEstados.addItem("Bahia  - BA");
		comboBoxEstados.addItem("Cear‡ - CE");
		comboBoxEstados.addItem("Distrito Federal  - DF");
		comboBoxEstados.addItem("Esp’rito Santo - ES");
		comboBoxEstados.addItem("Goi‡s - GO");
		comboBoxEstados.addItem("Mato Grosso - MT");
		comboBoxEstados.addItem("Mato Grosso do Sul - MS");
		comboBoxEstados.addItem("Minas Gerais - MG");
		comboBoxEstados.addItem("Par‡ - PA");
		comboBoxEstados.addItem("Para’ba - PB");
		comboBoxEstados.addItem("Paran‡ - PR");
		comboBoxEstados.addItem("Pernambuco - PE");
		comboBoxEstados.addItem("Piau’ - PI");
		comboBoxEstados.addItem("Rio de Janeiro - RJ");
		comboBoxEstados.addItem("Rio Grande do Norte - RN");
		comboBoxEstados.addItem("Roraima - RR");
		comboBoxEstados.addItem("Santa Catarina - SC");
		comboBoxEstados.addItem("S‹o Paulo - SP");
		comboBoxEstados.addItem("Sergipe - SE");
		comboBoxEstados.addItem("Tocantins - TO");
		
		InlineLabel nlnlblEndereo = new InlineLabel("Endere\u00E7o");
		PainelEditarPerfil.add(nlnlblEndereo, 331, 350);
		nlnlblEndereo.setSize("105px", "15px");
		
		IntegerBox endereco = new IntegerBox();
		PainelEditarPerfil.add(endereco, 330, 371);
		endereco.setSize("332px", "13px");
		
		InlineLabel nlnlblSexo = new InlineLabel("Sexo");
		PainelEditarPerfil.add(nlnlblSexo, 330, 111);
		nlnlblSexo.setSize("38px", "18px");
		
		RadioButton sexoMasculino = new RadioButton("new name", " Masculino");
		PainelEditarPerfil.add(sexoMasculino, 331, 135);
		
		RadioButton sexoFeminino = new RadioButton("new name", " Feminino");
		sexoFeminino.setValue(true);
		PainelEditarPerfil.add(sexoFeminino, 431, 135);
		
		InlineLabel nlnlblDataDeNascimento = new InlineLabel("Data de nascimento    (dd/mm/aaaa)");
		PainelEditarPerfil.add(nlnlblDataDeNascimento, 330, 169);
		nlnlblDataDeNascimento.setSize("270px", "18px");
		
		IntegerBox dataNasciemento = new IntegerBox();
		PainelEditarPerfil.add(dataNasciemento, 330, 193);
		dataNasciemento.setSize("332px", "13px");
		
		TextButton BotaoSalvar = new TextButton("Salvar");
		BotaoSalvar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				
				
				
			}
		});
		PainelEditarPerfil.add(BotaoSalvar, 589, 576);
		BotaoSalvar.setSize("79px", "28px");
		
		
		
		
		
	}
}
