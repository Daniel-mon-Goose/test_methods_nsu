package org.nsu.fit.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.tests.ui.screen.AdminScreen;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HiTest extends BaseTest {
    @Test(description = "Greetings admin via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Auth feature.")
    public void hiAdminTest() {
        AdminScreen screen = new LoginScreen(browser)
                .loginAsAdmin();

        String greetingsAdmin = screen.getGreetings();

        Assert.assertEquals(greetingsAdmin, "Hi admin!");

    }

    @Test(description = "Greetings customer via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Auth feature.")
    public void hiCustomerTest() {

        String greetingsCustomer = new LoginScreen(browser)
                .loginAsCustomer("test@test.com", "testtest")
                .getGreetings();

        Assert.assertEquals(greetingsCustomer, "Hi Test Test!");
    }

    @Test(description = "Greetings different users via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Auth feature.")
    public void hiAnyoneTest() {
        AdminScreen screen = new LoginScreen(browser)
                .loginAsAdmin();

        String greetingsAdmin = screen.getGreetings();

        Assert.assertEquals(greetingsAdmin, "Hi admin!");

        String greetingsCustomer = screen
                .logout()
                .loginAsCustomer("test@test.com", "testtest")
                .getGreetings();

        Assert.assertEquals(greetingsCustomer, "Hi Test Test!");
    }
}
