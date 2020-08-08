import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MemberDriver {

    /**
     * This class has a main method that runs the application.
     * @param args
     */
    public static void main(String[] args) throws IOException {

        /**
         * Here, the system takes in all parameters of a Member object
         * and uses those fields to construct the object.
         */
        // Ask the user to register a new account
        // make this a method that returns void(?) instead of including in main(?)
        System.out.print("Please create an account by first entering a username: ");
        // Debug: if a word with a space in it is typed and then entered, the
        // scanner picks up the second word as password.

        // Initialize a BufferedReader object to take in user input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Create an object to temporarily hold user input before using it
        // as a parameter for the constructor.
        String username = reader.readLine();

        // Ask the user to create their password
        System.out.print("Please enter a password for your account: ");
        String password = reader.readLine();

        // Ask the user how much they would like to open their account with
        System.out.print("Please enter the amount you would like to use to open your account: ");
        double balance = Double.parseDouble(reader.readLine());

        // Construct the user object from user input using the parameterized constructor
        Member user1 = new Member(balance, username, password);
        System.out.print(user1.toString());

        /**
         * Here, the system asks the user to log in using their credentials.
         */
        // Login with existing credentials
        System.out.print("Please enter your username: ");
        String usernameInput = reader.readLine();
        if (usernameInput.equalsIgnoreCase(user1.getUsername())) {
            System.out.print("Please enter your password: ");
            String passwordInput = reader.readLine();
            if(passwordInput.equalsIgnoreCase(user1.getPassword())) {
                System.out.print("This is your account balance: S" + user1.getBalance());
            } else {
                System.out.print("Incorrect.");
            }
        } else {
            System.out.print("Incorrect");
        }


        /**
         * Here, the system asks the user how much they would like to deposit and changes their balance.
         */
        // Deposit funds into an account
        System.out.print("\n How much would you like to deposit: ");
        double fundsToDeposit = Double.parseDouble(reader.readLine());

        // Check for negative deposit
        while(fundsToDeposit < 0) {
            System.out.println("You cannot deposit negative funds.");
            System.out.println("How much would you like to deposit: ");
            fundsToDeposit = Double.parseDouble(reader.readLine());
        }
        user1.setBalance(user1.getBalance() + fundsToDeposit);
        System.out.print("\n Your new balance is: S" + user1.getBalance() + "\n");

        withdrawFunds(user1);



        /**
         * The system asks the user if they would like to see their balance.
         */
        // Check to see if they are logged in first?
        // View the balance
        System.out.println("Would you like to see your account balance? Type y/n: ");
        String yesOrNo = reader.readLine();
        if(yesOrNo.equalsIgnoreCase("y")) {
            System.out.println("Your account balance is: $" + user1.getBalance());
        } else {
            System.out.println("Goodbye");
        }


    } // end main

    public static void withdrawFunds(Member m) throws IOException {
        /**
         * The system asks the user how much they would like to withdraw and changes their balance.
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Withdraw funds into an account
        System.out.println("\n How much would you like to withdraw: ");
        double fundsToWithdraw = Double.parseDouble(reader.readLine());

        // Check for invalid values (what if user enters a String)
        while(fundsToWithdraw < 0 || m.getBalance() - fundsToWithdraw < 0) {
            // Negative withdrawal
            if(fundsToWithdraw < 0) {
                System.out.println("You cannot deposit negative funds.");
                System.out.println("How much would you like to withdraw: ");
                fundsToWithdraw = Double.parseDouble(reader.readLine());
            }
            // Overdraft
            else if(m.getBalance() - fundsToWithdraw < 0) {
                System.out.println("You cannot overdraft your account balance.");
                System.out.println("\n How much would you like to withdraw: ");
                fundsToWithdraw = Double.parseDouble(reader.readLine());
            }
        }

        m.setBalance(m.getBalance() - fundsToWithdraw);
        System.out.print("\n Your new balance is: S" + m.getBalance() + "\n");


    }

} // end class
