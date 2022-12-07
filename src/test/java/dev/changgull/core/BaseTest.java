package dev.changgull.core;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import javax.annotation.Nullable;

public class BaseTest extends Base {
    @Parameters({"env", "browserOptions", "chromeDriverPath"})
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(@Optional("stage") String env
            , @Optional("") String browserOptions
            , @Optional @Nullable String chromeDriverPath) {
        loadProperties("default.properties");
        loadProperties(env + ".properties");
        setProperty("browser.options", browserOptions);
        if (chromeDriverPath != null) setProperty("chromeDriverPath", chromeDriverPath);
    }

    public void safeClose(BasePage page) {
        if (page != null && page.getDriver() != null) {
            page.closePage();
        }
    }
}