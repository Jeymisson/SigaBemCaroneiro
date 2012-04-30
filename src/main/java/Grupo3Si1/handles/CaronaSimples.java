package Grupo3Si1.handles;

public class CaronaSimples extends CaronaAbstract {

	boolean ehMunicipal;
	
	public CaronaSimples(String origem, String destino, String data, String hora, Integer vagas, Usuario donoDaCarona) throws Exception {
		super(origem, destino, data, hora, vagas, donoDaCarona);
		ehMunicipal = false;
	}


	@Override
	public boolean ehMunicipal() {
		return ehMunicipal;
	}
	

}

