import java.util.ArrayList;
import java.util.Scanner;

public class CommandLineInterface {
    public void mainInterface() throws Exception {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> mailingList = new ArrayList<String>();
        //int i = 0;

        System.out.println("---------------------------------");
        System.out.println("Welcome to Mailchamp!            ");
        System.out.println("---------------------------------");
        System.out.println("Enter your Gmail username: ");
        String senderAccount = scan.nextLine();
        System.out.println("Enter your Gmail password: ");
        String senderPassword = scan.nextLine();
        System.out.println("Who is this message being sent to? You can add up to 20 recipients");
        String recipient = scan.nextLine();

        //char [] password = cons.readPassword("Enter your Gmail password: ");
        //String senderPassword = new String(password);

        /*for(int i = 0; i <= numberRecipients; i++){
            System.out.println("Who is this message being sent to?");
            String recipient = scan.nextLine();
            mailingList.add(recipient);
        }*/
        JavaUtilMail javaUtilMail = new JavaUtilMail();
        javaUtilMail.sendMail(senderAccount, senderPassword, recipient);
            //System.out.println(mailingList.get(x));
        //System.out.println("Your username: " + senderAccount);
        //System.out.println("Your password: " + senderPassword);
    }
}
