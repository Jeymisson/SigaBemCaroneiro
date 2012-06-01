package grupo3si.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("greet")
public interface SigaBemServer extends RemoteService{

	void criarUsuario(String login, String senha, String nome,
			String endereco, String email);
}
