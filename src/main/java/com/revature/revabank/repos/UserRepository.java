package com.revature.revabank.repos;

import com.revature.revabank.db.UserDB;
import com.revature.revabank.models.AppUser;

public class UserRepository {

    private UserDB userDataset = UserDB.userDataset;

    public UserRepository() {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
    }

    public AppUser findUserByCredentials(String username, String password) {
        return userDataset.findUserByCredentials(username, password);
    }

    public AppUser findUserByUsername(String username) {
        return userDataset.findUserByUsername(username);
    }

    public AppUser save(AppUser newUser) {
        return userDataset.addUser(newUser);
    }

}
