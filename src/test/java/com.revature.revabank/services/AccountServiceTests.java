package com.revature.revabank.services;

import com.revature.revabank.exceptions.AuthenticationException;
import com.revature.revabank.exceptions.InvalidRequestException;
import com.revature.revabank.models.Account;
import com.revature.revabank.models.AccountType;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.models.Role;
import com.revature.revabank.repos.AccountRepository;
import com.revature.revabank.repos.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class AccountServiceTests {

    private AccountService sut;
    private AccountRepository mockAccountRepo = Mockito.mock(AccountRepository.class);
    Set<Account> mockUsers = new HashSet<>();

    /**
     * Sets up a new output stream to test printLn method
     */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Create mock users to test
     */
    @Before
    public void setup() {
        sut = new AccountService(mockAccountRepo);
        mockUsers.add(new Account(1, AccountType.CHECKING, 4,300.00d));
        mockUsers.add(new Account(2, AccountType.CHECKING, 3,300.00d));
        mockUsers.add(new Account(3, AccountType.SAVINGS, 2,300.00d));
        mockUsers.add(new Account(4, AccountType.SAVINGS, 1,300.00d));
        System.setOut(new PrintStream(outContent));
    }

    /**
     * To remove the mock users after the test is over, use removeAll
     */
    @After
    public void tearDown() {
        sut = null;
        mockUsers.removeAll(mockUsers);
        System.setOut(originalOut);
    }

//    @Test
//    public void authenticationWithValidCredentials() {
//
//        // Arrange
//        Account expectedAccount = new Account(1, AccountType.CHECKING, 30, 20.00d);
//        Mockito.when(mockAccountRepo.findAccountByUserId(1))
//                .thenReturn(Optional.of(expectedAccount));
//
//        // Act
//        Account actualResult = sut.authenticateByAccountId(1);
//
//        // Assert
//        Assert.assertEquals(expectedUser, actualResult);
//
//    }

    @Test(expected = InvalidRequestException.class)
    public void authenticationWithInvalidCredentials() {

        // Arrange
        // nothing to do here for this test; nothing to mock or expect

        // Act
        sut.showActiveAccounts(null);

        // Assert
        // nothing here, because the method should have raised an exception

    }

    @Test(expected = AuthenticationException.class)
    public void showAccountsWithUnknownCredentials() {
        sut.showActiveAccounts(700);
    }

    @Test (expected = InvalidRequestException.class)
    public void registerWithNullObject() { sut.register(null); }

    @Test (expected = NullPointerException.class)
    public void withdrawFromNonExistentAccount() throws IOException {
        // arrange
        Account mockAccount = new Account(null,null);

        // act
        sut.withdrawFunds(mockAccount, 200.00);

        // nothing to assert; method should raise an exception
    }

    @Test (expected = NullPointerException.class)
    public void depositToNonExistentAccount() throws IOException {
        // arrange
        Account mockAccount = new Account(null,null);

        // act
        sut.depositFunds(mockAccount, 200.00);

        // nothing to assert; method should raise an exception
    }

    @Test
    public void inValidAccountReturnsFalse() {
        // arrange
        Account mockAccount = new Account(null,null);

        // act and assert
        assertEquals(false, sut.isAccountValid(mockAccount));
    }

    @Test
    public void validAccountReturnsTrue() throws Exception {
        // arrange, act, assert
        assertEquals(true, sut.isAccountValid(mockUsers.stream().findAny().orElseThrow(Exception::new)));
    }

    @Test
    public void out() {
        // arrange
        System.out.print("hello");

        // act and assert
        assertEquals("hello", outContent.toString());
    }

//    @Test
//    public void accountFoundUsingAccountId() {
//        // arrange and act
//        assertEquals(mockAccountRepo.findAccountByAccountId(1), mockUsers.stream().findFirst());
//    }

    // TODO tests on deleteAccount


}



