package com.revature.revabank.util;

import com.revature.revabank.models.AppUser;
import com.revature.revabank.repos.UserRepository;
import com.revature.revabank.screens.DashboardScreen;
import com.revature.revabank.screens.HomeScreen;
import com.revature.revabank.screens.LoginScreen;
import com.revature.revabank.screens.RegisterScreen;
import com.revature.revabank.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private BufferedReader console;
    private AppUser currentUser;
    private ScreenRouter router;
    private boolean appRunning;

    public AppState() {
        System.out.println("[LOG] - Initializing application...");

        appRunning = true;
        console = new BufferedReader(new InputStreamReader(System.in));

        final UserRepository userRepo = new UserRepository();
        final UserService userService = new UserService(userRepo);

        router = new ScreenRouter();
        router.addScreen(new HomeScreen())
                .addScreen(new RegisterScreen(userService))
                .addScreen(new LoginScreen(userService))
                .addScreen(new DashboardScreen());

        System.out.println("[LOG] - Application initialization complete.");

    }

    public BufferedReader getConsole() {
        return console;
    }

    public AppUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(AppUser currentUser) {
        this.currentUser = currentUser;
    }

    public ScreenRouter getRouter() {
        return router;
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }

    public void invalidateCurrentSession() {
        currentUser = null;
    }

    public boolean isSessionValid() {
        return (this.currentUser != null);
    }
}
