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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AccountServiceTests {

    private AccountService sut;
    private AccountRepository mockAccountRepo = Mockito.mock(AccountRepository.class);
    Set<Account> mockUsers = new HashSet<>();

    @Before
    public void setup() {
        sut = new AccountService(mockAccountRepo);
        mockUsers.add(new Account(1, AccountType.CHECKING, 4,300.00d));
        mockUsers.add(new Account(2, AccountType.CHECKING, 3,300.00d));
        mockUsers.add(new Account(3, AccountType.SAVINGS, 2,300.00d));
        mockUsers.add(new Account(4, AccountType.SAVINGS, 1,300.00d));
    }

    @After
    public void tearDown() {
        sut = null;
        mockUsers.removeAll(mockUsers);
    }

//    @Test
//    public void authenticationWithValidCredentials() {
//
//        // Arrange
//        AppUser expectedUser = new AppUser(1, "Adam", "Inn", "admin", "secret", Role.ADMIN);
//        Mockito.when(mockUserRepo.findUserByCredentials("admin", "secret"))
//                .thenReturn(Optional.of(expectedUser));
//
//        // Act
//        AppUser actualResult = sut.authenticate("admin", "secret");
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
        sut.authenticateAccount(null);

        // Assert
        // nothing here, because the method should have raised an exception

    }

    @Test(expected = AuthenticationException.class)
    public void authenticationWithUnknownCredentials() {
        sut.authenticateAccount(700);
    }

}
