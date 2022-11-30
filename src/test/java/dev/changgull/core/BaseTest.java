package dev.changgull.core;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest extends Base {
    @Parameters({"env"})
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(@Optional("stage") String envName) {
        loadProperties("default.properties");
        loadProperties(envName + ".properties");
    }
}