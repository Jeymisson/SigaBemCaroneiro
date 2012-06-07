package grupo3si.client;



//import grupo3si.server.model.Usuario;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("greet")
public interface SigaBemServer extends RemoteService{

	void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception;

	void entrar(String text, String text2) throws Exception;

//	Usuario getUsuario(String login) throws Exception;
	
	
}