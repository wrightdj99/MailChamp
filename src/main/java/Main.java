import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Main {
    public static void main(String[] args) throws Exception {
        //JavaUtilMail.sendMail("wrightdj99@gmail.com");
        Scanner scan = new Scanner(System.in);
        System.out.println("---------------------------------");
        System.out.println("Welcome to Mailchamp!           |");
        System.out.println("---------------------------------");
        System.out.println("Enter your Gmail username: ");
        String senderAccount = scan.nextLine();
        System.out.println("Enter your Gmail password: ");
        String senderPassword = scan.nextLine();
        System.out.println("Who is this message being sent to?");
        String recipient = scan.nextLine();
        //System.out.println("Your username: " + senderAccount);
        //System.out.println("Your password: " + senderPassword);
        JavaUtilMail.sendMail(senderAccount, senderPassword, recipient);
    }
}
