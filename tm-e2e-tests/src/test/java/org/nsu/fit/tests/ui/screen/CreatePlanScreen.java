package org.nsu.fit.tests.ui.screen;

import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.shared.Screen;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePlanScreen extends Screen {
    @FindBy(xpath = "//input[@name='name']")
    private WebElement name;

    @FindBy(xpath = "//input[@name='details']")
    private WebElement details;

    @FindBy(xpath = "//input[@name='fee']")
    private WebElement fee;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;

    @FindBy(xpath = "//button[@type='button']")
    private WebElement cancel;

    public CreatePlanScreen(Browser browser) {
        super(browser);
    }

    public CreatePlanScreen fillName(String name) {
        this.name.clear();
        this.name.sendKeys(name);
        return this;
    }

    public CreatePlanScreen fillDetails(String details) {
        this.details.clear();
        this.details.sendKeys(details);
        return this;
    }

    public CreatePlanScreen fillFee(int fee) {
        this.fee.clear();
        this.fee.sendKeys(String.valueOf(fee));
        return this;
    }

    public AdminScreen clickSubmit() {
        submit.click();
        return new AdminScreen(browser);
    }

    public AdminScreen clickCancel() {
        cancel.click();
        return new AdminScreen(browser);
    }
}
