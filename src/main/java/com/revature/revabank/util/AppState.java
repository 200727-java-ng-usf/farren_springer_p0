package com.revature.revabank.util;

import com.revature.revabank.models.Account;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.repos.AccountRepository;
import com.revature.revabank.repos.UserRepository;
import com.revature.revabank.screens.*;
import com.revature.revabank.services.UserService;
import com.revature.revabank.services.AccountService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private BufferedReader console;
    private AppUser currentUser;
    private ScreenRouter router;
    private boolean appRunning;
    private Account currentAccount;

    public AppState() {
        System.out.println("[LOG] - Initializing application...");

        appRunning = true;
        console = new BufferedReader(new InputStreamReader(System.in));

        final UserRepository userRepo = new UserRepository();
        final AccountRepository accountRepo = new AccountRepository();
        final UserService userService = new UserService(userRepo);
        final AccountService accountService = new AccountService(accountRepo);

        router = new ScreenRouter();
        router.addScreen(new HomeScreen())
                .addScreen(new RegisterScreen(userService))
                .addScreen(new LoginScreen(userService))
                .addScreen(new DashboardScreen())
                .addScreen(new OpenANewAccountScreen(accountService))
                .addScreen(new AccessAccountsScreen(accountService))
                .addScreen(new ProfileScreen(accountService));

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

    public Account getCurrentAccount() { return currentAccount; }

    public void setCurrentAccount(Account currentAccount) { this.currentAccount = currentAccount; }

    public void invalidateCurrentSession() {
        currentUser = null;
    }

    public boolean isSessionValid() {
        return (this.currentUser != null);
    }
}
