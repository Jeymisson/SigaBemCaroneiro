package grupo3si.server.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Perfil {

	private String nome;
	private String endereco;
	private List<Carona> historicoDeCaronas;
	private String email;
	private List<Carona> historicoEmVagasDeCaronas;
	private Integer caronasSeguras;
	private Integer caronafaltosas;
	private Integer FaltaEmVagaDeCarona;
	private Integer presencaEmVagaDeCarona;
	private List<String> mensagens;
	private List<String> mensagensMotorista;

	/**
	 * construtor de perfil
	 * 
	 * @param nome
	 * @param endereco
	 * @param email
	 */
	public Perfil(String nome, String endereco, String email) {

		setNome(nome);
		setEmail(email);
		setEndereco(endereco);
		setCaronaFulerada(0);
		setCaronasSeguras(0);
		setFaltaEmVagaDeCarona(0);
		setPresencaEmVagaDeCarona(0);
		historicoDeCaronas = new LinkedList<Carona>();
		historicoEmVagasDeCaronas = new LinkedList<Carona>();
		mensagens = new ArrayList<String>();
		mensagensMotorista = new ArrayList<String>();
		
	}

	/**
	 * Metodo que da o review em um perfil
	 * 
	 * @param review
	 * @throws OpcaoInvalidaException
	 */
	public void reviewVagaEmCarona(String review) throws OpcaoInvalidaException {
		if (review.trim().equalsIgnoreCase("não faltou")) {
			presencaEmVagaDeCarona++;
		} else if (review.trim().equalsIgnoreCase("faltou")) {
			this.FaltaEmVagaDeCarona++;
		} else if (review.trim().equalsIgnoreCase("segura e tranquila")) {
			caronasSeguras++;
		} else if (review.trim().equalsIgnoreCase("não funcionou")) {
			caronafaltosas++;
		} else {
			throw new OpcaoInvalidaException();
		}
	}

	/**
	 * Metodo que retorna o nome do perfil
	 * 
	 * @return String nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo que muda o nome de um perfil
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * metodo que retorna o endereco do perfil
	 * 
	 * @return
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * metodo que muda o endereco do perfil
	 * 
	 * @param endereco
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * metodo que retorna o historico de caronas do perfil
	 * 
	 * @return String historicoDeCaronas
	 */
	public List<Carona> getHistoricoDeCaronas() {
		return historicoDeCaronas;
	}

	/**
	 * metodo que muda Historico
	 * 
	 * @param historicoDeCaronas
	 */
	public void setHistoricoDeCaronas(List<Carona> historicoDeCaronas) {
		this.historicoDeCaronas = historicoDeCaronas;
	}

	/**
	 * Metodo que retorna o email do usuario
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * metodo que muda o email do perfil
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * metodo que retorna o historico de caronas que o perfil pegou
	 * 
	 * @return
	 */
	public List<Carona> getHistoricoEmVagasDeCaronas() {
		return historicoEmVagasDeCaronas;
	}

	/**
	 * 
	 * @return
	 */
	public List<Carona> getCaronasQueEuPego() {
		return historicoEmVagasDeCaronas;
	}

	/**
	 * 
	 * @param historicoEmVagasDeCaronas
	 */
	public void setHistoricoDeVagasDeCaronas(
			List<Carona> historicoEmVagasDeCaronas) {
		this.historicoEmVagasDeCaronas = historicoEmVagasDeCaronas;
	}

	/**
	 * Metodo que adiona uma carona no historico
	 * 
	 * @param carona
	 */
	public void addMeuHistorico(Carona carona) {
		historicoEmVagasDeCaronas.add(carona);
	}

	/**
	 * metodo que retorna o numero de caronas seguras dadas pelo perfil
	 * 
	 * @return
	 */
	public Integer getCaronasSeguras() {
		return caronasSeguras;
	}

	/**
	 * metodo que muda o numero de caronas seguras dadas pelo perfil
	 * 
	 * @param caronasSeguras
	 */
	public void setCaronasSeguras(Integer caronasSeguras) {
		this.caronasSeguras = caronasSeguras;
	}

	/**
	 * metodo que retorna o numero de caronas que o perfil nao compareceu
	 * 
	 * @return
	 */
	public Integer getCaronaFaltosas() {
		return caronafaltosas;
	}

	/**
	 * metodo que muda o numero de caronas que o perfil nao compareceu
	 * 
	 * @param caronaFulerada
	 */
	public void setCaronaFulerada(Integer caronaFulerada) {
		this.caronafaltosas = caronaFulerada;
	}

	/**
	 * metodo que retorna o numero de caronas que o perfil nao foi.
	 * 
	 * @return caronas que o perfil nao foi
	 */
	public Integer getFaltaEmVagaDeCarona() {
		return FaltaEmVagaDeCarona;
	}

	/**
	 * metodo que seta o numero de caronas que o perfil faltou.
	 * 
	 * @param faltaEmVagaDeCarona
	 */
	public void setFaltaEmVagaDeCarona(Integer faltaEmVagaDeCarona) {
		FaltaEmVagaDeCarona = faltaEmVagaDeCarona;
	}

	/**
	 * metodo que retorna o numero de caronas que o perfil comprareceu
	 * 
	 * @return presencaEmVagaDeCaronas
	 */
	public Integer getPresencaEmVagaDeCarona() {
		return presencaEmVagaDeCarona;
	}

	/**
	 * metodo que muda o numero de presenca em caronas
	 * 
	 * @param presencaEmVagaDeCarona
	 */
	public void setPresencaEmVagaDeCarona(Integer presencaEmVagaDeCarona) {
		this.presencaEmVagaDeCarona = presencaEmVagaDeCarona;
	}

	/**
	 * Metodo que adiciona uma nova carona
	 * 
	 * @param carona
	 */
	public void add(Carona carona) {
		historicoDeCaronas.add(carona);
		System.out.println(carona.getCarona());
		addMensagemMotorista(carona.getCarona());
	}

	public void addMensagem(String mensagem) {
		mensagens.add(mensagem);
	}
	
	public List<String> getMensagens(){
		return mensagens;
	}

	private void addMensagemMotorista(String mensagem) {
		mensagensMotorista.add(mensagem);
		
	}
	
	public List<String> getMensagensMotorista(){
		return mensagensMotorista;
	}

}