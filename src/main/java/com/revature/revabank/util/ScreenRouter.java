package com.revature.revabank.util;

import com.revature.revabank.exceptions.ScreenNotFoundException;
import com.revature.revabank.screens.Screen;

import java.util.HashSet;
import java.util.Set;

public class ScreenRouter {

    private Set<Screen> screens = new HashSet<>();

    public ScreenRouter addScreen(Screen screen) {
        System.out.println("[LOG] - Loading " + screen.getName() + " into the router");
        screens.add(screen);
        return this;
    }

    public void navigate(String route) {

//        for (Screen screen : screens) {
//            if (screen.getRoute().equals(route)) {
//                screen.render();
//                return;
//            }
//        }
//
//        throw new RuntimeException("No screen found.");

        screens.stream()
                .filter(screen -> screen.getRoute().equals(route))
                .findFirst()
                .orElseThrow(() -> new ScreenNotFoundException("No screen found with that route."))
                .render();
    }

}
