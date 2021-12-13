package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.RestClient;
import org.testng.annotations.Test;

public class UserGetSubscriptionsTest extends UserTest {

    @Test(description = "Get subscriptions as user")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Subscriptions feature.")
    public void getSubscriptionsAsUserTest() {
        new RestClient().getAvailableSubscriptions(getUserToken());
    }
}
