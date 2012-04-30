package Grupo3Si1.handles;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.TreeMap;

import Grupo3Si1.exceptions.*;

public class RepositorioDeUsuarios {

	private AbstractMap<String, Usuario> userRep; ;
	private static RepositorioDeUsuarios Repository = null;
	
	//Metodos Get's
	/**
	 * 
	 * @return
	 */
	public static RepositorioDeUsuarios getInstance() {
		if (Repository == null) {
			// "lazy instantiation"
			Repository = new RepositorioDeUsuarios();
		}
		return Repository;
	}
	/**
	 * 
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public Usuario getUser(String login) throws Exception {

		Usuario user = null;

		if(login == null || login.equals("")){
			throw new InvalidLoginException();

		}else if(userRep.containsKey(login)) {
			user = userRep.get(login);

		} else {
			throw new InexistentLoginException();
		}

		return user;
	}
	
	
	/**
	 * Acessador de Usuário pelo ID
	 * @param idUsuario Id do usuario
	 * @return O usuario respectivo aquele ID.
	 */
	public Usuario getUserPorId(String idUsuario) {
		Iterator<Usuario> userIt = this.getUsuarios();
		Usuario usuario = null;
		
		while(userIt.hasNext()){
			Usuario nextUsuario = userIt.next();
			if(idUsuario.equals(nextUsuario.getUserID())){
				usuario = nextUsuario; 
				break;
			}
		}
		return usuario;
		
	}
	/**
	 * 
	 * @param idCarona
	 * @return
	 * @throws Exception
	 */
	public Usuario getDonoDe(String idCarona) throws Exception {

		Usuario user = null;

		Iterator<Usuario> userIt = userRep.values().iterator();
		while(userIt.hasNext()){
			Usuario nextUser = userIt.next();
			if(nextUser.getCarona(idCarona) != null){
				user = nextUser;
			}
		}
		return user;
	}
	/**
	 * 
	 * @param idCarona
	 * @return
	 */
	public Carona getCarona(String idCarona) {
		Iterator<Usuario> userIt = this.getUsuarios();
		Carona rice = null;
		while(userIt.hasNext() && rice == null){

			Usuario nextUser = userIt.next();
			rice =  nextUser.getCarona(idCarona); 
			
		}
		return rice;
	}

	//Metodos de funcionalidade da classe
	/**
	 * 
	 */
	public void clear(){
		userRep.clear();
	}
	/**
	 * 
	 * @param login
	 * @param user
	 * @throws Exception
	 */
	public void addUser(String login, Usuario user)throws Exception {

		checkDuplicatedData(user);
		userRep.put(login, user);
	}
	
	//metodos privados	
	/**
	 * 
	 */
	private RepositorioDeUsuarios() {
		userRep = new TreeMap<String, Usuario>();
	} /* o compilador não vai gerar um construtor default público */

	/**
	 * 
	 * @param user
	 * @throws Exception
	 */
	private void checkDuplicatedData(Usuario user) throws Exception {
		Iterator<Usuario> userIt = this.getUsuarios();

		while(userIt.hasNext()){

			Usuario nextUser = userIt.next();

			if(user.getLogin().equals(nextUser.getLogin()))
				throw new duplicatedLoginException();

			if(user.getEmail().equals(nextUser.getEmail()))
				throw new duplicatedEmailException();

		}		
	}
	/**
	 * 
	 * @return
	 */
	public Iterator<Usuario> getUsuarios() {
		return userRep.values().iterator();
	}
	/**
	 * 
	 * @return
	 */
	public AbstractMap<String, Usuario> getRepositorio() {
		return userRep;
	}
	/**
	 * 
	 * @param newRep
	 */
	public void setRepositorio(AbstractMap<String, Usuario> newRep){
		userRep = newRep;
	}
}
