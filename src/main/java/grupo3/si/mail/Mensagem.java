package grupo3.si.mail;
import javax.naming.InvalidNameException;

/**
 * Classe responsavel por configurar a mensagem que sera enviada ao usuario do
 * sistema por email
 * 
 */
public class Mensagem { 
        private String subject; 
        private String text; 
        
        /**
         * Metodo que configura a mensagem a ser enviada
         * ao destinatario por email.
         * @param text
         * 			texto da mensagem, recomendacoes.
         * @throws InvalidNameException
         * @throws NullPointerException
         */
        public Mensagem(String text) throws InvalidNameException, NullPointerException { 
                this.subject = "Siga Bem Caroneiro"; 
                this.text = text; 
        } 
        

        /**
         * Retorna o subject do email.
         * @return
         * 			o subject do email.
         */
        public String getSubject() { 
                return subject; 
        }
        
        
        /**
         * Retorna o texto do email.
         * @return
         * 			o texto do email.
         */
        public String getText() { 
                return text; 
        }
     
    
} 