package com.revature.revabank.screens;

import com.revature.revabank.repos.UserRepository;
import com.revature.revabank.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.revature.revabank.AppDriver.app;

public class HomeScreen extends Screen {

    public HomeScreen() {
        super("HomeScreen", "/home");
        System.out.println("[LOG] - Instantiating " + super.getName());
    }

    @Override
    public void render() {

        System.out.println("Welcome to Revabooks!\n");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit Application");

        try {
            System.out.print("> ");
            String userSelection = app.getConsole().readLine();

            switch (userSelection) {
                case "1":
//                    new LoginScreen(new UserService(new UserRepository())).render();
                    app.getRouter().navigate("/login");
                    break;
                case "2":
                    app.getRouter().navigate("/register");
                    break;
                case "3":
                    app.setAppRunning(false);
                    break;
                default:
                    System.out.println("[LOG] - Invalid selection!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
