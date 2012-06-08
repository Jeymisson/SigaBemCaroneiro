package grupo3si.server;

import java.util.List;

import javax.naming.directory.InvalidAttributeValueException;

import grupo3si.client.SigaBemServer;
import grupo3si.server.controller.SigaBemController;
import grupo3si.server.model.InexistentLoginException;
import grupo3si.server.model.Usuario;



import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class SigaBemServerImpl extends RemoteServiceServlet implements SigaBemServer{
	
	SigaBemController controller = SigaBemController.getInstance();
	
	
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		try {
			controller.criarUsuario(login, senha, nome, endereco, email);
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	}

	public void entrar(String login, String senha) throws Exception {
		
		try{
			controller.abrirSessao(login, senha);
			
		}catch (Exception e) {
			throw new Exception(e);
		}
		
		
	}

	public String getAtributoDeUsuario(String login, String atributo) throws Exception {
		return controller.getAtributoUsuario(login, atributo);
	}

	public void cadastrarCarona(String id, String origem, String destino,
			String data, String hora, Integer vagas) throws Exception {
		Usuario user = controller.getUser(id);
		user.cadastraCarona( origem, destino, data, hora, vagas);
		//controller.cadastrarCarona(user.getUserID(), origem, destino, data, hora, vagas);
	}

	public List<String> getMensagensMotorista(String login) {
		List<String> mensagens = null;
		try {
			Usuario user = controller.getUser(login);
			mensagens = user.getPerfil().getMensagensMotorista();
		} catch (InvalidAttributeValueException e) {
			e.printStackTrace();
		} catch (InexistentLoginException e) {
			e.printStackTrace();
		}
		return mensagens;
		
	}
}