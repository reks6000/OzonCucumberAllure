package ru.aplana.cucumberallure;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    @Before
    public void beforeEach() {
        Init.setUp();
    }

    @Attachment(type = "image/png", value = "Screenshot on failure")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot)Init.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenshot();
        }
        Init.tearDown();
    }
}
