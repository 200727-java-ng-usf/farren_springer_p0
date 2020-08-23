package com.revature.revabank.exceptions;

public class AccountAuthenticationException extends RuntimeException {

    public AccountAuthenticationException() {
        super("Account authentication failed!");
    }

    public AccountAuthenticationException(String message) {
        super(message);
    }

}