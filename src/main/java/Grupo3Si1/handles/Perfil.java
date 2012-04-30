package Grupo3Si1.handles;

import java.util.LinkedList;
import java.util.List;

import Grupo3Si1.exceptions.*;

public class Perfil {
	
	private String nome;
	private String endereco;
	private List<Carona> historicoDeCaronas;
	private String email;
	private List<Carona> historicoEmVagasDeCaronas;
	private Integer caronasSeguras;
	private Integer caronaFulerada;
	private Integer FaltaEmVagaDeCarona;
	private Integer presencaEmVagaDeCarona;
	
	/**
	 * construtor de perfil
	 * @param nome
	 * @param endereco
	 * @param email
	 */
	public Perfil(String nome, String endereco, String email){
		
		setNome(nome);
		setEmail(email);
		setEndereco(endereco);
		setCaronaFulerada(0);
		setCaronasSeguras(0);
		setFaltaEmVagaDeCarona(0);
		setPresencaEmVagaDeCarona(0);
		historicoDeCaronas = new LinkedList<Carona>();
		historicoEmVagasDeCaronas =  new LinkedList<Carona>();
		
	}
	/**
	 * Metodo que da o review em um perfil
	 * @param review
	 * @throws OpcaoInvalidaException
	 */
	public void reviewVagaEmCarona(String review) throws OpcaoInvalidaException{
		if(review.trim().equalsIgnoreCase("não faltou")){
			presencaEmVagaDeCarona++;
		}else if (review.trim().equalsIgnoreCase("faltou")){
			this.FaltaEmVagaDeCarona++;
		}else if(review.trim().equalsIgnoreCase("segura e tranquila")){
			caronasSeguras++;
		}else if(review.trim().equalsIgnoreCase("não funcionou")){
			caronaFulerada++;
		}else{
			throw new OpcaoInvalidaException();
		}
	}
	/**
	 * Metodo que retorna o nome do perfil
	 * @return String nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Metodo que muda o nome de um perfil
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * metodo que retorna o endereco do perfil
	 * @return
	 */
	public String getEndereco() {
		return endereco;
	}
	/**
	 * metodo que muda o endereco do perfil
	 * @param endereco
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/**
	 * metodo que retorna o historico de caronas do perfil
	 * @return String historicoDeCaronas
	 */
	public String getHistoricoDeCaronas() {
		String resp = "";
		if(historicoDeCaronas.size()==0) return"[]";
		if(historicoDeCaronas.size() == 1){
			resp += "[" + historicoDeCaronas.get(0) + "]";
		}
	
		if(historicoDeCaronas.size() > 1){
			resp = "[";
			for(int index = 0;index<historicoDeCaronas.size();index++){
				resp += historicoDeCaronas.get(index)+",";
			}
			resp = resp.substring(0, resp.length()-1);
			resp+="]";
		}
		return resp; 
	}
	/**
	 * metodo que muda Historico
	 * @param historicoDeCaronas
	 */
	public void setHistoricoDeCaronas(List<Carona> historicoDeCaronas) {
		this.historicoDeCaronas = historicoDeCaronas;
	}
	/**
	 * Metodo que retorna o email do usuario
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * metodo que muda o email do perfil
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * metodo que retorna o historico de caronas que o perfil pegou
	 * @return
	 */
	public String getHistoricoEmVagasDeCaronas() {
		String resp = "";
		if(historicoEmVagasDeCaronas.size()==0) return"[]";
		if(historicoEmVagasDeCaronas.size() == 1){
			resp += "[" + historicoEmVagasDeCaronas.get(0) + "]";
		}
			
		if(historicoEmVagasDeCaronas.size() > 1){
			resp = "["+historicoEmVagasDeCaronas.get(0);
			
			for(int index = 1;index<historicoEmVagasDeCaronas.size();index++){
				resp +=","+historicoEmVagasDeCaronas.get(index)+"";
			}
			resp+="]";
		}
		return resp;
	}
	/**
	 * 
	 * @return
	 */
	public List<Carona> getCaronasQueEuPego(){
		return historicoEmVagasDeCaronas;
	}
	/**
	 * 
	 * @param historicoEmVagasDeCaronas
	 */
	public void setHistoricoDeVagasDeCaronas(List<Carona> historicoEmVagasDeCaronas) {
		this.historicoEmVagasDeCaronas = historicoEmVagasDeCaronas;
	}
	/**
	 * Metodo que adiona uma carona no historico
	 * @param carona
	 */
	public void addMeuHistorico(Carona carona){
		historicoEmVagasDeCaronas.add(carona);
	}
	/**
	 * metodo que retorna o numero de caronas seguras dadas pelo perfil
	 * @return
	 */
	public Integer getCaronasSeguras() {
		return caronasSeguras;
	}
	/**
	 * metodo que muda o numero de caronas seguras dadas pelo perfil
	 * @param caronasSeguras
	 */
	public void setCaronasSeguras(Integer caronasSeguras) {
		this.caronasSeguras = caronasSeguras;
	}
	/**
	 * metodo que retorna o numero de caronas que o perfil nao compareceu
	 * @return
	 */
	public Integer getCaronaFulerada() {
		return caronaFulerada;
	}
	/**
	 * metodo que muda o numero de caronas que o perfil nao compareceu
	 * @param caronaFulerada
	 */
	public void setCaronaFulerada(Integer caronaFulerada) {
		this.caronaFulerada = caronaFulerada;
	}
	/**
	 * metodo que retorna o numero de caronas que o perfil nao foi.
	 * @return caronas que o perfil nao foi
	 */
	public Integer getFaltaEmVagaDeCarona() {
		return FaltaEmVagaDeCarona;
	}
	/**
	 * metodo que seta o numero de caronas que o perfil faltou.
	 * @param faltaEmVagaDeCarona
	 */
	public void setFaltaEmVagaDeCarona(Integer faltaEmVagaDeCarona) {
		FaltaEmVagaDeCarona = faltaEmVagaDeCarona;
	}
	/**
	 * metodo que retorna o numero de caronas que o perfil comprareceu
	 * @return presencaEmVagaDeCaronas
	 */
	public Integer getPresencaEmVagaDeCarona() {
		return presencaEmVagaDeCarona;
	}
	/**
	 * metodo que muda o numero de presenca em caronas
	 * @param presencaEmVagaDeCarona
	 */
	public void setPresencaEmVagaDeCarona(Integer presencaEmVagaDeCarona) {
		this.presencaEmVagaDeCarona = presencaEmVagaDeCarona;
	}
	/**
	 * metodo que retorna o historico de caronas do perfil
	 * @return List<Carona>
	 */
	public List<Carona> getCaronas(){
		return historicoDeCaronas;
	}
	/**
	 * Metodo que adiciona uma nova carona
	 * @param carona
	 */
	public void add(Carona carona){
		historicoDeCaronas.add(carona);
	}
	/**
	 * Metodo que retorna um atributo do perfil
	 * @param atributo
	 * @return
	 * @throws Exception
	 */
	public String getAtributoPerfil(String atributo) throws Exception{
		
		String resp = "";
		if(atributo == null || atributo.equals("")){
			throw new InvalidAtributeException();
		}
		
		if (AtributosDePerfil.NOME.getAtribute().equalsIgnoreCase(atributo)) {
			resp = getNome();
		} else if (AtributosDePerfil.ENDERECO.getAtribute().equalsIgnoreCase(
				atributo)) {
			resp = getEndereco();
		} else if (AtributosDePerfil.EMAIL.getAtribute().equalsIgnoreCase(atributo)) {
			resp = getEmail();
		}else  if(AtributosDePerfil.CARONASNAOFUNCIONOU.getAtribute().equalsIgnoreCase(atributo)){
			resp = getCaronaFulerada()+"";
		}else if(AtributosDePerfil.CARONASSEGURAS.getAtribute().equalsIgnoreCase(atributo)){
			resp = getCaronasSeguras()+"";
		}else if(AtributosDePerfil.FALTAEMVAGASDECARONAS.getAtribute().equalsIgnoreCase(atributo)){
			resp = getFaltaEmVagaDeCarona()+"";
		}else if(AtributosDePerfil.HISTORICODECARONAS.getAtribute().equalsIgnoreCase(atributo)){
			resp = getHistoricoDeCaronas();
		}else if (AtributosDePerfil.HISTORICOVAGASCARONAS.getAtribute().equalsIgnoreCase(atributo)){
			resp = getHistoricoEmVagasDeCaronas()+"";
		}else if(AtributosDePerfil.PRESENCAEMVAGASDECARONA.getAtribute().equalsIgnoreCase(atributo)){
			resp = getPresencaEmVagaDeCarona()+"";
		}else
			throw new InexistentAtributeException();

		return resp;
	}

}