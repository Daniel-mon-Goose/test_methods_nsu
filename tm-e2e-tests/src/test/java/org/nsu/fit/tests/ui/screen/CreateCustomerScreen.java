package org.nsu.fit.tests.ui.screen;

import org.nsu.fit.services.browser.Browser;
import org.nsu.fit.shared.Screen;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateCustomerScreen extends Screen {

    @FindBy(xpath = "//input[@name='login']")
    private WebElement email;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastName;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;

    @FindBy(xpath = "//button[@type='button']")
    private WebElement cancel;


    public CreateCustomerScreen(Browser browser) {
        super(browser);
    }

    public CreateCustomerScreen fillEmail(String email) {
        this.email.clear();
        this.email.sendKeys(email);
        return this;
    }

    public CreateCustomerScreen fillPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
        return this;
    }

    public CreateCustomerScreen fillFirstName(String firstName) {
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
        return this;
    }

    public CreateCustomerScreen fillLastName(String lastName) {
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
        return this;
    }

    // Лабораторная 4: Подумайте как обработать ситуацию,
    // когда при нажатии на кнопку Submit ('Create') не произойдет переход на AdminScreen,
    // а будет показана та или иная ошибка на текущем скрине.
    public AdminScreen clickSubmit() {
        submit.click();
        return new AdminScreen(browser);
    }

    public AdminScreen clickCancel() {
        cancel.click();
        return new AdminScreen(browser);
    }
}
