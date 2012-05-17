package grupo3si.server.model;

import java.util.Iterator;
import java.util.List;

public interface Usuario {
		/**
		 * Metodo acessor para login
		 * @return O login
		 */
		public String getLogin();
		/**
		 * Metodo acessor para perfil
		 * @return O perfil
		 */
		public Perfil getPerfil();
		/**
		 * Metodo acessor para senha
		 * @return A senha
		 */
		public String getSenha();
		/**
		 * Metodo acessor para nome
		 * @return O nome
		 */
		public String getNome();
		/**
		 * Metodo responsavel por definir um novo valor para login
		 * @param login O novo valor para login
		 */
		public void setLogin(String login);
		/**
		 * Metodo acessor para endereco
		 * @return O endereco
		 */
		public String getEndereco();
		/**
		 * Metodo acessor para email
		 * @return O email
		 */
		public String getEmail();
		/**
		 * Metodo acessor para o id do usuario
		 * @return O id do usuario
		 */
		public String getUserID();
		/**
		 * Metodo acessor para a carona
		 * @param idCarona O id da carona
		 * @return A carona
		 */
		public Carona getCarona(String idCarona);
		/**
		 * Metodo modificador do valor de senha
		 * @param senha O novo valor para senha
		 */
		public void setSenha(String senha);
		/**
		 * Metodo modificador para nome
		 * @param nome O novo valor para nome
		 */
		public void setNome(String nome);
		/**
		 * Metodo modificador para endereco
		 * @param endereco O novo endereco
		 */
		public void setEndereco(String endereco);
		/**
		 * Metodo modificador para email
		 * @param email O novo valor para email
		 */
		public void setEmail(String email);
		/**
		 * Hash Code para usuario
		 * @return o hashCode
		 */
		public int hashCode();
		/**
		 * Compara dois Usuarios
		 * @param obj O usuario a ser comparado
		 * @return True caso os usuarios sejam iguais e False caso contrario
		 */
		public boolean equals(Object obj);
		/**
		 * To string de usuario
		 * @return O toString de usuario
		 */
		public String toString();
		/**
		 * Metodo responsavel por cadastrar uma carona nas caronas do usuario
		 * @param origem A origem
		 * @param destino O destino
		 * @param data A data
		 * @param hora O horario
		 * @param vagas A quantidade de vagas
		 * @return O id da carona
		 * @throws Exception Excecao caso os dados da carona sejam invalidos
		 */
		public int cadastraCarona(String origem, String destino, String data, String hora, Integer vagas) throws Exception;
		/**
		 * 
		 * Metodo responsavel por cadastrar uma carona nas caronas do usuario
		 * @param idSessao O id da sessao do usuaeio
		 * @param origem A origem
		 * @param destino O destino
		 * @param data A data
		 * @param hora O horario
		 * @param vagas A quantidade de vagas
		 * @return O id da carona
		 * @throws Exception Excecao caso os dados da carona sejam invalidos
		 */
		public int cadastraCarona(String idSessao, String origem,String destino, String cidade,String data,String hora,String vagas) throws Exception;
		/**
		 * Metodo responsavel por locaclizar uma carona
		 * @param origem A origem
		 * @param destino O destino
		 * @return A lista de retorno
		 * @throws Exception Excecao retornada caso os dados passados sejam invalidos
		 */
		public List<Carona> localizaCarona(String origem, String destino) throws Exception;
		/**
		 * Retorna um iterator para a lista de caronas
		 * @return O iterator
		 */
		public Iterator<Carona> getCaronasIterator();
		/**
		 * Retorna um atributo para o usuario
		 * @param atributo O atributo procurado
		 * @return O valor do atributo
		 * @throws Exception Excecao caso os dados sejam invalidos
		 */
		public String getAtributo(String atributo)throws Exception;
		
}