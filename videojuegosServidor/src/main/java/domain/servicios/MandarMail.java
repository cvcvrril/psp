package domain.servicios;


import domain.ConstantsDomain;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;

import java.util.Properties;

@Log4j2
public class MandarMail {

    public void generateAndSendEmail(String to, String msg, String subject) throws MessagingException {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage generateMailMessage;

        mailServerProperties = System.getProperties();
        mailServerProperties.put(ConstantsDomain.MAIL_SMTP_PORT, Integer.parseInt(ConstantsDomain.NUMBER));
        mailServerProperties.put(ConstantsDomain.MAIL_SMTP_AUTH, ConstantsDomain.TRUE);
        mailServerProperties.put(ConstantsDomain.MAIL_SMTP_SSL_TRUST, ConstantsDomain.SMTP_GMAIL_COM);
        mailServerProperties.put(ConstantsDomain.MAIL_SMTP_STARTTLS_ENABLE, ConstantsDomain.TRUE);

        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        generateMailMessage.setSubject(subject);
        String emailBody = msg;
        generateMailMessage.setContent(emailBody, ConstantsDomain.CONTENT_TYPE);

        Transport transport = getMailSession.getTransport(ConstantsDomain.SMTP);

        transport.connect(ConstantsDomain.SMTP_GMAIL_COM,
                ConstantsDomain.MAIL,
                ConstantsDomain.PASSWORD);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}
