package Grupo3Si1.handles;

public enum AtributosDeUsuario {

	NOME("nome"), EMAIL("email"), SENHA("senha"), LOGIN("login"), ENDERECO(
			"endereco");

	String atribute;
	/**
	 * 
	 * @param atribute
	 */
	AtributosDeUsuario(String atribute) {
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
