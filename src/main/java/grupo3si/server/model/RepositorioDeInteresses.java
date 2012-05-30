package grupo3si.server.model;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeInteresses {
	
	private List<Interesse> interesses;
	
	protected RepositorioDeInteresses() {
		interesses = new ArrayList<Interesse>();
	}
	
	/**
	 * Esse metodo cadastra um novo interesse e chama o metodo que verifica no
	 * repositorio de usuarios se alguma carona com o perfil do interesse ja
	 * existe
	 * 
	 * @param interessado
	 * @param origem
	 * @param destino
	 * @param data
	 * @param horaInicio
	 * @param horaFim
	 * @return
	 */
	public String adicionaInteresse(Interessado interessado, String origem, String destino, String data, String horaInicio, String horaFim){
		Interesse interesse = new Interesse(interessado, origem, destino, data, horaInicio, horaFim);
		interesses.add(interesse);
		verificaSeCaronaJaExiste(interesse);
		return interesse.getId();
	}

	private void verificaSeCaronaJaExiste(Interesse interesse) {
		// TODO Auto-generated method stub
		//varre repositorio de usuarios procurando por carona com os dados do interesse
		//remove interesse da lista de interesses caso a carona seja encontrada
	}
	
	/**
	 * Esse metodo vai ser chamado pelo controlador assim que uma nova carona eh
	 * criada para verificar se existem interesses cadastrados nesse tipo de
	 * carona
	 * 
	 * @param origem
	 * @param destino
	 * @param data
	 * @param hora
	 * @param emailCriadorCarona
	 */
	public void verificaSeExisteInteresse(String origem, String destino, String data, String hora, String emailCriadorCarona){
		for(Interesse interesse : interesses){
			if(interesse.isCompativel(origem, destino, data, hora)){
				interesse.getInteressado().avisa(data, hora, emailCriadorCarona);
			}
		}
	}

}
