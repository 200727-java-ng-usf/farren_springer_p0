package com.revature.revabank.screens;

import com.revature.revabank.models.Account;
import com.revature.revabank.models.AccountType;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.services.AccountService;

import java.io.IOException;
import java.util.List;

import static com.revature.revabank.AppDriver.app;

public class DepositFundsScreen extends Screen {

    /**
     * AccountService is a dependency to the OpenANewAccountScreen
     */
    private AccountService accountService;

    /**
     * Inject the dependency through the constructor (constructor injection)
     */
    //
    public DepositFundsScreen(AccountService accountService) {
        super("DepositFunds", "/depositFunds");
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());

        // loosely coupled, because this class is not responsible for instantiation of a UserService
        this.accountService = accountService;

//        userService = new UserService();

    }

    /**
     * Renders the open a deposit funds screen menu to the console.
     */
    @Override
    public void render() {

        String userSelection;
        System.out.println("Rendering " + app.getCurrentUser().getFirstName() + "'s Account to deposit funds...");
        System.out.println("Deposit Funds Feature under construction");

//        while (app.isSessionValid()) {
//
//            System.out.println("\n\n+---------------------------------+\n");
//            System.out.println("1) Checking Account");
//            System.out.println("2) Savings Account");
//            System.out.println("3) Go Back");
//
//            /**
//             * Declare Account fields
//             */
//            Integer id;
//            Integer accountNumber;
//            List<AccountType> accountTypeList;
//            Double balance;
//            AppUser appUser;
//            AccountType accountType;
//            String holderName;
//
//            try {
//                System.out.print("Selection: ");
//                userSelection = app.getConsole().readLine();
//
//                switch (userSelection) {
//                    case "1":
//                        System.out.println("Creating a Checking Account...");
//                        Account newAccount = new Account(1, 100, 0.00d, app.getCurrentUser(), AccountType.CHECKING);
//                        AccountService.register(newAccount);
//                        System.out.println("Account created with: " + newAccount.getBalance());
//
//                        if (app.isSessionValid()) {
//                            app.getRouter().navigate("/dashboard");
//                        }
//                        break;
//                    case "2":
//                        System.out.println("Creating a Savings Account...");
//                        newAccount = new Account(1, 100, 0.00d, app.getCurrentUser(), AccountType.SAVINGS);
//                        AccountService.register(newAccount);
//                        System.out.println("Account created with: " + newAccount.getBalance());
//
//                        if (app.isSessionValid()) {
//                            app.getRouter().navigate("/dashboard");
//                        }
//                        break;
//                    case "3":
//                        System.out.println(app.getCurrentUser().getUsername() + " going back to Dashboard......");
//                        app.getRouter().navigate("/dashboard");
//                        break;
//                    default:
//                        System.out.println("Invalid Selection!");
//
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } // end while
    } // end render
}
