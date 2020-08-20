package com.revature.revabank.screens;

import com.revature.revabank.models.AppUser;
import com.revature.revabank.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DashboardScreen extends Screen{

    // Inject the dependency through the constructor (constructor injection)
    public DashboardScreen(UserService userService) {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());

    }

    /**
     * Renders the dashboard screen menu to the console.
     */
    @Override
    public void render() {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("This is the Dashboard Screen.");


            AppUser somethingUser;
            System.out.println("Print something here.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
