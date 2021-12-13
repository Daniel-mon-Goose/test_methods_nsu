package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.CustomerPojo;
import org.testng.annotations.Test;

public class AdminDeleteUserTest extends AdminTest {
    @Test(description = "Delete user as admin")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Customers feature.")
    public void deleteUserAsAdminTest() {
        CustomerPojo temporaryCustomer = new RestClient().createAutoGeneratedCustomer(getAdminToken());

        new RestClient().deleteUser(getAdminToken(), temporaryCustomer.id);
    }
}
