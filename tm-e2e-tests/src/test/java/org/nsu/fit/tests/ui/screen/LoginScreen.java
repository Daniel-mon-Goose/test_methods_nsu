package org.nsu.fit.tests.ui.screen;

import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.shared.Screen;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginScreen extends Screen {

    @FindBy(xpath = "//input[@id='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;

    public LoginScreen(Browser browser) {
        super(browser);
    }

    public AdminScreen loginAsAdmin() {
        this.email.sendKeys("admin");
        this.password.sendKeys("setup");
        this.submit.click();

        return new AdminScreen(browser);
    }

    public CustomerScreen loginAsCustomer(String userName, String password) {
        this.email.sendKeys(userName);
        this.password.sendKeys(password);
        this.submit.click();

        return new CustomerScreen(browser);
    }
}
