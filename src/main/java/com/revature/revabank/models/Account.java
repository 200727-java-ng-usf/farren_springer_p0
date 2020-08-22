package com.revature.revabank.models;

import java.util.List;
import java.util.Objects;

import static com.revature.revabank.AppDriver.app;

public class Account {

    /**
     * Account fields
     */
    private Integer id;
    private Double balance;
    private AccountType accountType;
    private Integer user_id;

    /**
     * No-args constructor
     */
    public Account() {
        super();
    }


    public Account(Double balance, Integer user_id) {
        this.balance = balance;
        this.user_id = app.getCurrentUser().getId();
    }

    public Account( AccountType accountType, Double balance,Integer user_id) {
        this(balance, user_id);
        this.accountType = accountType;
    }

    public Account(Integer id, AccountType accountType, Double balance, Integer user_id) {
        this(accountType, balance,user_id);
        this.id = id;
    }

    // copy constructor (used for conveniently copying the values of one AppUser to create a new instance with those values)
    public Account(Account copy) {
        this(copy.id,  copy.accountType, copy.balance,copy.user_id);
    }


    /**
     * Getters and Setters
     */
    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Double getBalance() { return balance; }

    public void setBalance(Double balance) { this.balance = balance; }

    public AccountType getAccountType() { return accountType; }

    public void setAccountType(AccountType accountType) { this.accountType = accountType; }

    public Integer getUser_id() { return user_id; }

    public void setUser_id(Integer user_id) { this.user_id = user_id; }

    /**
     * overridden Object methods
      */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(balance, account.balance) &&
                accountType == account.accountType &&
                Objects.equals(user_id, account.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, accountType, user_id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", accountType=" + accountType +
                ", user_id=" + user_id +
                '}';
    }

} // end class
