package net.ddns.iiiedug02.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import net.ddns.iiiedug02.model.bean.MailBean;



/*
 * Google將於５／３０後修改登入驗證方式，屆時可能無法使用本工具寄信
 */
public class SenedMailUtil {

  public void send(MailBean mail) {

    // Assuming you are sending email from through gmails smtp
    String host = "smtp.gmail.com";

    // Get system properties
    Properties properties = System.getProperties();

    // Setup mail server
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", "465");
    properties.put("mail.smtp.ssl.enable", "true");
    properties.put("mail.smtp.auth", "true");

    // Get the Session object.// and pass username and password
    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("noreplyiiiedug02@gmail.com", "1113DU902");
      }
    });

    // Used to debug SMTP issues
    session.setDebug(true);

    try {
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);

      // Set From: header field of the header.
      message.setFrom(new InternetAddress(mail.getFromAddress()));

      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO,
          new InternetAddress("noreplyiiiedug02@gmail.com"));

      // Set Subject: header field
      message.setSubject(mail.getSubject());

      // Now set the actual message
      message.setText(mail.getName() + " :\n" + mail.getMsg());

      System.out.println("sending...");
      // Send message
      Transport.send(message);
      System.out.println("Sent message successfully....");
    } catch (MessagingException mex) {
      mex.printStackTrace();
    }
  }

}
