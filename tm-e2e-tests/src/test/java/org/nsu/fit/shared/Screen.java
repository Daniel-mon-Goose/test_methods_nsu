package org.nsu.fit.shared;

import org.nsu.fit.services.browser.Browser;
import org.openqa.selenium.support.PageFactory;

public abstract class Screen {
    protected Browser browser;

    public Screen(Browser browser) {
        this.browser = browser;
        PageFactory.initElements(this.browser.getWebDriver(), this);
    }
}
