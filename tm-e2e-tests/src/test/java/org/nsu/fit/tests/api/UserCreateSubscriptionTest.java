package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.AccountTokenPojo;
import org.nsu.fit.services.rest.data.CustomerPojo;
import org.nsu.fit.services.rest.data.PlanPojo;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class UserCreateSubscriptionTest extends UserTest {

    private PlanPojo plan;

    @Test(description = "Create subscription as user.")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Subscriptions feature.")
    public void createSubscriptionTest() {
        AccountTokenPojo userToken = getUserToken();
        List<PlanPojo> list = new RestClient().getAvailablePlans(userToken);
        plan = list.get(0);
        new RestClient().createSubscription(userToken, plan);
    }

}