package Grupo3Si1.handles;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Grupo3Si1.exceptions.*;

public class UsuarioSimples implements Usuario {
	
	Perfil perfil;
	static int idContador = 0;
	int userID;
	String login;
	String senha;


	/**
	 * 
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
	 * 
	 */
	public Perfil getPerfil(){
		return perfil;
	}
	/**
	 * 
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * 
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * 
	 */
	public String getEndereco() {
		return perfil.getEndereco();
	}
	/**
	 * 
	 */
	public String getNome() {
		return perfil.getNome();
	}
	/**
	 * 
	 */
	public String getEmail() {
		return perfil.getEmail();
	}
	/**
	 * 
	 */
	public String getUserID() {
		return String.valueOf(this.userID);
	}
	/**
	 * 
	 */
	public String getAtributo(String atributo)	throws Exception {
		return perfil.getAtributoPerfil(atributo);
	}
	
	/**
	 * 
	 */
	public Carona getCarona(String idCarona) {
		Iterator<Carona> caronasIt = perfil.getCaronas().iterator();
		Carona carona = null;
		while(caronasIt.hasNext() && carona == null){
			Carona temporaria = caronasIt.next();
			if(temporaria.getId().equals(idCarona)) carona = temporaria;
		}
		return carona;
	}
	
	/**
	 * 
	 */
	public Iterator<Carona> getCaronasIterator() {
		return perfil.getCaronas().iterator();
	}


	//Metodos Set's
	/**
	 * 
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * 
	 * @param review
	 * @throws OpcaoInvalidaException
	 */
	public void reviewVagaEmCarona(String review) throws OpcaoInvalidaException{
		perfil.reviewVagaEmCarona(review);
	}
	/**
	 * 
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	/**
	 * 
	 */
	public void setNome(String nome) {
		perfil.setNome(nome);
	}
	/**
	 * 
	 */
	public void setEndereco(String endereco) {
		perfil.setEndereco(endereco);
	}
	/**
	 * 
	 */
	public void setEmail(String email) {
		perfil.setEmail(email);
	}

	/**
	 * 
	 */
	public String toString() {
		return "Login: " + login + ", nome: " + perfil.getNome() + ", endereco: "
				+ perfil.getEndereco() + ", email: " + perfil.getEmail();
	}

	/**
	 * 
	 */
	public int cadastraCarona(String origem, String destino, String data, String hora, Integer vagas) throws Exception {
		Carona carona = new CaronaSimples(origem,destino,data,hora,vagas,this);
		perfil.add(carona);
		return Integer.valueOf(carona.getId());
	}
	/**
	 * 
	 */
	public int cadastraCarona(String idSessao, String origem,String destino, String cidade,String data,String hora,String vagas) throws Exception{
		Carona carona = new CaronaMunicipal(origem, destino, data, hora, Integer.valueOf(vagas), this, cidade);
		perfil.add(carona);
		return Integer.valueOf(carona.getId());
	}
	
	/**
	 * 
	 */
	public List<Carona> localizaCarona(String origem, String destino) throws Exception{
		
		if(origem == null || origem.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*")){
			throw new OrigemInvalidaException();
		}
		if(destino == null || destino.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*")){
			throw new DestinoInvalidaException();
		}
		
		List<Carona> caronasLocalizadas = new LinkedList<Carona>();
		Iterator<Carona> caronasIt = perfil.getCaronas().iterator();
		
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
	 * 
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
	 * 
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



}
