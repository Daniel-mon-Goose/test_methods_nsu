package org.nsu.fit.tests.ui.screen;

import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.shared.Screen;
import org.nsu.fit.tests.ui.data.Plan;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerScreen extends Screen {
    private static final String subscriptions = "//div[@class='MuiPaper-root MuiPaper-elevation2 MuiPaper-rounded'][1]";
    private static final String plans = "//div[@class='MuiPaper-root MuiPaper-elevation2 MuiPaper-rounded'][2]";

    @FindBy(xpath = "//a[@href='/tm-frontend/top-up-balance']")
    private WebElement topUpBalance;

    @FindBy(xpath = "//h3[contains(text(), 'Your balance:')]")
    private WebElement currentBalance;

    @FindBy(xpath = subscriptions + "//input[@type='text' and @placeholder='Search']")
    private WebElement searchSubscription;

    @FindBy(xpath = plans + "//input[@type='text' and @placeholder='Search']")
    private WebElement searchPlan;

    @FindBy(xpath = "//h2[1]")
    private WebElement greeting;

    @FindBy(xpath = "//a[@href='/tm-frontend/login']")
    private WebElement logout;

    public CustomerScreen(Browser browser) {
        super(browser);
    }

    public int getCurrentBalance() {
        String[] balance = currentBalance.getText().split(" ");
        return Integer.parseInt(balance[balance.length - 1]);
    }

    public TopUpBalanceScreen topUpBalance() {
        topUpBalance.click();
        return new TopUpBalanceScreen(browser);
    }

    public Plan findSubscription(String name) {
        searchSubscription.clear();
        searchSubscription.sendKeys(name);

        try {
            String foundName = browser.getValue(By.xpath(subscriptions + "//tr[@index='0'][1]/td[2]"));
            String foundDetails = browser.getValue(By.xpath(subscriptions + "//tr[@index='0'][1]/td[3]"));
            String foundFee = browser.getValue(By.xpath(subscriptions + "//tr[@index='0'][1]/td[4]"));

            return new Plan(foundName, foundDetails, Integer.parseInt(foundFee));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public CustomerScreen deleteFirstSubscription() {
        browser.click(By.xpath(subscriptions + "//tr[@index='0'][1]/td[1]/div/button[1]"));
        return this;
    }

    public CustomerScreen deleteFirstSubscriptionSave() {
        browser.click(By.xpath(subscriptions + "//tr[@index='0' and @mode='delete'][1]/td[1]/div/button[1]"));
        browser.waitForElementToBeRemove(By.xpath(subscriptions + "/div[3]"), 10);
        return this;
    }

    public CustomerScreen deleteFirstSubscriptionCancel() {
        browser.click(By.xpath(subscriptions + "//tr[@index='0' and @mode='delete'][1]/td[1]/div/button[2]"));
        return this;
    }

    public Plan findPlan(String name) {
        searchPlan.clear();
        searchPlan.sendKeys(name);

        try {
            String foundName = browser.getValue(By.xpath(plans + "//tr[@index='0'][1]/td[2]"));
            String foundDetails = browser.getValue(By.xpath(plans + "//tr[@index='0'][1]/td[3]"));
            String foundFee = browser.getValue(By.xpath(plans + "//tr[@index='0'][1]/td[4]"));

            return new Plan(foundName, foundDetails, Integer.parseInt(foundFee));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public CustomerScreen buyFirstPlan() {
        browser.click(By.xpath(plans + "//tr[@index='0'][1]/td[1]/div/button[1]"));
        return this;
    }

    public CustomerScreen buyFirstPlanOk() {
        browser.click(By.xpath(plans + "//tr[@index='0' and @mode='delete'][1]/td[1]/div/button[1]"));
        return this;
    }

    public CustomerScreen buyFirstPlanCancel() {
        browser.click(By.xpath(plans + "//tr[@index='0' and @mode='delete'][1]/td[1]/div/button[2]"));
        return this;
    }

    public String getGreetings() {
        return greeting.getText();
    }

    public LoginScreen logout() {
        logout.click();
        return new LoginScreen(browser);
    }
}
