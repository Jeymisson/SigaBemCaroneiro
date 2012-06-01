package grupo3si.server.model;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import javax.naming.directory.InvalidAttributeValueException;

public class RepositorioDeUsuarios {

	private AbstractMap<String, Usuario> userRep;
	private static RepositorioDeUsuarios Repository = null;

	//Metodos Get's
	/**
	 * Metodo que retorna a instancia do repositorio.
	 * @return RepositorioDeUsuarios
	 */
	public static RepositorioDeUsuarios getInstance() {
		if (Repository == null) {
			// "lazy instantiation"
			Repository = new RepositorioDeUsuarios();
		}
		return Repository;
	}
	/**
	 * Metodo que pesquisa um usuario
	 * @param login
	 * @return Usuario
	 * @throws InvalidAttributeValueException 
	 * @throws InexistentLoginException 
	 * @throws Exception
	 */
	public Usuario getUser(String login) throws InvalidAttributeValueException, InexistentLoginException {

		Usuario user = null;

		if(login == null || login.equals("")){
			throw new InvalidAttributeValueException("Login inválido");

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
	 * Metodo que pesquisa o dono de uma carona
	 * @param idCarona
	 * @return Usuario
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
	 * Metodo que pesquisa uma carona
	 * @param idCarona
	 * @return Carona
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


	/**
	 * metodo que localiza uma carona
	 */
	public List<Carona> localizaCaronaOrigemDestino(String origem, String destino)
			throws Exception {

		if (origem == null
				|| origem.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%Â¨&*0-9].*")) {
			throw new OrigemInvalidaException();
		}
		if (destino == null
				|| destino
				.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%Â¨&*0-9].*")) {
			throw new DestinoInvalidaException();
		}

		List<Carona> caronasLocalizadas = new LinkedList<Carona>();
		Iterator<Usuario> userIt = this.getUsuarios();

		while(userIt.hasNext()){
			Iterator<Carona> caronasIt = userIt.next().getCaronasIterator();
			while (caronasIt.hasNext()) {
				Carona carona = caronasIt.next();
				if (carona.getOrigem().equals(origem)
						&& carona.getDestino().equals(destino)) {
					caronasLocalizadas.add(carona);
				} else if (carona.getOrigem().equals(origem) && destino.equals("")) {
					caronasLocalizadas.add(carona);
				} else if (carona.getDestino().equals(destino) && origem.equals("")) {
					caronasLocalizadas.add(carona);
				} else if (origem.equals("") && destino.equals("")) {
					caronasLocalizadas.add(carona);
				}
			}
		}


		return caronasLocalizadas;

	}

	//Metodos de funcionalidade da classe
	/**
	 * Metodo que limpa o repositorio de usuarios.
	 */
	public void clear(){
		userRep.clear();
	}
	/**
	 * Metodo que adiciona um usuario.
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
	 * Metodo que pesquisa se já existe o usuario cadastrado.
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
	 * Metodo que restona um iterator de usuarios.
	 * @return Iterator<Usuario>
	 */
	public Iterator<Usuario> getUsuarios() {
		return userRep.values().iterator();
	}
	/**
	 * Metodo que retorna o Map de usuarios.
	 * @return  AbstractMap<String, Usuario>
	 */
	public AbstractMap<String, Usuario> getRepositorio() {
		return userRep;
	}
	/**
	 * Metodo que muda o mapa de usuarios.
	 * @param newRep
	 */
	public void setRepositorio(AbstractMap<String, Usuario> newRep){
		userRep = newRep;
	}

	/**
	 * Metodo que localiza uma carona municipal pela cidade, origem e destino da carona.
	 * @param cidade Cidade a que a carona pertence
	 * @param origem Ponto de saída da carona
	 * @param destino Ponto de Destino da carona
	 * @return Lista com caronas localizadas
	 * @throws OrigemInvalidaException 
	 * @throws DestinoInvalidaException 
	 * @throws CidadeInexistenteException 
	 */
	public List<Carona> localizaCaronaMunicipioOrigemDestino(String cidade,
			String origem, String destino) throws OrigemInvalidaException, DestinoInvalidaException, CidadeInexistenteException {
		if (origem == null
				|| origem.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*")) {
			throw new OrigemInvalidaException();
		}
		if (destino == null
				|| destino
						.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*")) {
			throw new DestinoInvalidaException();
		}

		if(cidade ==null || cidade.equals("")) throw new CidadeInexistenteException();

		List<Carona> caronasLocalizadas = new LinkedList<Carona>();
		Iterator<Usuario> userIt = this.getUsuarios();

		while(userIt.hasNext()){
			Iterator<Carona> caronasIt = userIt.next().getCaronasIterator();
			
			while (caronasIt.hasNext()) {
				Carona carona = caronasIt.next();
				
				if(carona.getCidade().equals(cidade)){					
					if (carona.getOrigem().equals(origem)
							&& carona.getDestino().equals(destino)) {
						caronasLocalizadas.add(carona);
					} else if (carona.getOrigem().equals(origem) && destino.equals("")) {
						caronasLocalizadas.add(carona);
					} else if (carona.getDestino().equals(destino) && origem.equals("")) {
						caronasLocalizadas.add(carona);
					} else if (origem.equals("") && destino.equals("")) {
						caronasLocalizadas.add(carona);
					}
				}
			}
		}

		return caronasLocalizadas;
	}
}