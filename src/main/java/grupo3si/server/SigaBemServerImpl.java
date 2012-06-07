package grupo3si.server;

import javax.naming.directory.InvalidAttributeValueException;

import grupo3si.client.SigaBemServer;
import grupo3si.server.controller.SigaBemController;
import grupo3si.server.model.InexistentLoginException;


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


}