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

public class DepositWithdrawalOrViewScreen extends Screen {

    /**
     * AccountService is a dependency to the OpenANewAccountScreen
     */
    private AccountService accountService;
    private static AccountRepository accountRepo;

    // Inject the dependency through the constructor (constructor injection)
    public DepositWithdrawalOrViewScreen(AccountService accountService) {
        super("DespotWithdrawalOrView", "/depositWithdrawalOrView");
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
                        Integer userSelectionInteger = 0;
                        Double depositAmount = 0.0d;

                        System.out.println("Deposit Funds under construction...");

                        System.out.println("Here are your accounts.");
                        accountService.authenticateAccount(app.getCurrentUser().getId());

                        System.out.println("Each account has an id.");
                        System.out.println("Enter the id of the account from which you want to deposit funds: ");
                        userSelectionInteger = Integer.parseInt(app.getConsole().readLine());

                        accountService.authenticateByAccountId(userSelectionInteger);

                        System.out.println("How much would you like to deposit: ");
                        depositAmount = Double.parseDouble(app.getConsole().readLine());

                        accountService.depositFunds(app.getCurrentAccount(), depositAmount);

                        System.out.println(app.getCurrentAccount());

                        System.out.println("Transaction complete...navigating to Dashboard...");

                        if (app.isSessionValid()) {
                            app.getRouter().navigate("/dashboard");
                        }
                        break;
                    case "2":

                        Integer userSelectionIntegerCase2 = 0;
                        Double withdrawalAmount = 0.0d;

                        System.out.println("Withdraw Funds under construction...");

                        System.out.println("Here are your accounts.");
                        accountService.authenticateAccount(app.getCurrentUser().getId());

                        System.out.println("Each account has an id.");
                        System.out.println("Enter the id of the account from which you want to withdraw funds: ");
                        userSelectionIntegerCase2 = Integer.parseInt(app.getConsole().readLine());

                        accountService.authenticateByAccountId(userSelectionIntegerCase2);

                        System.out.println("How much would you like to withdraw: ");
                        withdrawalAmount = Double.parseDouble(app.getConsole().readLine());

                        accountService.withdrawFunds(app.getCurrentAccount(), withdrawalAmount);

                        System.out.println(app.getCurrentAccount());

                        System.out.println("Transaction complete...navigating to Dashboard...");

                        if (app.isSessionValid()) {
                            app.getRouter().navigate("/dashboard");
                        }
                        break;
                    case "3":
                        System.out.println("Here are your accounts: ");

                        accountService.authenticateAccount(app.getCurrentUser().getId());

                        System.out.println("Going back to " + app.getCurrentUser().getFirstName() + "'s Dashboard...");

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