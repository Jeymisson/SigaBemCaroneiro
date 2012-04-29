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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getHistoricoDeCaronas() {
		String resp = "";
		if(historicoDeCaronas.size()==0) return"[]";
		if(historicoDeCaronas.size() == 1){
			resp += "[" + historicoDeCaronas.get(0) + "]";
		}
	
		if(historicoDeCaronas.size() > 1){
			resp = "[";
			for(int index = 0;index<historicoDeCaronas.size()-1;index++){
				resp +=", "+historicoDeCaronas.get(index)+"";
			}
			resp+="]";
		}
		return resp; 
	}

	public void setHistoricoDeCaronas(List<Carona> historicoDeCaronas) {
		this.historicoDeCaronas = historicoDeCaronas;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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
	
	public List<Carona> getCaronasQueEuPego(){
		return historicoEmVagasDeCaronas;
	}

	public void setHistoricoDeVagasDeCaronas(List<Carona> historicoEmVagasDeCaronas) {
		this.historicoEmVagasDeCaronas = historicoEmVagasDeCaronas;
	}
	public void addMeuHistorico(Carona carona){
		historicoEmVagasDeCaronas.add(carona);
	}

	public Integer getCaronasSeguras() {
		return caronasSeguras;
	}

	public void setCaronasSeguras(Integer caronasSeguras) {
		this.caronasSeguras = caronasSeguras;
	}

	public Integer getCaronaFulerada() {
		return caronaFulerada;
	}

	public void setCaronaFulerada(Integer caronaFulerada) {
		this.caronaFulerada = caronaFulerada;
	}

	public Integer getFaltaEmVagaDeCarona() {
		return FaltaEmVagaDeCarona;
	}

	public void setFaltaEmVagaDeCarona(Integer faltaEmVagaDeCarona) {
		FaltaEmVagaDeCarona = faltaEmVagaDeCarona;
	}

	public Integer getPresencaEmVagaDeCarona() {
		return presencaEmVagaDeCarona;
	}

	public void setPresencaEmVagaDeCarona(Integer presencaEmVagaDeCarona) {
		this.presencaEmVagaDeCarona = presencaEmVagaDeCarona;
	}
	public List<Carona> getCaronas(){
		return historicoDeCaronas;
	}
	public void add(Carona carona){
		historicoDeCaronas.add(carona);
	}
	
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
