package Grupo3Si1.handles;

public enum AtributosDeUsuario {

	NOME("nome"), EMAIL("email"), SENHA("senha"), LOGIN("login"), ENDERECO(
			"endereco");

	String atribute;

	AtributosDeUsuario(String atribute) {
		this.atribute = atribute;
	}

	public String getatribute() {
		return atribute;
	}

}
