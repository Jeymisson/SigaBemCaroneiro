package grupo3.si.mail;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Classe responsavel por autenticar o endereco de email do sistema, para que o
 * mesmo possa enviar as recomendacoes para o usuario via web.
 * 
 */
class Autenticacao extends Authenticator { 
        private String usuario; 
        private String senha; 
        
        /**
         * Autentica o email do sistema.
         * @param usuario
         * 			usuario
         * @param senha
         * 			senha
         */
        public Autenticacao(String usuario, String senha) { 
                this.usuario = usuario; 
                this.senha = senha; 
        } 
  
        protected PasswordAuthentication getPasswordAuthentication() { 
                return new PasswordAuthentication (usuario,senha); 
        } 
}

