package com.revature.revabank.models;

import java.util.List;
import java.util.Objects;

public class Account {

    /**
     * Account fields
     */
    private Integer id;
    private Double balance;
    private AppUser appUser;
    private AccountType accountType;
    private String holderName;

    /**
     * No-args constructor
     */
    public Account() {
        super();
    }

    /**
     * Constructor
     * @param balance
     */
    public Account(Double balance) {
        this.balance = balance;
        this.appUser = new AppUser();
        this.accountType = accountType.CHECKING;
    }

    /**
     * Constructor
     * @param balance
     * @param appUser
     */
    public Account(Double balance, AppUser appUser) {
        this(balance);
        this.appUser = new AppUser();
        this.accountType = accountType.CHECKING;
    }

    /**
     * Constructor for all fields
     * @param id
     * @param balance
     * @param appUser
     * @param accountType
     */
    public Account(Integer id, Double balance, AppUser appUser, AccountType accountType) {
        this(balance, appUser);
        this.id = id;
        this.accountType = accountType.CHECKING;
    }

    // copy constructor (used for conveniently copying the values of one AppUser to create a new instance with those values)
    public Account(Account copy) {
        this(copy.id, copy.balance, copy.appUser, copy.accountType);
    }


    /**
     * Getters and Setters
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

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
                Objects.equals(appUser, account.appUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, appUser);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", appUser=" + appUser +
                '}';
    }

}
