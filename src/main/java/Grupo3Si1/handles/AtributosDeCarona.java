package Grupo3Si1.handles;

public enum AtributosDeCarona {
	
	ORIGEM("origem"), DESTINO("destino"), DATA("data"), HORA("hora"), VAGA(
			"vagas"), EHMUNICIPAL("ehMunicipal"), PONTO_DE_ENCONTRO("Ponto de Encontro");
	
	String atribute;
	/**
	 * Utilizado na busca pelo atributo correto
	 * @param atribute O atributo
	 */
	AtributosDeCarona(String atribute) {
		this.atribute = atribute;
	}
	/**
	 * Metodo acessor para atributo
	 * @return O atributo
	 */
	public String getatribute() {
		return atribute;
	}
}
