package com.revature.revabank.screens;

import com.revature.revabank.exceptions.AuthenticationException;
import com.revature.revabank.models.Account;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.repos.AccountRepository;

import static com.revature.revabank.AppDriver.*;

public class DashboardScreen extends Screen {

    private static AccountRepository accountRepo;
    /**
     * Constructor
     */
    public DashboardScreen() {
        super("DashboardScreen", "/dashboard");
        System.out.println("[LOG] - Instantiating " + super.getName());
    }

    @Override
    public void render() {

        String userSelection;
        System.out.println("Rendering " + app.getCurrentUser().getFirstName() + "'s Dashboard...");

        while (app.isSessionValid()) {

            System.out.println("\n\n+---------------------------------+\n");
            System.out.println("1) Add to, Withdraw from, or See Accounts");
            System.out.println("2) Open a New Account");
            System.out.println("3) Delete an Account");
            System.out.println("4) Edit User Information");
            System.out.println("5) Sign Out");

            try {

                System.out.print("Selection: ");
                userSelection = app.getConsole().readLine();

                switch (userSelection) {
                    case "1":
                        System.out.println("Navigating to SeeAccountsScreen...");
                        app.getRouter().navigate("/seeAccounts");
                        break;
                    case "2":
                        System.out.println("Navigating to New Account Screen...");
                        app.getRouter().navigate("/openANewAccount");
                        break;
                    case "3":
                        System.err.println("Delete An Account Screen under construction...");
                        break;
                    case "4":
                        System.out.println("Edit User Information Screen under construction...");
//                        app.getRouter().navigate("/profile");
                        break;
                    case "5":
                        System.out.println(app.getCurrentUser().getUsername() + " signing out...");
                        app.getRouter().navigate("/home");
                        break;
                    default:
                        System.out.println("Invalid Selection!");
                }

            } catch (Exception e) {
                System.err.println("[ERROR] - " + e.getMessage());
                System.out.println("[LOG] - Shutting down application");
                app.setAppRunning(false);
            }

        } // end while

    } // end render

} // end class
