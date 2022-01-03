import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaUtilMail {
    public static void sendMail(String senderAccount, String senderPassword, String receiver) throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the subject of the email you want to send?");
        String subject = scanner.nextLine();
        System.out.println("What is the message that you want to send?");
        String messageText = scanner.nextLine();
        if(messageText != null && subject != null) {
            System.out.println("Preparing to send");
            Properties properties = new Properties();

            //mail.smtp.auth = deals with authentication for mail
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    //return super.getPasswordAuthentication();
                    return new PasswordAuthentication(senderAccount, senderPassword);
                }
            });

            Message message = prepareMessage(session, senderAccount, receiver, subject, messageText);

            Transport.send(message);
            System.out.println("Message sent");
        }
        else{
            throw new Exception("Message subject and message must not be null");
        }
    }

    private static Message prepareMessage(Session session, String senderAccount, String receiver, String subject, String text){
        Message message = null;
        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject(subject);
            message.setText(text);
        }
        catch(Exception ex){
            Logger.getLogger(JavaUtilMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }
}
