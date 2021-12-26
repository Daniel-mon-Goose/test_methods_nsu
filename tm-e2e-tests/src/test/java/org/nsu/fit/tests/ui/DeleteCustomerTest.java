package org.nsu.fit.tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.shared.RandomClientDataGenerator;
import org.nsu.fit.tests.ui.data.Customer;
import org.nsu.fit.tests.ui.screen.AdminScreen;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteCustomerTest extends BaseTest {
    @Test(description = "Delete customer via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Customer feature.")
    public void deleteCustomerTest() {
        String email = RandomClientDataGenerator.createEmail();
        String pass = RandomClientDataGenerator.createPassword(6, 12);
        String firstName = RandomClientDataGenerator.createRandomName(2, 12);
        String lastName = RandomClientDataGenerator.createRandomName(2, 12);

        AdminScreen screen = new LoginScreen(browser)
                .loginAsAdmin()
                .createCustomer()
                .fillEmail(email)
                .fillPassword(pass)
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .clickSubmit();

        Customer customer = screen.findCustomer(email);

        Assert.assertNotNull(customer);
        Assert.assertNull(screen
                .deleteFirstCustomer()
                .deleteFirstCustomerSave()
                .findCustomer(customer.login));
    }
}
