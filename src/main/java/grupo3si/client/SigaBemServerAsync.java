package grupo3si.client;

//import grupo3si.server.model.Usuario;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SigaBemServerAsync  {

	void criarUsuario(String login, String senha, String nome, String endereco,
			String email, AsyncCallback<Void> callback);

	void entrar(String text, String text2, AsyncCallback<Void> asyncCallback);

	void getAtributoDeUsuario(String login, String string,
			AsyncCallback<String> atributoCallback);


}