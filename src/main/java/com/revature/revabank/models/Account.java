package com.revature.revabank.models;

import java.util.List;
import java.util.Objects;

public class Account {

    // fields/attributes
    private Integer id;
    Integer accountNumber;
    List<AccountType> accountTypeList;
    Integer balance;
    private Role role;

    // constructors
    public Account() {
        super();
    }

    public Account(Integer accountNumber,Integer balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.role = Role.LOCKED;
    }

    public Account(Integer accountNumber, List<AccountType> accountTypeList, Integer balance, Role role) {
        this(accountNumber, balance);
        this.accountTypeList = accountTypeList;
        this.role = role;
    }

    public Account(Integer id, Integer accountNumber, List<AccountType> accountTypeList, Integer balance, Role role) {
        this(accountNumber, accountTypeList, balance, role);
        this.id = id;
    }

    // copy constructor (used for conveniently copying the values of one AppUser to create a new instance with those values)
    public Account(Account copy) {
        this(copy.id, copy.accountNumber, copy.accountTypeList, copy.balance, copy.role);
    }

    // getters and setters


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

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // overridden Object methods


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(accountNumber, account.accountNumber) &&
                Objects.equals(accountTypeList, account.accountTypeList) &&
                Objects.equals(balance, account.balance) &&
                role == account.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, accountTypeList, balance, role);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber=" + accountNumber +
                ", accountTypeList=" + accountTypeList +
                ", balance=" + balance +
                ", role=" + role +
                '}';
    }
}
