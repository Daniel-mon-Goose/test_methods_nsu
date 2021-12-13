package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.PlanPojo;
import org.testng.annotations.Test;

public class AdminDeletePlanTest extends AdminTest {
    @Test(description = "Get plans as admin")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Plans feature.")
    public void deletePlanAsAdminTest() {

        PlanPojo plan = new RestClient().createPlan(getAdminToken());
        new RestClient().deletePlan(getAdminToken(), plan.id);
    }
}
