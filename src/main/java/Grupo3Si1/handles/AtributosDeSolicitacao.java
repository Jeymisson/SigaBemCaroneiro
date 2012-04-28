package Grupo3Si1.handles;

public enum AtributosDeSolicitacao {

	ORIGEM("origem"), DESTINO("destino"), DONO_DA_CARONA("Dono da carona"), DONO_DA_SOLICITACAO("Dono da solicitacao"), PONTO_DE_DESTINO(
			"Ponto de Encontro");
	
	String atribute;

	AtributosDeSolicitacao(String atribute) {
		this.atribute = atribute;
	}

	public String getatribute() {
		return atribute;
	}
	
}
