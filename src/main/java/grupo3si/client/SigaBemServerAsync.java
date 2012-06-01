package grupo3si.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SigaBemServerAsync  {

	void criarUsuario(String login, String senha, String nome, String endereco,
			String email, AsyncCallback<Void> callback);

}
