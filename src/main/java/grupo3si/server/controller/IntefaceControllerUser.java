package grupo3si.server.controller;

import grupo3si.server.model.Carona;
import grupo3si.server.model.CidadeInexistenteException;
import grupo3si.server.model.DestinoInvalidaException;
import grupo3si.server.model.InexistentLoginException;
import grupo3si.server.model.OrigemInvalidaException;
import grupo3si.server.model.Usuario;

import java.util.AbstractMap;
import java.util.List;

import javax.naming.directory.InvalidAttributeValueException;

public interface IntefaceControllerUser {

	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws Exception;

	public Carona getCarona(String idCarona);
	
	public  Usuario getUser(String login) throws InvalidAttributeValueException, InexistentLoginException;
	
	public Usuario getUserPorId(String idSessao);
	
	public Usuario getDonoDe(String id) throws Exception;
	
	public List<Carona> getHistoricoDeCaronas(String IdSessao) throws Exception;
	
	public  void clear();
	
	public AbstractMap<String, Usuario> getRepositorio();
	
	public void setRepositorio(AbstractMap<String, Usuario> newRep);
	
	public List<Carona> localizaCaronaOrigemDestino(String origem,String destino) throws Exception;
	
	public List<Carona> localizaCaronaMunicipioOrigemDestino(String cidade,String origem, String destino) throws OrigemInvalidaException, DestinoInvalidaException, CidadeInexistenteException;

	public void sessoesAbertasClear();
	
	public void sessoesAbertasPut(String id, Usuario user) ;

	public void removeSessoesAbertas(String userID);
	
	public Usuario getUserSessaoAberta(String id);
}
