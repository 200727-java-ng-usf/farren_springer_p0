package com.revature.revabank.screens;

import com.revature.revabank.services.AccountService;
import com.revature.revabank.services.UserService;

import java.io.IOException;

import static com.revature.revabank.AppDriver.app;

public class EditUserInfoScreen extends Screen {

    private UserService userService;

    public EditUserInfoScreen(UserService userService) {
        super("EditUserInfoScreen", "/editUserInfo");
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());

        // loosely coupled, because this class is not responsible for instantiation of a UserService
        this.userService = userService;

//        userService = new UserService();

    }

    @Override
    public void render() {
        String userSelection;
        System.out.println("Rendering " + app.getCurrentUser().getFirstName() + "'s Edit User Information Screen...");

        while (app.isSessionValid()) {

            System.out.println("\n+---------------------------------+\n");
            System.out.println("Select information to update:");
            System.out.println("1) Last Name: " + app.getCurrentUser().getLastName());
            System.out.println("2) Email: " + app.getCurrentUser().getEmail());
            System.out.println("3) Go Back");

            try {
                System.out.print("Selection: ");
                userSelection = app.getConsole().readLine();

                switch (userSelection) {
                    case "1":
                        // TODO call userRepo method to update last name
                        System.out.println("Enter new last name: ");
                        String newLastName = app.getConsole().readLine();
                        userService.updateLastName(newLastName, app.getCurrentUser());
                        System.out.println("Last name updated to: " + app.getCurrentUser().getLastName());
                        System.out.println("Returning to " + app.getCurrentUser().getFirstName() + "'s Dashboard...");
                        app.getRouter().navigate("/dashboard");
                        break;

                    case "2":
                        // TODO call updateEmail method
//                        System.out.println("Cannot edit email for now...");
                        System.out.println("Enter new email: ");
                        String newEmail = app.getConsole().readLine();
                        userService.updateEmail(newEmail, app.getCurrentUser());
                        System.out.println("Email updated to: " + app.getCurrentUser().getEmail());
                        System.out.println("Returning to " + app.getCurrentUser().getFirstName() + "'s Dashboard...");
                        app.getRouter().navigate("/dashboard");
                        break;
                    case "3":
                        System.out.println("Returning to " + app.getCurrentUser().getFirstName() + "'s Dashboard...");
                        app.getRouter().navigate("/dashboard");
                    default:
                        System.out.println("Invalid Selection!");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } // end while
    }

}
