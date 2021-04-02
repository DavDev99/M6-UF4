package m9.UF3.SocolsiServeis.Activitat7;

/*
 * Fitxer: SenMailUsingJavaMail.java.
 */
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendMailUsingJavaMail {

    public static void main(String[] args) {
        try {
            final String username = "profebaixcamp@gmail.com";
            final String password = "insbaixcamp123";

            JavaMailSender sender = new JavaMailSender("smtp.gmail.com", 587);

            sender.addRecipient("pferre2@xtec.cat");
            //sender.addRecipient("algumes@xtec.cat");
            sender.setSender("profebaixcamp@gmail.com");
            sender.setSubject("prova2");
            sender.setMailText("Body is a body");
            sender.sendUsingTLSAuthentication(username, password);
            //sender.sendUsingSSLAuthentication(username, password);
            //sender.sendUsingAuthentication(username, password);
            //sender.send();
        } catch (Exception ex) {
            Logger.getLogger(SendMailUsingJavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
