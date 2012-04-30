package Grupo3Si1.handles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.TreeMap;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Classe que manipula objetos para XML e os salva em um arquivo XML
 * @author Jeymisson
 * 
 */
public class GerenciaDadosEmXML {
	private XStream xstream;

	/**
	 * Construtor de GerenciaDadosEmXML
	 */
	public GerenciaDadosEmXML() {
		xstream = new XStream(new DomDriver());
		xstream.alias("Usuario", UsuarioSimples.class);
		xstream.alias("Carona", CaronaSimples.class);	

	}
	/**
	 * 
	 * @param usuarios
	 * @return
	 */
	private String usuarioToXML(AbstractMap<String, Usuario> usuarios){
		String xml = xstream.toXML(usuarios);
		return xml;
	}
	/**
	 * 
	 * @param negociacoes
	 * @return
	 */
	private String negociacoesToXML(ControladorDeNegociacoes negociacoes) {
		String xml = xstream.toXML(negociacoes);
		return xml;
	}
	
	/**
	 * Método que salva Usuarios em um arquivo XML
	 * @param fileName Arquivo onde sera salvo
	 * @param mapaRepositorio Objeto que sera salvo
	 */
	public void salvaUsuariosXML(String fileName, AbstractMap<String, Usuario> mapaRepositorio){
		String xml = this.usuarioToXML(mapaRepositorio);
		escreveEmArquivo(fileName,xml);		
	}
	
	
	/**
	 * Método que salva Negociações em um arquivo XML
	 * @param fileName arquivo onde sera salvo
	 * @param negociacoes Objeto a ser salvo
	 */
	public void salvaNegociacoesXML(String fileName, ControladorDeNegociacoes negociacoes){
		String xml = this.negociacoesToXML(negociacoes);
		escreveEmArquivo(fileName,xml);
	}
	
	/**
	 * 
	 * @param fileName
	 * @param dados
	 */
	private void escreveEmArquivo(String fileName, String dados){	
		PrintWriter outputStream = null;
		try{
			outputStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName,false),"UTF-8")));
			outputStream.write(dados);
			
		}catch(FileNotFoundException e2){
			e2.printStackTrace();

		}catch(IOException e){
			e.printStackTrace();
		}
		finally{
			if (outputStream != null) {
	            outputStream.close();
	         }
		}
	}
	/**
	 * 
	 * @param fileName
	 */
	public void zeraArquivo(String fileName) {
		escreveEmArquivo(fileName, "");	
	}
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public AbstractMap<String, Usuario> getRepositorioUsuarios(String fileName) {
		File file = new File(fileName);
		TreeMap<String, Usuario> mapaUsuarios = (TreeMap<String,Usuario>)xstream.fromXML(file);	
		return mapaUsuarios;
		
	}
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public ControladorDeNegociacoes getControladorDeNegociacoes(String fileName) {
		File file = new File(fileName);
		ControladorDeNegociacoes controlador = (ControladorDeNegociacoes)xstream.fromXML(file);
		return controlador;
	}
}
