package com.revature.revabank.services;

import com.revature.revabank.exceptions.InvalidRequestException;
import com.revature.revabank.models.Account;
import com.revature.revabank.models.AccountType;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.models.Role;
import com.revature.revabank.repos.AccountRepository;
import com.revature.revabank.repos.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.revature.revabank.AppDriver.app;

public class AccountService {

    private static AccountRepository accountRepo;

    public AccountService(AccountRepository repo) {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
        accountRepo = repo;
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

    public static void register(Account newAccount) {



//        newAccount.setAccountType(AccountType.CHECKING);
        accountRepo.save(newAccount);
        System.out.println(newAccount);
//        app.setCurrentUser(newUser);

    }

}
