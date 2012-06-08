package grupo3si.server.model;

import java.util.Iterator;
import java.util.List;

import javax.naming.directory.InvalidAttributeValueException;

public class Usuario implements Interessado {

	private Perfil perfil;
	private static int idContador = 0;
	private int userID;
	private String login;
	private String senha;

	/**
	 * Metodo que cria um usuario simples.
	 * 
	 * @param login
	 * @param senha
	 * @param nome
	 * @param endereco
	 * @param email
	 * @throws Exception
	 */
	public Usuario(String login, String senha, String nome, String endereco,
			String email) throws Exception {

		this.checkUserData(login, nome, endereco, email);
		perfil = new Perfil(nome, endereco, email);
		idContador++;
		this.login = login;
		this.senha = senha;
		this.userID = idContador;
	}

	// metodos Get's
	/**
	 * Metodo que retorna um perfil de um usuario
	 */
	public Perfil getPerfil() {
		return perfil;
	}

	/**
	 * Metodo que retorna o login do usuario.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Metodo que retorna o endereco do usuario.
	 */
	public String getEndereco() {
		return perfil.getEndereco();
	}

	/**
	 * Metodo que retorna o nome do usuario
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
		while (caronasIt.hasNext() && carona == null) {
			Carona temporaria = caronasIt.next();
			if (temporaria.getId().equals(idCarona))
				carona = temporaria;
		}
		return carona;
	}

	/**
	 * Metodo que retorna um iterador de caronas
	 */
	public Iterator<Carona> getCaronasIterator() {
		return perfil.getHistoricoDeCaronas().iterator();
	}

	// Metodos Set's
	/**
	 * Metodo que seta o login do usuario.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Metodo que da um review no usuario.
	 * 
	 * @param review
	 * @throws OpcaoInvalidaException
	 */
	public void reviewVagaEmCarona(String review) throws OpcaoInvalidaException {
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
		return "Login: " + login + ", nome: " + perfil.getNome()
				+ ", endereco: " + perfil.getEndereco() + ", email: "
				+ perfil.getEmail();
	}

	/**
	 * metodo que cadastra uma carona no usuario.
	 */
	public int cadastraCarona(String origem, String destino, String data,
			String hora, Integer vagas) throws Exception {
		Carona carona = new CaronaSimples(origem, destino, data, hora, vagas,false);
		perfil.add(carona);
		return Integer.valueOf(carona.getId());
	}

	/**
	 * metodo que cadatra uma carona municipal no usuario.
	 */
	public int cadastraCarona(String idSessao, String origem, String destino,
			String cidade, String data, String hora, String vagas)
			throws Exception {
		Carona carona = new CaronaMunicipal(origem, destino, data, hora,
				Integer.valueOf(vagas), cidade, true);
		perfil.add(carona);
		return Integer.valueOf(carona.getId());
	}

	// metodos privados
	/**
	 * Metodo que checa dados do usuario.
	 * 
	 * @param login
	 * @param nome
	 * @param endereco
	 * @param email
	 * @throws InvalidEnderecoException 
	 * @throws Exception
	 */
	private void checkUserData(String login, String nome, String endereco,
			String email) throws InvalidAttributeValueException {

		if ((login == null) || (login.equals(""))) {
			throw new InvalidAttributeValueException("Login inválido");
		}
		if ((nome == null) || (nome.equals(""))) {
			throw new InvalidAttributeValueException("Nome inválido");
		}
		if ((endereco == null) || (endereco.equals(""))) {
			throw new InvalidAttributeValueException("Endereço inválido");
		}
		if ((email == null) || (email.equals(""))) {
			throw new InvalidAttributeValueException("Email inválido");
		}
	}

	/**
	 * Metodo que retorna se dois objetos são iguais.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	public boolean checkSenha(String senha){
		return this.senha.equals(senha)?true:false;
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

	public void avisa(String diaCriacao, String horaCriacao, String emailDoCriador) {
		String mensagem = "Carona cadastrada no dia "
				+ diaCriacao
				+ ", às "
				+ horaCriacao
				+ " de acordo com os seus interesses registrados. Entrar em contato com "
				+ emailDoCriador;
		perfil.addMensagem(mensagem);
	}

	public List<String> getMensagens() {
		return perfil.getMensagens();
	}
	
}