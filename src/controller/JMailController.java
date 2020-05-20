package controller;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JMailController {
    Properties emailProp;
    Session mailSession;
    MimeMessage emailMessage;

    public JMailController() {
    }

    public static void main(String[] args) throws AddressException, MessagingException {
        JMailController javaEmail = new JMailController();
        javaEmail.setMailServerProperties();
        javaEmail.createEmailMessage();
        javaEmail.sendEmail();
    }

    public void setMailServerProperties() throws MessagingException {
        String emailPort = "587";
        this.emailProp = System.getProperties();
        this.emailProp.put("mail.smtp.port", emailPort);
        String[] hedefler = new String[]{""};
        String emailKonu = "Java Email";
        String emailMetni = "JavaMail API deneme mail'idir.";
        this.mailSession = Session.getDefaultInstance(this.emailProp,null);
        this.emailMessage = new MimeMessage(this.mailSession);

        for(int i = 0; i < hedefler.length; ++i) {
            this.emailMessage.addRecipient(RecipientType.TO, new InternetAddress(hedefler[i]));
        }

        this.emailMessage.setSubject(emailKonu);
        this.emailMessage.setContent(emailMetni, "text/html");
        this.emailProp.put("mail.smtp.auth", "true");
        this.emailProp.put("mail.smtp.starttls.enable", "true");
    }

    public void createEmailMessage() throws MessagingException {
    }

    public void sendEmail() throws AddressException, MessagingException {
        String emailHost = "smtp.gmail.com";
        String fromUser = "" //GMail kullanıcı adı;
        String fromUserEmailPassword = "" //GMail şifre;
        Transport transport = this.mailSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        transport.sendMessage(this.emailMessage, this.emailMessage.getAllRecipients());
        transport.close();
        System.out.println("Mail Başarıyla gönderildi.");
    }
}
