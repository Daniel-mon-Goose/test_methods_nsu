package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.AccountTokenPojo;
import org.testng.annotations.Test;

public class UserGetAvailablePlansTest extends UserTest {

    @Test(description = "Get available plans as user.")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Plans feature.")
    public void availablePlansTest() {
        AccountTokenPojo userToken = getUserToken();
        new RestClient().getAvailablePlans(userToken);

    }
}