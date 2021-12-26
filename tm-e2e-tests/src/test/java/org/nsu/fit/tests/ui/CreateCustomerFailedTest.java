package org.nsu.fit.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.shared.RandomClientDataGenerator;
import org.nsu.fit.tests.ui.data.Customer;
import org.nsu.fit.tests.ui.screen.CreateCustomerScreen;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateCustomerFailedTest extends BaseTest {
    @Test(description = "Create customer failed via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Customer feature.")
    public void createCustomerFailedTest() {
        String email = RandomClientDataGenerator.createEmail();
        String invalidPass = "123";
        String validPass = RandomClientDataGenerator.createPassword(6, 12);
        String firstName = RandomClientDataGenerator.createRandomName(2, 12);
        String lastName = RandomClientDataGenerator.createRandomName(2, 12);

        CreateCustomerScreen screen = new LoginScreen(browser)
                .loginAsAdmin()
                .createCustomer()
                .fillEmail(email)
                .fillPassword(invalidPass)
                .fillFirstName(firstName)
                .fillLastName(lastName);

        Assert.assertThrows(screen::clickSubmit);

        Customer customer = screen
                .fillPassword(validPass)
                .clickSubmit()
                .findCustomer(email);

        Assert.assertEquals(customer.login, email);
        Assert.assertEquals(customer.firstName, firstName);
        Assert.assertEquals(customer.lastName, lastName);
    }
}
