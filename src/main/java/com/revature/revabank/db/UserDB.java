package com.revature.revabank.db;

import com.revature.revabank.models.AppUser;
import com.revature.revabank.models.Role;

import java.util.HashMap;

public class UserDB extends HashMap<Integer, AppUser> {

    public static UserDB userDataset = new UserDB();
    public static int key = 1;

    static {
        userDataset.addUser(new AppUser("Adam", "Inn", "admin", "p4ssw0rd", Role.ADMIN));
        userDataset.addUser(new AppUser("Wezley", "Singleton", "wsingleton", "java", Role.MANAGER));
        userDataset.addUser(new AppUser("Blake", "Kruppa", "bkruppa", "javascript", Role.PREMIUM_MEMBER));
        userDataset.addUser(new AppUser("Dylan", "McBee", "dmcbee", "password", Role.BASIC_MEMBER));
        userDataset.addUser(new AppUser("Nickolas", "Jurczak", "njurczak", "drowssap", Role.BASIC_MEMBER));
    }

    public AppUser addUser(AppUser newUser) {
        AppUser nUser = new AppUser(newUser);
        nUser.setId(key);
        userDataset.put(key++, nUser);
        return nUser;
    }

    public AppUser findUserByCredentials(String username, String password) {

        for (AppUser user : userDataset.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public AppUser findUserByUsername(String username) {

        // using the Stream API (all Collection implementations have a .stream() method)
//        return userDataset.values()
//                          .stream()
//                          .filter(user -> user.getUsername().equals(username))
//                          .findFirst()
//                          .orElse(null);

        for (AppUser user : userDataset.values()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

}
