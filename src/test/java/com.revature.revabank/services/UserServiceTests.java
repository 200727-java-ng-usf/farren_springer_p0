package com.revature.revabank.services;

import com.revature.revabank.exceptions.AuthenticationException;
import com.revature.revabank.exceptions.InvalidRequestException;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.models.Role;
import com.revature.revabank.repos.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.revature.revabank.AppDriver.app;

public class UserServiceTests {

    private UserService sut;
    private UserRepository mockUserRepo = Mockito.mock(UserRepository.class);
    Set<AppUser> mockUsers = new HashSet<>();

    @Before
    public void setup() {
        sut = new UserService(mockUserRepo);
        mockUsers.add(new AppUser(1, "Adam", "Inn", "admin", "secret", "admin@app.com", Role.ADMIN));
        mockUsers.add(new AppUser(2, "Manny", "Gerr", "manager", "manage", "manager@app.com", Role.MANAGER));
        mockUsers.add(new AppUser(3, "Alice", "Anderson", "aanderson", "password", "admin@app.com", Role.BASIC_MEMBER));
        mockUsers.add(new AppUser(4, "Bob", "Bailey", "bbailey", "dev", "dev@app.com", Role.PREMIUM_MEMBER));
    }

    @After
    public void tearDown() {
        sut = null;
        mockUsers.removeAll(mockUsers);
    }

    @Test(expected = InvalidRequestException.class)
    public void authenticationWithInvalidCredentials() {

        // Arrange
        // nothing to do here for this test; nothing to mock or expect

        // Act
        sut.authenticate("", "");

        // Assert
        // nothing here, because the method should have raised an exception

    }

    @Test
    public void authenticationWithValidCredentials() {

        // Arrange. Mock findUserByCredentials, because we want the test to only test authenticate
        AppUser expectedUser = new AppUser("Adam", "Inn", "admin", "secret", "ainn@revature.com", Role.ADMIN);
        Mockito.when(mockUserRepo.findUserByCredentials("admin", "secret"))
                .thenReturn(Optional.of(expectedUser));

        // Act
        sut.authenticate("admin", "secret");
        AppUser actualResult = app.getCurrentUser();

        // Assert
        Assert.assertEquals(expectedUser, actualResult);

    }

    @Test(expected = AuthenticationException.class)
    public void authenticationWithUnknownCredentials() {
        sut.authenticate("garbage", "user");
    }

    @Test (expected = InvalidRequestException.class)
    public void registerWithNullAppUser() {
        // Arrange
        // nothing to do here for this test; nothing to mock or expect

        // Act
        sut.register(null);

        // Assert
        // nothing here, because the method should have raised an exception
    }

    @Test
    public void registerWithValidAppUser() {

    }

}
