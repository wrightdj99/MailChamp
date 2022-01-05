import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaUtilMail {
    public static void sendMail(String senderAccount, String senderPassword, String receiver) throws Exception{
        String mailingList = "";
        int i = 0;
        Scanner scanner = new Scanner(System.in);
        mailingList = mailingList.concat(receiver);
        String another;
        String anotherRec;
        String path = "";

        while(i < 20) {
            System.out.println("Would you like to add another address? (Y or N)");
            anotherRec = scanner.nextLine();
            if(anotherRec.equalsIgnoreCase("Y")) {
                another = scanner.nextLine();
                mailingList = mailingList.concat(", " + another);
                i++;
            }
            else{
                break;
            }
        }
        System.out.println("What is the subject of the email you want to send?");
        String subject = scanner.nextLine();
        System.out.println("What is the message that you want to send?");
        String messageText = scanner.nextLine();
        System.out.println("Would you like to send an attachment with your message?");
        String fileAnswer = scanner.nextLine();
        if(fileAnswer.equalsIgnoreCase("Y")){
            System.out.println("Copy the path to your attachment here: ");
            path = scanner.nextLine();
        }
        else{
            path = "";
        }

        if(messageText != null && subject != null) {
            System.out.println("Preparing to send");
            System.out.println(mailingList);
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

            Message message = prepareMessage(session, senderAccount, mailingList, subject, messageText, path);

            Transport.send(message);
            System.out.println("Message sent");
        }
        else{
            throw new Exception("Message subject and message must not be null");
        }
    }

    public static Message prepareMessage(Session session, String senderAccount, String receivers, String subject, String text, String path) throws AddressException {
        Message message = null;
        text = text.concat("\n\nSENT WITH JAVA MAIL API");
        //System.out.println(receivers);
        int size = 5;
        //InternetAddress[] toParse = InternetAddress.parse(to, true);
        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderAccount));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receivers));
            message.setSubject(subject);

            BodyPart mailBodyPart = new MimeBodyPart();
            mailBodyPart.setText(text);

            MimeBodyPart mailAttachment = new MimeBodyPart();
            mailAttachment.attachFile(new File(path));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mailBodyPart);
            multipart.addBodyPart(mailAttachment);

            message.setContent(multipart);
        }
        catch(Exception ex){
            Logger.getLogger(JavaUtilMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

}


