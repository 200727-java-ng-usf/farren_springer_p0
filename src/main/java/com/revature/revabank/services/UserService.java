package com.revature.revabank.services;

import com.revature.revabank.exceptions.AuthenticationException;
import com.revature.revabank.exceptions.InvalidRequestException;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.models.Role;
import com.revature.revabank.repos.UserRepository;

import java.util.HashSet;
import java.util.Set;

public class UserService {

    private UserRepository userRepo;

    public UserService(UserRepository repo) {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
        userRepo = repo;
//        userRepo = new UserRepository(); // tight coupling! ~hard~ impossible to unit test
    }

    public AppUser authenticate(String username, String password) {

        // validate that the provided username and password are not non-values
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid credential values provided!");
        }

        AppUser authenticatedUser = userRepo.findUserByCredentials(username, password);

        if (authenticatedUser == null) {
            throw new AuthenticationException("No user found with the provided credentials");
        }

        return authenticatedUser;

    }

    public AppUser register(AppUser newUser) {

        if (!isUserValid(newUser)) {
            // TODO implement a custom InvalidRequestException
            throw new RuntimeException("Invalid user field values provided during registration!");
        }

        if (userRepo.findUserByUsername(newUser.getUsername()) != null) {
            // TODO implement a custom ResourcePersistenceException
            throw new RuntimeException("Provided username is already in use!");
        }

        newUser.setRole(Role.BASIC_MEMBER);
        return userRepo.save(newUser);

    }

    public Set<AppUser> getAllUsers() {
        return new HashSet<>();
    }

    public Set<AppUser> getUsersByRole() {
        return new HashSet<>();
    }

    public AppUser getUserById(int id) {
        return null;
    }

    public AppUser getUserByUsername(String username) {
        return null;
    }

    public boolean deleteUserById(int id) {
        return false;
    }

    public boolean update(AppUser updatedUser) {
        return false;
    }

    /**
     * Validates that the given user and its fields are valid (not null or empty strings). Does
     * not perform validation on id or role fields.
     *
     * @param user
     * @return true or false depending on if the user was valid or not
     */
    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        if (user.getPassword() == null || user.getPassword().trim().equals("")) return false;
        return true;
    }


}
