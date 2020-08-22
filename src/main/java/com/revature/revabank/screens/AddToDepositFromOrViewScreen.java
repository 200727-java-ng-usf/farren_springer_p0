package com.revature.revabank.screens;

import com.revature.revabank.exceptions.AuthenticationException;
import com.revature.revabank.exceptions.InvalidRequestException;
import com.revature.revabank.models.Account;
import com.revature.revabank.models.AccountType;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.repos.AccountRepository;
import com.revature.revabank.services.AccountService;
import com.revature.revabank.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.revature.revabank.AppDriver.app;

public class AddToDepositFromOrViewScreen extends Screen {

    /**
     * AccountService is a dependency to the OpenANewAccountScreen
     */
    private AccountService accountService;
    private static AccountRepository accountRepo;

    // Inject the dependency through the constructor (constructor injection)
    public AddToDepositFromOrViewScreen(AccountService accountService) {
        super("SeeAccounts", "/seeAccounts");
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
        System.out.println("Rendering " + app.getCurrentUser().getFirstName() + "'s Accounts...");

        while (app.isSessionValid()) {

            System.out.println("\n\n+---------------------------------+\n");
            System.out.println("1) Deposit Funds");
            System.out.println("2) Withdraw Funds");
            System.out.println("3) View Balances");
            System.out.println("4) Go Back");

            try {
                System.out.print("Selection: ");
                userSelection = app.getConsole().readLine();

                switch (userSelection) {
                    case "1":
//                        Double depositAmount;
//                        System.out.println("Deposit Funds: ");
//                        System.out.println("How much would you like to deposit: ");
//                        depositAmount = Double.valueOf(app.getConsole().readLine());
                        System.out.println("Deposit Funds under construction...");
                        System.out.println(app.getCurrentAccount().toString());

                        // show the user's their accounts using the same as view accounts
                        // ask them to input the accountId they would like to deposit to using a switch
                        // statement
                        // the switch statement should set the current account to that account
                        // and take them to a screen that allows them to deposit
                        if (app.isSessionValid()) {
                            app.getRouter().navigate("/dashboard");
                        }
                        break;
                    case "2":
                        System.out.println("Withdraw Funds under construction");
                        if (app.isSessionValid()) {
                            app.getRouter().navigate("/dashboard");
                        }
                        // show the user's their accounts using the same as view accounts
                        // ask them to input the accountId they would like to withdraw from using
                        // a switch statement
                        // the switch statement should set the current account to that account
                        // and take them to a screen that allows them to withdraw
                        break;
                    case "3":
                        System.out.println("View Balances under construction...");
                        System.out.println("Here are your accounts: ");

                        // SELECT * from project0.accounts WHERE id = ?
                        // make ? the app.getcurrentUser().getId()

                        // authenticate for each account found where user_id matches app_user id column?
//                        accountService.authenticate(app.getCurrentUser().getId());
                        System.out.println(app.getCurrentUser().toString());
                        System.out.println(accountRepo.findAllAccountsWithAppUserId(app.getCurrentUser().getId()).toString());
                        // for the number of accounts that have the user's id as their
                        // user_id...
                        // print the toString for those accounts.
                        // parse through the account list...
                        // find the first instance of user_id matching the id of currentUser

                        // set the current account to the account with user_id
                        // that matches id in app_users table
                        // show the accountId's of all accounts registered under the app_user

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