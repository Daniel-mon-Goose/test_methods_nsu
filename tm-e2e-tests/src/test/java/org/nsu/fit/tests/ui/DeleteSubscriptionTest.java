package org.nsu.fit.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.tests.ui.data.Plan;
import org.nsu.fit.tests.ui.screen.CustomerScreen;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteSubscriptionTest extends BaseTest {
    @Test(description = "Delete subscription via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Subscription feature.")
    public void deleteSubscriptionTest() {
        CustomerScreen screen = new LoginScreen(browser)
                .loginAsCustomer("test@test.com", "testtest");

        Plan planToBuy = screen.findPlan("");

        Assert.assertNotNull(planToBuy);

        Plan subscription = screen
                .buyFirstPlan()
                .buyFirstPlanOk()
                .findSubscription(planToBuy.name);

        Assert.assertNotNull(subscription);

        Assert.assertNull(screen
                .deleteFirstSubscription()
                .deleteFirstSubscriptionSave()
                .findSubscription(subscription.name));
    }
}
