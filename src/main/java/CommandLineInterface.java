import java.util.ArrayList;
import java.util.Scanner;

public class CommandLineInterface {
    public void mainInterface() throws Exception {
        Scanner scan = new Scanner(System.in);
        //CLI and instructions are down below
        System.out.println("---------------------------------");
        System.out.println("Welcome to Mailchamp!            ");
        System.out.println("---------------------------------");
        //Authentication
        System.out.println("Enter your Gmail username: ");
        String senderAccount = scan.nextLine();
        System.out.println("Enter your Gmail password: ");
        String senderPassword = scan.nextLine();
        /*Authentication may have been fairly straight-forward, but
        figuring out how to add more than one recipient was a bit hairier.*/
        System.out.println("Who is this message being sent to? You can add up to 20 recipients");
        String recipient = scan.nextLine();


        JavaUtilMail javaUtilMail = new JavaUtilMail();
        javaUtilMail.sendMail(senderAccount, senderPassword, recipient);

    }
}
