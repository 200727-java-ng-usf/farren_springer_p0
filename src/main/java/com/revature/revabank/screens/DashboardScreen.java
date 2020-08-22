package com.revature.revabank.screens;

import static com.revature.revabank.AppDriver.*;

public class DashboardScreen extends Screen {

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
            System.out.println("1) See Accounts");
            System.out.println("2) Add, Withdraw, or See Funds");
            System.out.println("3) Delete an Account");
            System.out.println("4) Open a New Account");
            System.out.println("5) Edit User Information");
            System.out.println("6) Sign Out");

            try {

                System.out.print("Selection: ");
                userSelection = app.getConsole().readLine();

                switch (userSelection) {
                    case "1":
                        System.err.println("See Accounts screen under construction...");
                        break;
                    case "2":
                        System.err.println("Add, Withdraw, or See Funds Screen under construction...");
                        System.err.println("Only feature to add funds for now...navigating to DepositFundsScreen...");
                        app.getRouter().navigate("/depositFunds");
                        break;
                    case "3":
                        System.err.println("Delete An Account Screen under construction...");
                        break;
                    case "4":
                        System.out.println("Navigating to New Account Screen...");
                        app.getRouter().navigate("/openANewAccount");
                        break;
                    case "5":
                        System.out.println("Edit User Information Screen under construction...");
//                        app.getRouter().navigate("/profile");
                        break;
                    case "6":
                        System.out.println(app.getCurrentUser().getUsername() + " signing out...");
                        app.invalidateCurrentSession();
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
