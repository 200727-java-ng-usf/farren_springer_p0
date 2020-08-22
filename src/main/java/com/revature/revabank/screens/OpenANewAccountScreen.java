package com.revature.revabank.screens;

import com.revature.revabank.exceptions.AuthenticationException;
import com.revature.revabank.exceptions.InvalidRequestException;
import com.revature.revabank.models.Account;
import com.revature.revabank.models.AccountType;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.services.AccountService;
import com.revature.revabank.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.revature.revabank.AppDriver.app;

public class OpenANewAccountScreen extends Screen {

    // UserService is a dependency to the LoginScreen
    private AccountService accountService;

    // Inject the dependency through the constructor (constructor injection)
    public OpenANewAccountScreen(AccountService accountService) {
        super("OpenANewAccount", "/openANewAccount");
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());

        // loosely coupled, because this class is not responsible for instantiation of a UserService
        this.accountService = accountService;

//        userService = new UserService();

    }

    /**
     * Renders the open a new account screen menu to the console.
     */
    @Override
    public void render() {
        // TODO try to create an account object
        String userSelection;
        System.out.println("Rendering " + app.getCurrentUser().getFirstName() + "'s new Account...");

        while (app.isSessionValid()) {

            System.out.println("\n\n+---------------------------------+\n");
            System.out.println("1) Checking Account");
            System.out.println("2) Savings Account");
            System.out.println("3) Go Back");

            /**
             * Declare Account fields
             */
            Integer id;
            Integer accountNumber;
            List<AccountType> accountTypeList;
            Double balance;
            AppUser appUser;
            AccountType accountType;
            String holderName;

            try {
                System.out.print("Selection: ");
                userSelection = app.getConsole().readLine();

                switch (userSelection) {
                    case "1":
                        System.out.println("Creating a Checking Account...");
                        Account newAccount = new Account(1, 100, 0.00d, app.getCurrentUser(), AccountType.CHECKING);
                        AccountService.register(newAccount);
                        System.out.println("Account created with: " + newAccount.getBalance());

                        if (app.isSessionValid()) {
                            app.getRouter().navigate("/dashboard");
                        }
                        break;
                    case "2":
                        System.err.println("Creating a Savings Account under construction...");
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