package org.nsu.fit.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.tests.ui.data.Plan;
import org.nsu.fit.tests.ui.screen.CustomerScreen;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateSubscriptionTest extends BaseTest {
    @Test(description = "Create subscription via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Subscription feature.")
    public void createSubscriptionTest() {
        new LoginScreen(browser)
                .loginAsAdmin()
                .createPlan()
                .fillName("test@test.com")
                .fillDetails("details")
                .fillFee(10)
                .clickSubmit().logout();

        CustomerScreen screen = new LoginScreen(browser)
                .loginAsCustomer("test@test.com", "testtest");

        Plan planToBuy = screen.findPlan("");

        Assert.assertNotNull(planToBuy);

        Plan subscription = screen
                .buyFirstPlan()
                .buyFirstPlanOk()
                .findSubscription(planToBuy.name);

        Assert.assertEquals(subscription.name, planToBuy.name);
        Assert.assertEquals(subscription.details, planToBuy.details);
        Assert.assertEquals(subscription.fee, planToBuy.fee);
    }
}
