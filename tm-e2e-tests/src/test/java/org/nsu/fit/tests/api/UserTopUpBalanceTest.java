package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.AccountTokenPojo;
import org.testng.annotations.Test;

public class UserTopUpBalanceTest extends UserTest {

    @Test(description = "Update balance as user.")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Balance feature.")
    public void topUpBalanceAsUserTest() {
        AccountTokenPojo userToken = getUserToken();
        new RestClient().updateUserBalance(userToken);

    }
}