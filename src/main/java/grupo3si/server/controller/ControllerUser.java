package grupo3si.server.controller;

import java.util.AbstractMap;
import java.util.List;
import java.util.TreeMap;

import javax.naming.directory.InvalidAttributeValueException;

import grupo3si.server.model.*;

public class ControllerUser implements IntefaceControllerUser{

	private  RepositorioDeUsuarios rep;

	// Map <idUser, User>
	AbstractMap<String, Usuario> sessoesAbertas;

	public ControllerUser() {
		this.rep = RepositorioDeUsuarios.getInstance();
		sessoesAbertas = new TreeMap<String, Usuario>();
	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		Usuario user = new Usuario(login, senha, nome, endereco, email);
		rep.addUser(login, user);
	}
	public Carona getCarona(String idCarona) {
		return rep.getCarona(idCarona);
	}
	public  Usuario getUser(String login) throws InvalidAttributeValueException, InexistentLoginException {
		return rep.getUser(login);
	}
	public Usuario getUserPorId(String idSessao) {
		return rep.getUserPorId(idSessao);
	}
	public Usuario getDonoDe(String id) throws Exception {
		return rep.getDonoDe(id);
	}
	public List<Carona> getHistoricoDeCaronas(String IdSessao) throws Exception{
		return getDonoDe(IdSessao).getHistoricoDeCaronas();
	}
	public  void clear() {
		rep.clear();
	}
	public AbstractMap<String, Usuario> getRepositorio() {
		return rep.getRepositorio();
	}
	public void setRepositorio(AbstractMap<String, Usuario> newRep){
		rep.setRepositorio(newRep);
	}
	public List<Carona> localizaCaronaOrigemDestino(String origem,String destino) throws Exception {
		return rep.localizaCaronaOrigemDestino(origem, destino);
	}
	public List<Carona> localizaCaronaMunicipioOrigemDestino(String cidade,
			String origem, String destino) throws OrigemInvalidaException, DestinoInvalidaException, CidadeInexistenteException {
		return rep.localizaCaronaMunicipioOrigemDestino(cidade, origem, destino);
	}

	public void sessoesAbertasClear() {
		sessoesAbertas.clear();

	}

	public void sessoesAbertasPut(String id, Usuario user) {
		sessoesAbertas.put(id,user);

	}

	public void removeSessoesAbertas(String userID) {
		sessoesAbertas.remove(userID);
	}

	public Usuario getUserSessaoAberta(String id) {
		return sessoesAbertas.get(id);
	}

}
