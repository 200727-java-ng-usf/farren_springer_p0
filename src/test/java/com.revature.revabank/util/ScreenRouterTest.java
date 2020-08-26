package com.revature.revabank.util;

import com.revature.revabank.exceptions.ScreenNotFoundException;
import com.revature.revabank.repos.UserRepository;
import com.revature.revabank.screens.EditUserInfoScreen;
import com.revature.revabank.screens.Screen;
import com.revature.revabank.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.revature.revabank.AppDriver.app;
import static org.junit.Assert.assertEquals;


public class ScreenRouterTest {

    private ScreenRouter sut;
    private UserRepository mockUserRepo = new UserRepository();
    private UserService mockUserService = new UserService(mockUserRepo);

    @Before
    public void setup() { sut = new ScreenRouter(); }

    @After
    public void tearDown() { sut = null; }

    @Test (expected = ScreenNotFoundException.class)
    public void navigateWithInvalidScreen() { sut.navigate("/notAPath"); }

}
