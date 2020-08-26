package com.revature.revabank.screens;

import com.revature.revabank.services.UserService;

import java.io.IOException;

import static com.revature.revabank.AppDriver.app;

public class ProfileScreen extends Screen {

    private UserService userService;

    public ProfileScreen(UserService userService) {
        super("ProfileScreen", "/profile");
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());

        // loosely coupled, because this class is not responsible for instantiation of a UserService
        this.userService = userService;
    }

    @Override
    public void render() {
        String userSelection;
        System.out.println("Rendering " + app.getCurrentUser().getFirstName() + "'s Profile...");

        while (app.isSessionValid()) {

            System.out.println("\n+---------------------------------+\n");
            System.out.println("Here is your user information... ");
            System.out.println("First Name: " + app.getCurrentUser().getFirstName());
            System.out.println("Last Name: " + app.getCurrentUser().getLastName());
            System.out.println("Username: " + app.getCurrentUser().getUsername());
            System.out.println("Email: " + app.getCurrentUser().getEmail());
            System.out.println("User ID: " + app.getCurrentUser().getId());
            System.out.println("1) Edit User Information");
            System.out.println("2) Delete User");
            System.out.println("3) Go Back");

            try {
                System.out.print("Selection: ");
                userSelection = app.getConsole().readLine();

                switch (userSelection) {
                    case "1":
                        app.getRouter().navigate("/editUserInfo");
                        break;
                    case "2":
                        String confirmOrDeny = "N";

                        System.out.println(app.getCurrentUser().getFirstName() + ", Are you sure you want to delete " + app.getCurrentUser().getFirstName() + "?");
                        System.out.println("Enter y for yes, n for no: ");

                        try {
                            confirmOrDeny = app.getConsole().readLine();
                        } catch (Exception e) { // TODO custom exception
                            System.out.println("You did not enter yes or no.");
                        }

                        if(confirmOrDeny.equalsIgnoreCase("y")) {
                            userService.deleteUser(app.getCurrentUser());
                            System.out.println("User #"+ app.getCurrentUser().getId() + " Deleted. Navigating to Home Screen...");
                            app.getRouter().navigate("/home");
                        } else if(confirmOrDeny.equalsIgnoreCase("n")) {
                            System.out.println("User not deleted. Navigating to "
                                    + app.getCurrentUser().getFirstName() + "'s Dashboard...");
                        } else {
                            System.out.println("I'll take that as a no. Returning to "
                                    + app.getCurrentUser().getFirstName() + "'s Dashboard...");
                        }

                        if (app.isSessionValid()) {
                            app.getRouter().navigate("/dashboard");
                        }

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
