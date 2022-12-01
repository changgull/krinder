package dev.changgull.core;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class BasePage<T> extends Base {
    private String _url;
    private WebDriver _driver;
    private Keys _controlKey;

    protected void initChromeDriver() {
        String driverPath;
        String osName = System.getProperty("os.name");
        if (osName.equals("Mac OS X")) {
            if (System.getProperty("os.arch").equals("aarch64")) {
                driverPath = "bin/mac_arm64/chromedriver";
            } else {
                driverPath = "bin/mac64/chromedriver";
            }
        } else if (osName.equals("Linux")) {
            driverPath = "bin/linux64/chromedriver";
        } else {
            Assert.fail("We do not have the web driver for this OS. Refer to README.md");
        }
    }

    public T openUrl(String url) {
        if (getDriver() == null) initChromeDriver();
        getLogger().info("Opening url: " + url);
        getDriver().get(url);
        PageFactory.initElements(getDriver(), this);
        return (T) this;
    }

    public T openPage() {
        return openUrl(getUrl());
    }

    public T continueFrom(BasePage page) {
        setDriver(page.getDriver());
        PageFactory.initElements(getDriver(), this);
        return (T) this;
    }

    protected void setUrl(String url) {
        _url = url;
    }

    protected String getUrl() {
        return _url;
    }

    private void setControlKey(Keys key) {
        _controlKey = key;
    }

    protected Keys getControlKey(Keys key) {
        return _controlKey;
    }

    private void setDriver(WebDriver driver) {
        _driver = driver;
    }

    protected WebDriver getDriver() {
        return _driver;
    }
}
