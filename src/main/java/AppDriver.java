package com.revature.revabank;

import com.revature.revabank.util.AppState;

public class AppDriver {

    public static AppState app = new AppState();

    public static void main(String[] args) {

        while(app.isAppRunning()) {
            app.getRouter().navigate("/home");
        }

    }

}
