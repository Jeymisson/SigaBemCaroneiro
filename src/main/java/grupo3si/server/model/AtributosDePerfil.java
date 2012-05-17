package grupo3si.server.model;


public enum AtributosDePerfil {
	
	
	NOME("nome"),ENDERECO("endereco"),HISTORICODECARONAS("historico de caronas"),EMAIL("email"),
	HISTORICOVAGASCARONAS("historico de vagas em caronas"),CARONASSEGURAS("caronas seguras e tranquilas"),
	CARONASNAOFUNCIONOU("caronas que não funcionaram"),FALTAEMVAGASDECARONAS("faltas em vagas de caronas"),
	PRESENCAEMVAGASDECARONA("presenças em vagas de caronas");
	
	String atributo;
	/**
	 * Utilizado na busca pelo atributo correto
	 * @param atribute O atributo
	 */
	 AtributosDePerfil(String atributo) {
		this.atributo = atributo;
	}
	 /**
	  * Metodo acessor para atributo
	  * @return O atributo
	  */
	public String getAtribute(){
		return atributo;
	}
			
}
