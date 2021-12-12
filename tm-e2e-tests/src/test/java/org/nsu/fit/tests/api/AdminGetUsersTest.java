package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.RestClient;
import org.testng.annotations.Test;

public class AdminGetUsersTest extends AdminTest {
    @Test(description = "Get users as admin")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Customers feature.")
    public void getUsersTest() {
        new RestClient().getUsers(getAdminToken());
    }
}
