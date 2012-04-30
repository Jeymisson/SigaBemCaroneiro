package Grupo3Si1.handles;


public enum AtributosDePerfil {
	
	
	NOME("nome"),ENDERECO("endereco"),HISTORICODECARONAS("historico de caronas"),EMAIL("email"),
	HISTORICOVAGASCARONAS("historico de vagas em caronas"),CARONASSEGURAS("caronas seguras e tranquilas"),
	CARONASNAOFUNCIONOU("caronas que não funcionaram"),FALTAEMVAGASDECARONAS("faltas em vagas de caronas"),
	PRESENCAEMVAGASDECARONA("presenças em vagas de caronas");
	
	String atributo;
	/**
	 * 
	 * @param atributo
	 */
	 AtributosDePerfil(String atributo) {
		this.atributo = atributo;
	}
	 /**
	  * 
	  * @return
	  */
	public String getAtribute(){
		return atributo;
	}
			
}
