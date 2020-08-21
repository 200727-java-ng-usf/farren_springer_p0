package com.revature.revabank.services;

import com.revature.revabank.models.Account;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.repos.UserRepository;

import java.util.HashSet;
import java.util.Set;

public class AccountService {

    private UserRepository userRepo;

    public AccountService(UserRepository repo) {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
        userRepo = repo;
    }

    /**
     * Convenience methods
     * @return
     */
    public Set<Account> getAllAccounts() {
        return new HashSet<>();
    }

    public Set<Account> getAccountByAccountNumber() {
        return new HashSet<>();
    }

    public boolean deleteAccountById(int id) {
        return false;
    }

    public boolean update(Account updatedAccount) {
        return false;
    }

    // TODO method to withdraw funds
    public void withdrawFunds(Account account) {

    }

    // TODO method to deposit funds
    public void depositFunds(Account account) {

    }

    // TODO use this method as an option for user
    public void displayBalance(Account account) {
        System.out.println(account.getBalance());
    }


}
