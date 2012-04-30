package Grupo3Si1.handles;

import java.util.Iterator;
import java.util.List;

public interface Usuario {
		/**
		 * 
		 * @return
		 */
		public String getLogin();
		/**
		 * 
		 * @return
		 */
		public Perfil getPerfil();
		/**
		 * 
		 * @return
		 */
		public String getSenha();
		/**
		 * 
		 * @return
		 */
		public String getNome();
		/**
		 * 
		 * @param login
		 */
		public void setLogin(String login);
		/**
		 * 
		 * @return
		 */
		public String getEndereco();
		/**
		 * 
		 * @return
		 */
		public String getEmail();
		/**
		 * 
		 * @return
		 */
		public String getUserID();
		/**
		 * 
		 * @param idCarona
		 * @return
		 */
		public Carona getCarona(String idCarona);
		/**
		 * 
		 * @param senha
		 */
		public void setSenha(String senha);
		/**
		 * 
		 * @param nome
		 */
		public void setNome(String nome);
		/**
		 * 
		 * @param endereco
		 */
		public void setEndereco(String endereco);
		/**
		 * 
		 * @param email
		 */
		public void setEmail(String email);
		/**
		 * 
		 * @return
		 */
		public int hashCode();
		/**
		 * 
		 * @param obj
		 * @return
		 */
		public boolean equals(Object obj);
		/**
		 * 
		 * @return
		 */
		public String toString();
		/**
		 * 
		 * @param origem
		 * @param destino
		 * @param data
		 * @param hora
		 * @param vagas
		 * @return
		 * @throws Exception
		 */
		public int cadastraCarona(String origem, String destino, String data, String hora, Integer vagas) throws Exception;
		/**
		 * 
		 * @param idSessao
		 * @param origem
		 * @param destino
		 * @param cidade
		 * @param data
		 * @param hora
		 * @param vagas
		 * @return
		 * @throws Exception
		 */
		public int cadastraCarona(String idSessao, String origem,String destino, String cidade,String data,String hora,String vagas) throws Exception;
		/**
		 * 
		 * @param origem
		 * @param destino
		 * @return
		 * @throws Exception
		 */
		public List<Carona> localizaCarona(String origem, String destino) throws Exception;
		/**
		 * 
		 * @return
		 */
		public Iterator<Carona> getCaronasIterator();
		/**
		 * 
		 * @param atributo
		 * @return
		 * @throws Exception
		 */
		public String getAtributo(String atributo)throws Exception;
		
}