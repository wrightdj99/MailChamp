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
        //Declaration of class-level variables.
        String mailingList = "";
        int i = 0;
        Scanner scanner = new Scanner(System.in);
        mailingList = mailingList.concat(receiver);
        String another;
        String anotherRec;
        String path = "";

        //You can add up to 20 mail recipients if you wish. If not, that's cool too.
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
        //Setting message parameters. Subject, Text and a possible File attachment are all here
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

            //All this deals with networking protocols for sending a message over the internet; Secure Mail Transfer Protocol
            //needs specifications for authorization, TLS (securing contents), the mail provider, and finally the port number
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    //Password auth
                    return new PasswordAuthentication(senderAccount, senderPassword);
                }
            });
            //Maybe a bit of a long parameter code smell, but it's fine. We have a lot flying around here.
            Message message = prepareMessage(session, senderAccount, mailingList, subject, messageText, path);
            //Actually sends the message over the net
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
        //Get ready for a longer than average try-catch
        try {
            //Set up the new MimeMessage component. The recipient list ended up being parsed from a string of comma separated addresses
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderAccount));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receivers));
            message.setSubject(subject);
            //Text component
            BodyPart mailBodyPart = new MimeBodyPart();
            mailBodyPart.setText(text);
            //Attachment component
            MimeBodyPart mailAttachment = new MimeBodyPart();
            mailAttachment.attachFile(new File(path));
            //Adding it all together
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mailBodyPart);
            multipart.addBodyPart(mailAttachment);
            //We're done! Just return the message now
            message.setContent(multipart);
        }
        catch(Exception ex){
            Logger.getLogger(JavaUtilMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

}


