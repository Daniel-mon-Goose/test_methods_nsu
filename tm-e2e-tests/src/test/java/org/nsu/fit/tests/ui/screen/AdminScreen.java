package org.nsu.fit.tests.ui.screen;

import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.shared.Screen;
import org.openqa.selenium.By;

public class AdminScreen extends Screen {
    public AdminScreen(Browser browser) {
        super(browser);
    }

    public CreateCustomerScreen createCustomer() {
        browser.click(By.xpath("/MaterialTable/actions[@tooltip = 'Add Customer']"));
        return new CreateCustomerScreen(browser);
    }


}
