import java.util.Scanner;

public class UserDriver {


    public static void main(String[] args) {

        // Ask the user to register a new account
        System.out.print("Please create an account by first entering a username: ");

        // Initialize a Scanner object to take in user input
        Scanner scanner = new Scanner(System.in);

        // Create an object to temporarily hold user input before using it
        // as a parameter for the constructor.
        String username = scanner.next();

        // Ask the user to create their password
        System.out.print("Please enter a password for your account: ");
        String password = scanner.next();

        System.out.print("Please enter the amount you would like to use to open your account: ");
        System.out.print("This should be in a 'dollars and cents' format: ");
        double balance = scanner.nextDouble();

        // Construct the user object from user input using the parameterized constructor
        User user1 = new User(balance, username, password);
        System.out.print(user1.toString());

        // Login with existing credentials
        System.out.print("Please enter your username: ");
        String usernameInput = scanner.next();
        if (usernameInput.equalsIgnoreCase(user1.getUsername())) {
            System.out.print("Please enter your password: ");
            String passwordInput = scanner.next();
            if(passwordInput.equalsIgnoreCase(user1.getPassword())) {
                System.out.print("This is your account balance: S" + user1.getBalance());
            } else {
                System.out.print("Incorrect.");
            }
        } else {
            System.out.print("Incorrect");
        }

        // Deposit funds into an account
        System.out.print("\n How much would you like to deposit in dollars and cents: ");
        double fundsToDeposit = scanner.nextDouble();
        while(fundsToDeposit < 0) {
            System.out.println("You cannot deposit negative funds.");
            System.out.println("How much would you like to deposit in dollars and cents: ");
            fundsToDeposit = scanner.nextDouble();
        }
        user1.setBalance(user1.getBalance() + fundsToDeposit);
        System.out.print("\n Your new balance is: S" + user1.getBalance() + "\n");

        // Withdraw funds into an account
        System.out.println("\n How much would you like to withdraw in dollars and cents: ");
        double fundsToWithdraw = scanner.nextDouble();
        // Check for overdraft
        if (user1.getBalance() - fundsToWithdraw < 0.0) {
            System.out.println("You cannot overdraft your account balance.");
        } else {
            // Check for negative withdrawal
            while(fundsToDeposit < 0) {
                System.out.println("You cannot deposit negative funds.");
                System.out.println("How much would you like to deposit in dollars and cents: ");
                fundsToDeposit = scanner.nextDouble();
            }
            user1.setBalance(user1.getBalance() - fundsToWithdraw);
            System.out.println("Your new balance is: S" + user1.getBalance());
        }

        // View the balance
        System.out.println("Would you like to see your account balance? Type y/n: ");
        String yesOrNo = scanner.next();
        if(yesOrNo.equalsIgnoreCase("y")) {
            System.out.println("Your account balance is: $" + user1.getBalance());
        } else {
            System.out.println("Goodbye");
        }


    } // end main

} // end class
