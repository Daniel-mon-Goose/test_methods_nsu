package org.nsu.fit.tests.ui.screen;

import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.shared.Screen;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TopUpBalanceScreen extends Screen {
    @FindBy(xpath = "//input[@name='money']")
    private WebElement money;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;

    @FindBy(xpath = "//button[@type='button']")
    private WebElement cancel;

    public TopUpBalanceScreen(Browser browser) {
        super(browser);
    }

    public TopUpBalanceScreen fillMoney(int money) {
        this.money.clear();
        this.money.sendKeys(String.valueOf(money));
        return this;
    }

    public CustomerScreen clickSubmit() {
        submit.click();
        return new CustomerScreen(browser);
    }

    public CustomerScreen clickCancel() {
        cancel.click();
        return new CustomerScreen(browser);
    }
}
