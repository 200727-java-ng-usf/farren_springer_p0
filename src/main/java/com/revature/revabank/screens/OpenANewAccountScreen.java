package com.revature.revabank.screens;

import com.revature.revabank.models.Account;
import com.revature.revabank.models.AccountType;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.services.AccountService;

import java.io.IOException;
import java.util.List;

import static com.revature.revabank.AppDriver.app;

public class OpenANewAccountScreen extends Screen {

    /**
     * AccountService is a dependency to the OpenANewAccountScreen
      */
    private AccountService accountService;

    // Inject the dependency through the constructor (constructor injection)
    public OpenANewAccountScreen(AccountService accountService) {
        super("OpenANewAccount", "/openANewAccount");
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());

        // loosely coupled, because this class is not responsible for instantiation of a UserService
        this.accountService = accountService;
    }

    /**
     * Renders the open a new account screen menu to the console.
     */
    @Override
    public void render() {
        // TODO try to create an account object
        String userSelection;
        System.out.println("Rendering " + app.getCurrentUser().getFirstName() + "'s Open New Account Screen...");

        while (app.isSessionValid()) {

            System.out.println("\n+---------------------------------+\n");
            System.out.println("1) Checking Account");
            System.out.println("2) Savings Account");
            System.out.println("3) Go Back");

            try {
                System.out.print("Selection: ");
                userSelection = app.getConsole().readLine();

                switch (userSelection) {
                    case "1":

                        System.out.println("Creating a Checking Account...");

                        Account newCheckingAccount = new Account(AccountType.CHECKING, app.getCurrentUser().getId(), 0.00d);
                        accountService.register(newCheckingAccount);

                        System.out.println("Account created with: " + newCheckingAccount.getBalance());

                        if (app.isSessionValid()) {
                            app.getRouter().navigate("/dashboard");
                        }
                        break;
                    case "2":

                        System.out.println("Creating a Savings Account...");

                        Account newSavingsAccount = new Account(AccountType.SAVINGS, app.getCurrentUser().getId(), 0.00d);
                        accountService.register(newSavingsAccount);

                        System.out.println("Account created with: " + newSavingsAccount.getBalance());

                        if (app.isSessionValid()) {
                            app.getRouter().navigate("/dashboard");
                        }
                        break;
                    case "3":
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