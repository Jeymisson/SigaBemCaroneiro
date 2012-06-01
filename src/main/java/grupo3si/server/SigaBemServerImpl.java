package grupo3si.server;

import grupo3si.client.SigaBemServer;
import grupo3si.server.controller.SigaBemController;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class SigaBemServerImpl extends RemoteServiceServlet implements SigaBemServer{
	
	SigaBemController controller = SigaBemController.getInstance();
	
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) {
		try {
			controller.criarUsuario(login, senha, nome, endereco, email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}




}