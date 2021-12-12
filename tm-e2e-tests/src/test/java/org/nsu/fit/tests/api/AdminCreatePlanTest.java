package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.RestClient;
import org.testng.annotations.Test;

public class AdminCreatePlanTest extends AdminTest {
    @Test(description = "Create plan as admin.")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Plans feature.")
    public void createPlanTest() {
        new RestClient().createPlan(getAdminToken());
    }
}
