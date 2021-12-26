package org.nsu.fit.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.shared.RandomClientDataGenerator;
import org.nsu.fit.tests.ui.data.Plan;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreatePlanTest extends BaseTest {
    @Test(description = "Create plan via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Plan feature.")
    public void createPlanTest() {
        String name = RandomClientDataGenerator.createRandomName(2, 12);
        String details = RandomClientDataGenerator.createRandomName(2, 12);
        int fee = 10;

        Plan plan = new LoginScreen(browser)
                .loginAsAdmin()
                .createPlan()
                .fillName(name)
                .fillDetails(details)
                .fillFee(fee)
                .clickSubmit()
                .findPlan(name);

        Assert.assertEquals(plan.name, name);
        Assert.assertEquals(plan.details, details);
        Assert.assertEquals(plan.fee, fee);
    }
}
