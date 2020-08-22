package com.revature.revabank.models;

import java.util.List;
import java.util.Objects;

public class Account {

    // fields/attributes
    private Integer id;
    private Integer accountNumber;
    private List<AccountType> accountTypeList;
    private Double balance;
    private AppUser appUser;
    private AccountType accountType;
    private String holderName;

    // constructors
    public Account() {
        super();
    }

    public Account(Integer accountNumber, Double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.appUser = new AppUser();
        this.accountType = accountType.CHECKING;
    }

    public Account(Integer accountNumber, List<AccountType> accountTypeList, Double balance, AppUser appUser) {
        this(accountNumber, balance);
        this.accountTypeList = accountTypeList;
        this.appUser = new AppUser();
        this.accountType = accountType.CHECKING;
    }

    public Account(Integer id, Integer accountNumber, List<AccountType> accountTypeList, Double balance, AppUser appUser, AccountType accountType) {
        this(accountNumber, accountTypeList, balance, appUser);
        this.id = id;
        this.accountType = accountType.CHECKING;
    }

    // copy constructor (used for conveniently copying the values of one AppUser to create a new instance with those values)
    public Account(Account copy) {
        this(copy.id, copy.accountNumber, copy.accountTypeList, copy.balance, copy.appUser, copy.accountType);
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

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<AccountType> getAccountTypeList() {
        return accountTypeList;
    }

    public void setAccountTypeList(List<AccountType> accountTypeList) {
        this.accountTypeList = accountTypeList;
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
                Objects.equals(accountNumber, account.accountNumber) &&
                Objects.equals(accountTypeList, account.accountTypeList) &&
                Objects.equals(balance, account.balance) &&
                Objects.equals(appUser, account.appUser) &&
                accountType == account.accountType &&
                Objects.equals(holderName, account.holderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, accountTypeList, balance, appUser, accountType, holderName);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber=" + accountNumber +
                ", accountTypeList=" + accountTypeList +
                ", balance=" + balance +
                ", appUser=" + appUser +
                ", accountType=" + accountType +
                ", holderName='" + holderName + '\'' +
                '}';
    }
}
