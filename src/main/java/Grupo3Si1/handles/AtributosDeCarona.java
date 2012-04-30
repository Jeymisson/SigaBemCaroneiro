package Grupo3Si1.handles;

public enum AtributosDeCarona {
	
	ORIGEM("origem"), DESTINO("destino"), DATA("data"), HORA("hora"), VAGA(
			"vagas"), EHMUNICIPAL("ehMunicipal"), PONTO_DE_ENCONTRO("Ponto de Encontro");
	
	String atribute;
	/**
	 * 
	 * @param atribute
	 */
	AtributosDeCarona(String atribute) {
		this.atribute = atribute;
	}
	/**
	 * 
	 * @return
	 */
	public String getatribute() {
		return atribute;
	}
}
