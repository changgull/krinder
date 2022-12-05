package dev.changgull.core;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest extends Base {
    @Parameters({"env", "browserOptions"})
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(@Optional("stage") String env, @Optional("") String browserOptions) {
        loadProperties("default.properties");
        loadProperties(env + ".properties");
        getProperties().setProperty("browser.options", browserOptions);
    }
}