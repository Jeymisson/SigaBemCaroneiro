package Grupo3Si1.handles;

import java.util.Iterator;
import java.util.List;

public interface Usuario {
		
		public String getLogin();
		
		public Perfil getPerfil();
		
		public String getSenha();
		
		public String getNome();
		
		public void setLogin(String login);

		public String getEndereco();
		
		public String getEmail();

		public String getUserID();
		
		public Carona getCarona(String idCarona);

		public void setSenha(String senha);

		public void setNome(String nome);

		public void setEndereco(String endereco);

		public void setEmail(String email);

		public int hashCode();
		
		public boolean equals(Object obj);

		public String toString();

		public int cadastraCarona(String origem, String destino, String data, String hora, Integer vagas) throws Exception;

		public int cadastraCarona(String idSessao, String origem,String destino, String cidade,String data,String hora,String vagas) throws Exception;
		
		public List<Carona> localizaCarona(String origem, String destino) throws Exception;
		
		public Iterator<Carona> getCaronasIterator();

		public String getAtributo(String atributo)throws Exception;
		
}