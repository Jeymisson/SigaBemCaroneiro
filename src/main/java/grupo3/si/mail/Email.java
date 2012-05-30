package grupo3.si.mail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.InvalidNameException;

/**
 * Classe responsavel por contactar a internet e enviar as recomendacoes ao
 * usuario do sitema por email.
 * 
 */
public class Email extends Mensagem {

	private String usuario = "sigabemcaroneiro@gmail.com";
	private String senha = "negojeylukinhasjordinhoeu";

	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	private static final String SMTP_PORT = "465";

	public static final String EMAILDOREMETENTE = "sigabemcaroneiro@gmail.com";
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private String to;
	private String from;

	/**
	 * Metodo responsavel por receber o email do destinatario das recomendacoes
	 * junto com as mesmas.
	 * 
	 * @param to
	 *            email do destinatario das recomencaoes.
	 * @param text
	 *            texto a ser enviado, recomendacoes.
	 * @throws InvalidNameException
	 * @throws NullPointerException
	 * 
	 */
	public Email(String to, String text) throws InvalidNameException,
			NullPointerException {
		super(text);
		this.to = to;
		this.from = "WebService-SigaBemCaroneiro";
	}

	/**
	 * Retorna o email do destinatario das recoemendacoes.
	 * 
	 * @return o email do destinatario das recoemendacoes.
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Retorna o email do remetente.
	 * 
	 * @return o email do remetente.
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Metodo responsavel por logar no email do remetente e enviar ao
	 * destinatario as recomendacoes.
	 */
	public boolean run() {
		try {
			Properties mailProps = new Properties();
			mailProps.put("mail.smtp.host", SMTP_HOST_NAME);

			Autenticacao auth = null;
			auth = new Autenticacao(usuario, senha);

			mailProps.put("mail.smtp.auth", "true");
			mailProps.put("mail.user", usuario);
			mailProps.put("mail.from", getFrom());
			mailProps.put("mail.to", getTo());

			mailProps.put("mail.smtp.host", SMTP_HOST_NAME);
			mailProps.put("mail.smtp.auth", "true");
			mailProps.put("mail.debug", "false");

			mailProps.put("mail.smtp.port", SMTP_PORT);
			mailProps.put("mail.smtp.socketFactory.port", SMTP_PORT);
			mailProps.put("mail.smtp.socketFactory.class", SSL_FACTORY);
			mailProps.put("mail.smtp.socketFactory.fallback", "false");

			Session mailSession = Session.getDefaultInstance(mailProps, auth);

			mailSession.setDebug(false);

			Message email = new MimeMessage(mailSession);
			email.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(getTo()));

			email.setFrom(new InternetAddress(getFrom()));
			email.setSubject(getSubject());
			email.setContent(getText(), "text/plain");

			Transport tr = mailSession.getTransport("smtp");
			tr.connect(SMTP_HOST_NAME, usuario, senha);
			email.saveChanges();
			tr.sendMessage(email, email.getAllRecipients());
			tr.close();
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
}
