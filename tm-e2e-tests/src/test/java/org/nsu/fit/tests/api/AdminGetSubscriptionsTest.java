package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.RestClient;
import org.testng.annotations.Test;

public class AdminGetSubscriptionsTest extends AdminTest {
    @Test(description = "Get subscriptions as admin")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Subscriptions feature.")
    public void getSubscriptionsASAdminTest() {
        new RestClient().getSubscriptions(getAdminToken());
    }
}
