package org.nsu.fit.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.tests.ui.screen.CustomerScreen;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TopUpBalanceTest extends BaseTest {
    @Test(description = "Top up balance via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Balance feature.")
    public void topUpBalanceTest() {
        CustomerScreen screen = new LoginScreen(browser)
                .loginAsCustomer("test@test.com", "testtest");

        int initialMoney = screen.getCurrentBalance();

        int money = screen
                .topUpBalance()
                .fillMoney(10)
                .clickSubmit()
                .getCurrentBalance();

        Assert.assertEquals(money, initialMoney + 10);
    }
}
