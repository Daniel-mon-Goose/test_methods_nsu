package org.nsu.fit.tests.ui.screen;

import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.shared.Screen;
import org.nsu.fit.tests.ui.data.Customer;
import org.nsu.fit.tests.ui.data.Plan;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminScreen extends Screen {
    private static final String customers = "//div[@class='MuiPaper-root MuiPaper-elevation2 MuiPaper-rounded'][1]";
    private static final String plans = "//div[@class='MuiPaper-root MuiPaper-elevation2 MuiPaper-rounded'][2]";

    @FindBy(xpath = "//button[@title='Add Customer']")
    private WebElement createCustomer;

    @FindBy(xpath = "//button[@title='Add plan']")
    private WebElement createPlan;

    @FindBy(xpath = customers + "//input[@type='text' and @placeholder='Search']")
    private WebElement searchCustomer;

    @FindBy(xpath = plans + "//input[@type='text' and @placeholder='Search']")
    private WebElement searchPlan;

    @FindBy(xpath = "//h2[1]")
    private WebElement greeting;

    @FindBy(xpath = "//a[@href='/tm-frontend/login']")
    private WebElement logout;

    public AdminScreen(Browser browser) {
        super(browser);
        browser.waitForElement(By.xpath(customers), 5);
    }

    public CreateCustomerScreen createCustomer() {
        this.createCustomer.click();
        return new CreateCustomerScreen(browser);
    }

    public CreatePlanScreen createPlan() {
        this.createPlan.click();
        return new CreatePlanScreen(browser);
    }

    public Customer findCustomer(String login) {
        searchCustomer.clear();
        searchCustomer.sendKeys(login);

        try {
            String foundLogin = browser.getValue(By.xpath(customers + "//tr[1]/td[2]"));
            String foundFirstName = browser.getValue(By.xpath(customers + "//tr[1]/td[3]"));
            String foundLastName = browser.getValue(By.xpath(customers + "//tr[1]/td[4]"));

            return new Customer(foundLogin, foundFirstName, foundLastName);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public AdminScreen deleteFirstCustomer() {
        browser.click(By.xpath(customers + "//tr[1]/td[1]/div[1]/button[1]"));
        return this;
    }

    public AdminScreen deleteFirstCustomerSave() {
        browser.click(By.xpath(customers + "//tr[1]/td[1]/div[1]/button[1]"));
        browser.waitForElementToBeRemove(By.xpath(customers + "/div[3]"), 10);
        return this;
    }

    public AdminScreen deleteFirstCustomerCancel() {
        browser.click(By.xpath(customers + "//tr[1]/td[1]/div[1]/button[2]"));
        return this;
    }

    public Plan findPlan(String name) {
        searchPlan.clear();
        searchPlan.sendKeys(name);

        try {
            String foundName = browser.getValue(By.xpath(plans + "//tr[1]/td[2]"));
            String foundDetails = browser.getValue(By.xpath(plans + "//tr[1]/td[3]"));
            String foundFee = browser.getValue(By.xpath(plans + "//tr[1]/td[4]"));

            return new Plan(foundName, foundDetails, Integer.parseInt(foundFee));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public AdminScreen deleteFirstPlan() {
        browser.click(By.xpath(plans + "//tr[1]/td[1]/div/button[1]"));
        return this;
    }

    public AdminScreen deleteFirstPlanSave() {
        browser.click(By.xpath(plans + "//tr[1]/td[1]/div/button[1]"));
        browser.waitForElementToBeRemove(By.xpath(plans + "/div[3]"), 10);
        return this;
    }

    public AdminScreen deleteFirstPlanCancel() {
        browser.click(By.xpath(plans + "//tr[1]/td[1]/div/button[2]"));
        return this;
    }

    public String getGreetings() {
        return greeting.getText();
    }

    public LoginScreen logout() {
        logout.click();
        return new LoginScreen(browser);
    }

    public AdminScreen reload() {
        browser.openPage(browser.getWebDriver().getCurrentUrl());
        return this;
    }
}
