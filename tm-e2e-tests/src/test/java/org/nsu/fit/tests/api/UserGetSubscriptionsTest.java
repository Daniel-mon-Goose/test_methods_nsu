package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.AccountTokenPojo;
import org.testng.annotations.Test;

import javax.ws.rs.InternalServerErrorException;

import static org.testng.Assert.assertThrows;

public class UserGetSubscriptionsTest extends UserTest {

    @Test(description = "Get subscriptions as user")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Subscriptions feature.")
    public void getSubscriptionsAsUserTest() {
        new RestClient().getAvailableSubscriptions(getUserToken());
    }

    @Test(description = "Get subscriptions as non-existing user")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Subscriptions feature.")
    public void notGetSubscriptionsAsUserTest() {
        assertThrows(InternalServerErrorException.class, ()-> new RestClient().getAvailableSubscriptions(new AccountTokenPojo()));
    }
}
