package grupo3si.server.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class UsuarioSimples implements Usuario {
	
	Perfil perfil;
	static int idContador = 0;
	int userID;
	String login;
	String senha;


	/**
	 * Metodo que cria um usuario simples.
	 * @param login
	 * @param senha
	 * @param nome
	 * @param endereco
	 * @param email
	 * @throws Exception
	 */
	public UsuarioSimples(String login, String senha, String nome, String endereco, String email) throws Exception   {

		this.checkUserData(login, nome, endereco, email);
		perfil = new Perfil(nome, endereco, email);
		idContador++;
		this.login = login;
		this.senha = senha;
		this.userID = idContador;
	}
	
	//metodos Get's
	/**
	 * Metodo que retorna um perfil de um usuario
	 */
	public Perfil getPerfil(){
		return perfil;
	}
	
	/**
	 *Metodo que retorna o login do usuario. 
	 */
	public String getLogin() {
		return login;
	}
	

	/**
	 *Metodo que retorna o endereco do usuario. 
	 */
	public String getEndereco() {
		return perfil.getEndereco();
	}
	
	/**
	 *Metodo que retorna o nome do usuario 
	 */
	public String getNome() {
		return perfil.getNome();
	}
	/**
	 * Metodo que retorna o email do usuario.
	 */
	public String getEmail() {
		return perfil.getEmail();
	}
	/**
	 * Metodo que retorna o ID do usuario.
	 */
	public String getUserID() {
		return String.valueOf(this.userID);
	}
	
	/**
	 * Metodo que retorna uma carona do usuario
	 */
	public Carona getCarona(String idCarona) {
		Iterator<Carona> caronasIt = perfil.getHistoricoDeCaronas().iterator();
		Carona carona = null;
		while(caronasIt.hasNext() && carona == null){
			Carona temporaria = caronasIt.next();
			if(temporaria.getId().equals(idCarona)) carona = temporaria;
		}
		return carona;
	}
	
	/**
	 * Metodo que retorna um iterador de caronas
	 */
	public Iterator<Carona> getCaronasIterator() {
		return perfil.getHistoricoDeCaronas().iterator();
	}


	//Metodos Set's
	/**
	 * Metodo que seta o login do usuario.
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * Metodo que da um review no usuario.
	 * @param review
	 * @throws OpcaoInvalidaException
	 */
	public void reviewVagaEmCarona(String review) throws OpcaoInvalidaException{
		perfil.reviewVagaEmCarona(review);
	}
	/**
	 * Metodo que muda a senha do usuario.
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	/**
	 * Metodo que muda o nome do usuario.
	 */
	public void setNome(String nome) {
		perfil.setNome(nome);
	}
	/**
	 * Metodo que muda o endereco do usuario.
	 */
	public void setEndereco(String endereco) {
		perfil.setEndereco(endereco);
	}
	/**
	 * metodo que muda o email do usuario.
	 */
	public void setEmail(String email) {
		perfil.setEmail(email);
	}

	/**
	 * Metodo que retorna o toString do usuario.
	 */
	public String toString() {
		return "Login: " + login + ", nome: " + perfil.getNome() + ", endereco: "
				+ perfil.getEndereco() + ", email: " + perfil.getEmail();
	}

	/**
	 * metodo que cadastra uma carona no usuario.
	 */
	public int cadastraCarona(String origem, String destino, String data, String hora, Integer vagas) throws Exception {
		Carona carona = new CaronaSimples(origem,destino,data,hora,vagas,false);
		perfil.add(carona);
		return Integer.valueOf(carona.getId());
	}
	/**
	 * metodo que cadatra uma carona municipal no usuario.
	 */
	public int cadastraCarona(String idSessao, String origem,String destino, String cidade,String data,String hora,String vagas) throws Exception{
		Carona carona = new CaronaMunicipal(origem, destino, data, hora, Integer.valueOf(vagas), cidade,true);
		perfil.add(carona);
		return Integer.valueOf(carona.getId());
	}
	
	/**
	 * metodo que localiza uma carona
	 */
	public List<Carona> localizaCarona(String origem, String destino) throws Exception{
		
		if(origem == null || origem.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*")){
			throw new OrigemInvalidaException();
		}
		if(destino == null || destino.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*")){
			throw new DestinoInvalidaException();
		}
		
		List<Carona> caronasLocalizadas = new LinkedList<Carona>();
		Iterator<Carona> caronasIt = perfil.getHistoricoDeCaronas().iterator();
		
		while(caronasIt.hasNext()){
			Carona carona = caronasIt.next();
			if(carona.getOrigem().equals(origem) && carona.getDestino().equals(destino)){
				caronasLocalizadas.add(carona);
			}else if(carona.getOrigem().equals(origem) && destino.equals("")){
				caronasLocalizadas.add(carona);
			}else if(carona.getDestino().equals(destino) && origem.equals("")){
				caronasLocalizadas.add(carona);
			}else if(origem.equals("") && destino.equals("")){
				caronasLocalizadas.add(carona);
			}
		}
		
		return caronasLocalizadas;
		
	}
	
	//metodos privados
	/**
	 * Metodo que checa dados do usuario.
	 * @param login
	 * @param nome
	 * @param endereco
	 * @param email
	 * @throws Exception
	 */
	private void checkUserData(String login,String nome, String endereco, String email) throws Exception{
		
		if ((login == null) || (login.equals(""))) {
			throw new InvalidLoginException();
		}
		if ((nome == null) || (nome.equals(""))) {
			throw new InvalidNameException();
		}
		if ((endereco == null) || (endereco.equals(""))) {
			throw new InvalidEnderecoException();
		}
		if ((email == null) || (email.equals(""))) {
			throw new InvalidEmailException();
		}
	}


	/**
	 * Metodo que retorna se dois objectos são iguais.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioSimples other = (UsuarioSimples) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	public String getSenha() {
		return senha;
	}
	public boolean cheksenha(String senha){
		return this.senha ==senha?true:false;
	}

	public Integer getCaronafaltosas() {
		return perfil.getCaronaFaltosas();
	}

	public Integer getCaronasSeguras() {
		return perfil.getCaronasSeguras();
	}

	public Integer getFaltaEmVagaDeCarona() {
		return perfil.getFaltaEmVagaDeCarona();
	}

	public List<Carona> getHistoricoDeCaronas() {
		return perfil.getHistoricoDeCaronas();
	}
	
	public List<Carona> getHistoricoEmVagasDeCaronas() {
		return perfil.getHistoricoEmVagasDeCaronas();
	}

	public Integer getPresencaEmVagaDeCarona() {
		return perfil.getPresencaEmVagaDeCarona();
	}

	public boolean checkSenha(String senha) {
		// TODO Auto-generated method stub
		return false;
	}
	


}