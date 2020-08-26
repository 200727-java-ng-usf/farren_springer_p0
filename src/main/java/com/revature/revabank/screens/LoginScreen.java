package com.revature.revabank.screens;

import com.revature.revabank.exceptions.AuthenticationException;
import com.revature.revabank.exceptions.InvalidRequestException;
import com.revature.revabank.services.UserService;

import static com.revature.revabank.AppDriver.app;

public class LoginScreen extends Screen {

    // UserService is a dependency to the LoginScreen
    private UserService userService;

    // Inject the dependency through the constructor (constructor injection)
    public LoginScreen(UserService userService) {
        super("LoginScreen", "/login");
//        System.out.println("[LOG] - Instantiating " + this.getClass().getName());

        // loosely coupled, because this class is not responsible for instantiation of a UserService
        this.userService = userService;

//        userService = new UserService();

    }

    /**
     * Renders the login screen menu to the console.
     */
    @Override
    public void render() {
        String username, password;

        try {
            System.out.println("Please provide your login credentials");
            System.out.print("Username: ");
            username = app.getConsole().readLine();
            System.out.print("Password: ");
            password = app.getConsole().readLine();

            userService.authenticate(username, password);

            if (app.isSessionValid()) {
                app.getRouter().navigate("/dashboard");
            }

        } catch (InvalidRequestException | AuthenticationException e) {
            System.err.println("Invalid login credentials provided!");
            app.getRouter().navigate("/home");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[ERROR] - An unexpected exception occurred: " + e.getMessage());
            System.out.println("[LOG] - Shutting down application");
            app.setAppRunning(false);
        }

    }
}
