package net.ddns.iiiedug02.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/*
 * Google將於５／３０後修改登入驗證方式，屆時可能無法使用本工具寄信
 */
public class SenedMailUtil {

  private String toAddress;

  private String text;

  public void setText(String text) {
    this.text = text;
  }

  public void setToAddress(String toAddress) {
    // Recipient's email ID needs to be mentioned.
    this.toAddress = toAddress;
  }

  public void send() {
    if (null == this.text || null == this.toAddress) {
      return;
    }

    // Sender's email ID needs to be mentioned
    String from = "noreplyiiiedug02@gmail.com";

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
      message.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));

      // Set Subject: header field
      message.setSubject("HI,這是用JAVA記得信喔");

      // Now set the actual message
      message.setText(text);

      System.out.println("sending...");
      // Send message
      Transport.send(message);
      System.out.println("Sent message successfully....");
    } catch (MessagingException mex) {
      mex.printStackTrace();
    }
  }

}
