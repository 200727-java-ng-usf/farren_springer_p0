package com.revature.revabank.screens;

import static com.revature.revabank.AppDriver.*;

/**
 * The dashboard screen render method asks the user what screen
 * they want to go to and navigates to that screen.
 */
public class DashboardScreen extends Screen {

    /**
     * Constructor
     */
    public DashboardScreen() {
        super("DashboardScreen", "/dashboard");
//        System.out.println("[LOG] - Instantiating " + super.getName());
    }

    @Override
    public void render() {

        String userSelection;
        System.out.println("Rendering " + app.getCurrentUser().getFirstName() + "'s Dashboard...");

        while (app.isSessionValid()) {

            System.out.println("\n+---------------------------------+\n");
            System.out.println("1) Access Accounts");
            System.out.println("2) Open a New Account");
            System.out.println("3) See Profile");
            System.out.println("4) Sign Out");

            try {

                System.out.print("Selection: ");
                userSelection = app.getConsole().readLine();

                switch (userSelection) {
                    case "1":
                        System.out.println("Navigating to AccessAccountsScreen...");
                        app.getRouter().navigate("/accessAccounts");
                        break;
                    case "2":
                        System.out.println("Navigating to New Account Screen...");
                        app.getRouter().navigate("/openANewAccount");
                        break;
                    case "3":
                        System.out.println("Navigating to Profile Screen...");
                        app.getRouter().navigate("/profile");
                        break;
                    case "4":
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
