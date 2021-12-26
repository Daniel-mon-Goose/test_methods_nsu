package org.nsu.fit.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.shared.RandomClientDataGenerator;
import org.nsu.fit.tests.ui.data.Customer;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateCustomerTest extends BaseTest {
    @Test(description = "Create customer via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Customer feature.")
    public void createCustomerTest() {
        String email = RandomClientDataGenerator.createEmail();
        String pass = RandomClientDataGenerator.createPassword(6, 12);
        String firstName = RandomClientDataGenerator.createRandomName(2, 12);
        String lastName = RandomClientDataGenerator.createRandomName(2, 12);

        Customer customer = new LoginScreen(browser)
                .loginAsAdmin()
                .createCustomer()
                .fillEmail(email)
                .fillPassword(pass)
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .clickSubmit()
                .findCustomer(email);

        Assert.assertNotNull(customer);
        Assert.assertEquals(customer.login, email);
        Assert.assertEquals(customer.firstName, firstName);
        Assert.assertEquals(customer.lastName, lastName);
    }
}
