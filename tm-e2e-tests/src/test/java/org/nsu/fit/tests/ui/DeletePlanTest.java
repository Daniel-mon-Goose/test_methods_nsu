package org.nsu.fit.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.shared.RandomClientDataGenerator;
import org.nsu.fit.tests.ui.data.Plan;
import org.nsu.fit.tests.ui.screen.AdminScreen;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeletePlanTest extends BaseTest {
    @Test(description = "Delete plan via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Plan feature.")
    public void deletePlanTest() {
        String name = RandomClientDataGenerator.createRandomName(2, 12);
        String details = RandomClientDataGenerator.createRandomName(2, 12);
        int fee = 10;

        AdminScreen screen = new LoginScreen(browser)
                .loginAsAdmin()
                .createPlan()
                .fillName(name)
                .fillDetails(details)
                .fillFee(fee)
                .clickSubmit();

        Plan plan = screen.findPlan(name);

        Assert.assertNotNull(plan);
        Assert.assertNull(screen
                .deleteFirstPlan()
                .deleteFirstPlanSave()
                .findPlan(name));
    }
}
