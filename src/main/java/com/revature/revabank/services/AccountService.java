package com.revature.revabank.services;

import com.revature.revabank.exceptions.AccountAuthenticationException;
import com.revature.revabank.exceptions.AuthenticationException;
import com.revature.revabank.exceptions.InvalidRequestException;
import com.revature.revabank.models.Account;

import com.revature.revabank.repos.AccountRepository;

import java.io.IOException;
import java.util.*;
import java.text.NumberFormat;

import static com.revature.revabank.AppDriver.app;

public class AccountService {

    private static AccountRepository accountRepo;
    NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(); // for currencies

    public AccountService(AccountRepository repo) {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
        accountRepo = repo;
    }

    /**
     * To validate that the account exists, the authenticateByAccountId method
     * finds the account if the id is not 0 or empty and finds the account by
     * the account id.
     * Then, it sets the currentAccount to the account found using the account id.
     * to the authorized account found using the accountRepo.
     * @param id
     */
    public void authenticateByAccountId(Integer id) {

        if (id == 0 || id.equals("") ) {
            throw new InvalidRequestException("Invalid credential values provided!");
        }

        Account authAccount = accountRepo.findAccountByAccountId(id)
                .orElseThrow(AuthenticationException::new);

        app.setCurrentAccount(authAccount);

    }

    /**
     * To create a new row in the accounts table, the register method
     * checks if the account already exists, then saves the account and prints it.
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
         * To save the account, accountRepo calls the save method.
         */
        accountRepo.save(newAccount);
        System.out.println(newAccount);
        app.setCurrentAccount(newAccount); // redundant?

    }

    /**
     * Convenience methods
     * @return
     */

    /**
     * To make sure that the user has accounts, the showActiveAccounts method finds
     * the accounts associated with the appUser's id using the accountRepository's
     * findAccountByUserId method.
     * @param user_id
     */
    public void showActiveAccounts(Integer user_id) {

        // validate that the provided id is not a non-value
        if (user_id == null ) {
            throw new InvalidRequestException("Invalid credential values provided!");
        }
        System.out.println("Here are your account(s):");
        Account authAccount = accountRepo.findAccountByUserId(user_id)
                .orElseThrow(AccountAuthenticationException::new);

        app.setCurrentAccount(authAccount); // redundant?

    }

    /**
     * To set the balance field of the currentAccount to the new balance,
     * the withdrawFunds method validates that the amount is not negative or
     * more than the current balance.
     * Then, it calls the updateBalance method to update the database.
     * @param account
     * @param amount
     * @throws IOException
     */
    public void withdrawFunds(Account account, Double amount) throws IOException {
        /**
         * To check for overdraw, use an if statement
         */
        while (amount > account.getBalance() || amount < 0.0d) {
            System.out.println("Invalid amount! Try again...");
            System.out.println("How much would you like to withdraw: ");
            amount = Double.valueOf(app.getConsole().readLine());
        }
        account.setBalance(account.getBalance() - amount);
        System.out.println("Withdrawing " + defaultFormat.format(amount) + " from account #" + account.getId());
        // instead of saving, we want to edit the information in the db
        accountRepo.updateBalance(account.getBalance(), account.getId());
    }

    /**
     * To set the balance field of the currentAccount to the new balance,
     * the depositFunds method validates that the amount is not negative.
     * Then, it calls the updateBalance method to update the database.
     * @param account
     * @param amount
     * @throws IOException
     */
    public void depositFunds(Account account, Double amount) throws IOException {
        while (amount < 0.0d) {
            System.out.println("You cannot deposit negative funds! Try again...");
            System.out.println("How much would you like to deposit: ");
            amount = Double.valueOf(app.getConsole().readLine());
        }
        account.setBalance(account.getBalance() + amount);
        System.out.println("Depositing " + defaultFormat.format(amount) + " into account #" + account.getId());
        accountRepo.updateBalance(account.getBalance(), account.getId());
    }

    public void deleteAccount(Account account) {
        accountRepo.delete(account.getId());
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
