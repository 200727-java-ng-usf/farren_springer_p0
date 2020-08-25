package com.revature.revabank.screens;

import com.revature.revabank.exceptions.InvalidRequestException;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.services.UserService;

import static com.revature.revabank.AppDriver.app;

public class RegisterScreen extends Screen {

    private UserService userService;

    public RegisterScreen(UserService userService) {
        super("RegisterScreen", "/register");
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
        this.userService = userService;
    }

    @Override
    public void render() {

        String firstName, lastName, email, username, password;

        try {

            System.out.println("Sign up for a new account!");
            System.out.print("First name: ");
            firstName = app.getConsole().readLine();
            System.out.print("Last name: ");
            lastName = app.getConsole().readLine();
            System.out.print("Email: ");
            email = app.getConsole().readLine();
            System.out.print("Username: ");
            username = app.getConsole().readLine();
            System.out.print("Password: ");
            password = app.getConsole().readLine();

            AppUser newUser = new AppUser(firstName, lastName, username, password, email);
            userService.register(newUser);

            if (app.isSessionValid()) {
                app.getRouter().navigate("/dashboard");
            }

        } catch (InvalidRequestException e) {
            System.err.println("Registration unsuccessful, invalid values provided.");
        } catch (Exception e) {
            System.err.println("[ERROR] - An unexpected exception occurred: " + e.getMessage());
            System.out.println("[LOG] - Shutting down application");
            app.setAppRunning(false);
        }
    } // end render
} // end class
