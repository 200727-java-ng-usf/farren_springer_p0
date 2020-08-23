package com.revature.revabank.screens;

import com.revature.revabank.services.AccountService;
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

//        userService = new UserService();

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
            System.out.println("2) Go Back");

            try {
                System.out.print("Selection: ");
                userSelection = app.getConsole().readLine();

                switch (userSelection) {
                    case "1":
                        // TODO call userRepo methods to edit information
                        app.getRouter().navigate("/editUserInfo");
                        System.out.println("Cannot edit user information for now...Going back to "
                                + app.getCurrentUser().getFirstName() + "'s Dashboard");
                        app.getRouter().navigate("/dashboard");
                        break;

                    case "2":
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
    }
}
