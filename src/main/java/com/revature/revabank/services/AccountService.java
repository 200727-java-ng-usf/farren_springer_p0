package com.revature.revabank.services;

import com.revature.revabank.exceptions.AuthenticationException;
import com.revature.revabank.exceptions.InvalidRequestException;
import com.revature.revabank.models.Account;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.models.Role;
import com.revature.revabank.repos.AccountRepository;

import java.util.*;

import static com.revature.revabank.AppDriver.app;

public class AccountService {

    private static AccountRepository accountRepo;

    public AccountService(AccountRepository repo) {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
        accountRepo = repo;
    }

    /**
     * Validates that the account exists, finds the account, and sets the currentAccount to the
     * authorized account found using the accountRepo.
     * @param id
     */
    public void authenticateByAccountId(Integer id) {

        // validate that the provided Integer id is not null
        if (id == 0 || id.equals("") ) {
            throw new InvalidRequestException("Invalid credential values provided!");
        }

        Account authAccount = accountRepo.findAccountByAccountId(id)
                .orElseThrow(AuthenticationException::new);

        app.setCurrentAccount(authAccount);

    }

    /**
     * Creates a new row in the accounts table in the project0 db through the save
     * method, first checking if the account already exists.
     * @param newAccount
     */
    public void register(Account newAccount) {

        if (!isAccountValid(newAccount)) {
            throw new InvalidRequestException("Invalid account field values provided during registration!");
        }

        /**
         * Right now the method does not check if the account already exists...
         * Maybe make sure users do not create 1399429 accounts (set a limit).
         */
//        Optional<Account> existingAccount = accountRepo.findAccountByAccountId(newAccount.getId());
//        if (existingAccount.isPresent()) {
            // TODO implement a custom ResourcePersistenceException
//            throw new RuntimeException("Provided username is already in use!");
//        }
        /**
         * Saves the account, prints it, and sets the current account to the
         * new account (maybe don't need to set it as current since this is
         * done when they want to make a withdrawal or deposit
         */
        accountRepo.save(newAccount);
        System.out.println(newAccount);
        app.setCurrentAccount(newAccount); // may be redundant

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

    // method that returns all accounts with a user_id that matches the currentUser's id

    /**
     * This method makes sure that the user has accounts. It will print those accounts
     * to the console if they exist using the findAccountByUserId method.
     * @param user_id
     */
    public void authenticateAccount(Integer user_id) {

        // validate that the provided id is not a non-value
        if (user_id == null ) {
            throw new InvalidRequestException("Invalid credential values provided!");
        }

        /**
         * The findAccountByUserId prints ALL accounts that the user has made
         */
        Account authAccount = accountRepo.findAccountByUserId(user_id)
                .orElseThrow(AuthenticationException::new);

        app.setCurrentAccount(authAccount);

    }

    public void withdrawFunds(Account account, Double amount) {
        account.setBalance(account.getBalance() - amount);
        System.out.println(account.getBalance());
        // instead of saving, we want to edit the information in the db
        accountRepo.save(account);
    }

    public void depositFunds(Account account, Double amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println(account.getBalance());
        accountRepo.updateBalance(account, account.getBalance(), account.getId());
    }

    // TODO use this method as an option for user
    public void displayBalance(Account account) {
        System.out.println(account.getBalance());
    }

    /**
     * Validates that the given user and its fields are valid (not null or empty strings). Does
     * not perform validation on id or role fields.
     *
     * @param account
     * @return true or false depending on if the account was valid or not
     */
    public boolean isAccountValid(Account account) {
        if (account == null) return false;
        if (account.getBalance() == null ) return false;
        if (account.getAccountType() == null ) return false;
        if (account.getUser_id() == null ) return false;
        return true;
    }

}
