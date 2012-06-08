package grupo3si.client;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("greet")
public interface SigaBemServer extends RemoteService{

	void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception;

	void entrar(String text, String text2) throws Exception;

	String getAtributoDeUsuario(String login, String atributo) throws Exception;

	void cadastrarCarona(String id, String origem, String destino,
			String data, String hora, Integer vagas) throws Exception;
	
}