package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.AccountTokenPojo;
import org.testng.annotations.Test;

public class GetUsersTest extends AdminTest {
    @Test(description = "Get users as admin")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Customers feature.")
    public void getUsersAsAdminTest() {
        new RestClient().getUsers(getAdminToken());
    }

    @Test(description = "Get users as user")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Customers feature.")
    public void getUsersAsUserTest() {
        AccountTokenPojo userToken = new RestClient().authenticate("user@us.er", "defaultpass");
        new RestClient().getUsers(userToken);
    }
}
