package com.revature.revabank.services;

import com.revature.revabank.exceptions.AuthenticationException;
import com.revature.revabank.exceptions.InvalidRequestException;
import com.revature.revabank.models.Account;
import com.revature.revabank.models.AccountType;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.models.Role;
import com.revature.revabank.repos.AccountRepository;
import com.revature.revabank.repos.UserRepository;

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
//    public void authenticate(Integer id) {
//
//        // validate that the provided username and password are not non-values
//        if (id == 0 || id.equals("") ) {
//            throw new InvalidRequestException("Invalid credential values provided!");
//        }
//
//        Account authAccount = accountRepo.findAccountByAccountId(id)
//                .orElseThrow(AuthenticationException::new);
//
//        app.setCurrentAccount(authAccount);
//
//    }

    public void register(Account newAccount) {

        if (!isAccountValid(newAccount)) {
            throw new InvalidRequestException("Invalid account field values provided during registration!");
        }

//        System.out.println("This is the account id: " + accountRepo.findAccountByAccountId(newAccount.getId()));
//        Optional<Account> existingAccount = accountRepo.findAccountByAccountId(newAccount.getId());
//        if (existingAccount.isPresent()) {
//            // TODO implement a custom ResourcePersistenceException
//            throw new RuntimeException("Provided username is already in use!");
//        }

//        newAccount.setAccountType(AccountType.CHECKING);
        accountRepo.save(newAccount);
        System.out.println(newAccount);
        app.setCurrentAccount(newAccount);

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
