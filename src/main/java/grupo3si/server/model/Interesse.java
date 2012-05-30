package grupo3si.server.model;

public class Interesse {
	
	private final Interessado interessado;
	private final String origem;
	private final String destino;
	private final String data;
	private final String horaInicio;
	private final String horaFim;
	private final String id;
	private static Integer contId=0;
	
	public Interesse(Interessado interessado, String origem, String destino, String data, String horaInicio, String horaFim) {
		this.interessado = interessado;
		this.origem = origem;
		this.destino = destino;
		this.data = data;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.id = (++contId).toString();
	}
	
	public Interessado getInteressado() {
		return interessado;
	}
	public String getOrigem() {
		return origem;
	}
	public String getDestino() {
		return destino;
	}
	public String getData() {
		return data;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public String getHoraFim() {
		return horaFim;
	}
	public String getId() {
		return id;
	}
	
	public boolean isCompativel(String origem, String destino, String data, String hora){
		//TODO
		//verifica a compatibilidade da carona criada com o interesse this
		return false;
	}
	
}
