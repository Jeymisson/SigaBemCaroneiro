package grupo3si.server.model;

public enum AtributosDeSolicitacao {

	ORIGEM("origem"), DESTINO("destino"), DONO_DA_CARONA("Dono da carona"), DONO_DA_SOLICITACAO("Dono da solicitacao"), PONTO_DE_DESTINO(
			"Ponto de Encontro");
	
	String atribute;
	/**
	 * Utilizado na busca pelo atributo correto
	 * @param atribute O atributo
	 */
	AtributosDeSolicitacao(String atribute) {
		this.atribute = atribute;
	}

	/**
	 * Metodo acessor para atributo
	 * 
	 * @return O atributo
	 */
	public String getatribute() {
		return atribute;
	}
	
}
