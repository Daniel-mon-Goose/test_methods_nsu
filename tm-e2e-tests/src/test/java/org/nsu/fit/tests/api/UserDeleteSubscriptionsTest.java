package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.AccountTokenPojo;
import org.nsu.fit.services.rest.data.PlanPojo;
import org.nsu.fit.services.rest.data.SubscriptionPojo;
import org.testng.annotations.Test;

import java.util.List;

public class UserDeleteSubscriptionsTest extends UserTest {

    @Test(description = "Delete subscription as user")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Subscriptions feature.")
    public void deleteSubscriptionAsUserTest() {

        AccountTokenPojo userToken = getUserToken();
        List<SubscriptionPojo> list = new RestClient().getAvailableSubscriptions(userToken);
        new RestClient().deleteSubscription(getUserToken(), list.get(0).id);
    }
}
