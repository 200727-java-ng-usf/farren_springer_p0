package com.revature.revabank.screens;

import com.revature.revabank.services.AccountService;

import java.io.IOException;
import java.text.NumberFormat;

import static com.revature.revabank.AppDriver.app;

// TODO documentation on methods (param...etc)
public class AccessAccountsScreen extends Screen {

    /**
     * AccountService is a dependency to the AccessAccountsScreen
     */
    private AccountService accountService;
    NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(); // for currencies

    // Inject the dependency through the constructor (constructor injection)
    public AccessAccountsScreen(AccountService accountService) {
        super("AccessAccountsScreen", "/accessAccounts");
//        System.out.println("[LOG] - Instantiating " + this.getClass().getName());

        // loosely coupled, because this class is not responsible for instantiation of a UserService
        this.accountService = accountService;

    }

    /**
     * Renders the open a new account screen menu to the console.
     */
    @Override
    public void render() {

        String userSelection;
        System.out.println("Rendering " + app.getCurrentUser().getFirstName() + "'s Accounts...");

        while (app.isSessionValid()) {

            System.out.println("\n+---------------------------------+\n");
            accountService.showActiveAccounts(app.getCurrentUser().getId());
            System.out.println("1) Deposit Funds");
            System.out.println("2) Withdraw Funds");
            System.out.println("3) Delete An Account");
            System.out.println("4) Go Back");

            try {
                System.out.print("Selection: ");
                userSelection = app.getConsole().readLine();

                switch (userSelection) {
                    case "1": // deposit funds case

                        Integer userSelectionInteger = 0;
                        Double depositAmount = 0.0d;

                        System.out.println("All accounts have IDs...");
                        System.out.println("Enter the id of the account to deposit funds: ");
                        userSelectionInteger = Integer.parseInt(app.getConsole().readLine());

                        /**
                         * Find the account
                         */
                        accountService.authenticateByAccountId(userSelectionInteger);

                        System.out.println("How much would you like to deposit: ");

                        /**
                         * To handle an invalid deposit amount, a try/catch block is used
                         */
                        try {
                            depositAmount = Double.parseDouble(app.getConsole().readLine());
                            while(depositAmount < 0.00d) {
                                System.out.println("You cannot deposit negative funds! Try again...");
                                System.out.println("How much would you like to deposit:");
                                depositAmount = Double.parseDouble(app.getConsole().readLine());
                            }
                        } catch (Exception e) { // TODO custom exception
                            System.out.println("You did not enter an amount.");
                        }

                        accountService.depositFunds(app.getCurrentAccount(), depositAmount);

                        System.out.println(app.getCurrentAccount());

                        System.out.println("Transaction complete...navigating to Dashboard...");

                        if (app.isSessionValid()) {
                            app.getRouter().navigate("/dashboard");
                        }
                        break;
                    case "2": // withdraw funds case

                        Integer userSelectionIntegerCase2 = 0;
                        Double withdrawalAmount = 0.0d;

                        System.out.println("All accounts have IDs...");
                        System.out.println("Enter the id of the account to withdraw funds: ");
                        userSelectionIntegerCase2 = Integer.parseInt(app.getConsole().readLine());

                        accountService.authenticateByAccountId(userSelectionIntegerCase2);

                        System.out.println("How much would you like to withdraw: ");

                        try {
                            withdrawalAmount = Double.parseDouble(app.getConsole().readLine());
                            while(withdrawalAmount < 0.00d || withdrawalAmount > app.getCurrentAccount().getBalance()) {
                                System.out.println("You cannot withdraw that! Try again...");
                                System.out.println("How much would you like to withdraw:");
                                withdrawalAmount = Double.parseDouble(app.getConsole().readLine());
                            }

                        } catch (Exception e) { // TODO custom exception
                            System.out.println("You did not enter an amount.");
                        }
//                        try {
//                            withdrawalAmount = Double.parseDouble(app.getConsole().readLine());
//                        } catch (Exception e) { // TODO custom exception
//                            System.out.println("You did not enter an amount.");
//                        }

                        accountService.withdrawFunds(app.getCurrentAccount(), withdrawalAmount);

                        System.out.println(app.getCurrentAccount());

                        System.out.println("Transaction complete...navigating to Dashboard...");
                        // wrap more logic in try so that it doesn't proceed on.

                        if (app.isSessionValid()) {
                            app.getRouter().navigate("/dashboard");
                        }
                        break;
                    case "3": // delete accounts case
                        Integer userSelectionIntegerCase4 = 0;
                        String confirmOrDeny = "N";

                        System.out.println("All accounts have IDs...");
                        System.out.println("Enter the id of the account from which you want to delete: ");
                        userSelectionIntegerCase4 = Integer.parseInt(app.getConsole().readLine());

                        accountService.authenticateByAccountId(userSelectionIntegerCase4);

                        System.out.println(app.getCurrentUser().getFirstName() + ", Are you sure you want to delete this account?");
                        System.out.println("Enter y for yes, n for no: ");

                        try {
                            confirmOrDeny = app.getConsole().readLine();
                        } catch (Exception e) { // TODO custom exception
                            System.out.println("You did not enter yes or no.");
                        }

                        if(confirmOrDeny.equalsIgnoreCase("y")) {
                            accountService.deleteAccount(app.getCurrentAccount());
                            System.out.println("Account #"+ app.getCurrentAccount().getId() + " Deleted. Navigating to Dashboard");
                        } else if(confirmOrDeny.equalsIgnoreCase("n")) {
                            System.out.println("Account is still open. Navigating to "
                                    + app.getCurrentUser().getFirstName() + "'s Dashboard...");
                        } else {
                            System.out.println("I'll take that as a no. Returning to "
                                    + app.getCurrentUser().getFirstName() + "'s Dashboard...");
                        }

                        if (app.isSessionValid()) {
                            app.getRouter().navigate("/dashboard");
                        }
                        break;
                    case "4":
                        System.out.println(app.getCurrentUser().getUsername() + " going back to Dashboard......");
                        app.getRouter().navigate("/dashboard");
                        break;
                    default:
                        System.out.println("Invalid Selection!");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } // end while
    } // end render
} // end class