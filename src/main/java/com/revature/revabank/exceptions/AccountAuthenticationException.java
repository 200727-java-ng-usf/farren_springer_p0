package com.revature.revabank.exceptions;

public class AccountAuthenticationException extends RuntimeException {

    public AccountAuthenticationException() {
        super("Account does not exist! Try opening an account first...");
    }

    public AccountAuthenticationException(String message) {
        super(message);
    }

}