package org.nsu.fit.tests.ui;

import com.mifmif.common.regex.Generex;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.tests.ui.screen.AdminScreen;
import org.nsu.fit.tests.ui.screen.LoginScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.services.browser.BrowserService;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class CreateCustomerTest {

    private Browser browser = null;

    @BeforeClass
    public void beforeClass() {
        browser = BrowserService.openNewBrowser();
    }

    @Test(description = "Create customer via UI.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Create customer feature")
    public void createCustomer() {
        LoginScreen screen = new LoginScreen(browser);
        AdminScreen ascreen = screen.loginAsAdmin();
        browser.waitForElement(By.partialLinkText("Logout"));
        Generex namesGenerator = new Generex("([A-Z][a-z]{2,12})");
        Generex loginsGenerator = new Generex("([a-zA-Z0-9_]{1,})[@]([a-zA-Z0-9]{1,})[\\.]([a-zA-Z0-9]{1,})");
        String genName = namesGenerator.random();
        String genLogin = loginsGenerator.random();

        AdminScreen newAScreen = ascreen.createCustomer()
                .fillEmail(genLogin)
                .fillPassword("strongPass1")
                .fillFirstName(genName)
                .fillLastName(genName)
                .clickSubmit();

        assertEquals(true, true);
        // Лабораторная 4: Проверить что customer создан с ранее переданными полями.
        // Решить проблему с генерацией случайных данных.
    }

    @AfterClass
    public void afterClass() {
        if (browser != null) {
            browser.close();
        }
    }


}
