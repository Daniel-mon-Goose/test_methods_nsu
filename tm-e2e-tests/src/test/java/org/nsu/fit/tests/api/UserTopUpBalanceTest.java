package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.AccountTokenPojo;
import org.nsu.fit.services.rest.data.CustomerPojo;
import org.testng.annotations.Test;

public class UserTopUpBalanceTest {
    private AccountTokenPojo adminToken;

    @Test(description = "Update balance as user.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Authentication feature.")
    public void topUpBalanceTest() {

        adminToken = new RestClient().authenticate("admin", "setup");
    }
}