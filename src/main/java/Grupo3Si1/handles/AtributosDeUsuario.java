package Grupo3Si1.handles;

public enum AtributosDeUsuario {

	NOME("nome"), EMAIL("email"), SENHA("senha"), LOGIN("login"), ENDERECO(
			"endereco");

	String atribute;
	/**
	 * Utilizado na busca pelo atributo correto
	 * @param atribute O atributo
	 */
	AtributosDeUsuario(String atribute) {
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
