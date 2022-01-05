import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        //Different reader for username and recipient, barely noticeable to user
        CommandLineInterface cli = new CommandLineInterface();
        cli.mainInterface();
    }
}
