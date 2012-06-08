package grupo3si.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SigaBemServerAsync  {

	void criarUsuario(String login, String senha, String nome, String endereco,
			String email, AsyncCallback<Void> callback);

	void entrar(String text, String text2, AsyncCallback<Void> asyncCallback);

	void getAtributoDeUsuario(String login, String string,
			AsyncCallback<String> atributoCallback);
	void cadastrarCarona(String id, String origem, String destino, String data,
			String hora, Integer vagas, AsyncCallback<Void> callback);

}