package Grupo3Si1.handles;

public enum AtributosDeCarona {
	
	ORIGEM("origem"), DESTINO("destino"), DATA("data"), HORA("hora"), VAGA(
			"vagas"), EHMUNICIPAL("ehMunicipal");
	
	String atribute;

	AtributosDeCarona(String atribute) {
		this.atribute = atribute;
	}

	public String getatribute() {
		return atribute;
	}
}
