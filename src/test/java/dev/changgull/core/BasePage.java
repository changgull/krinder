package dev.changgull.core;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

public class BasePage<T> extends Base {
    private String _url;
    private WebDriver _driver;
    private Keys _controlKey;

    protected void initChromeDriver() {
        setDriverPath();
        ChromeOptions options = new ChromeOptions();
        String optionsStr = getProperty("chrome.options") + getProperty("browser.options");
        options.addArguments(Arrays.asList(optionsStr.split("[|]")));
        getLogger().info("browser options: " + options.toString());
        setDriver(new ChromeDriver(options));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.valueOf(getProperty("page.implicitTimeoutSec"))));
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

    public void closePage() {
        getDriver().quit();
    }

    public WebElement findElement(String xpath) {
        return getDriver().findElement(By.xpath(xpath));
    }

    public WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(Long.valueOf(getProperty("page.implicitTimeoutSec"))));
    }

    private void setDriverPath() {
        String osName = System.getProperty("os.name");
        String overridePath = getProperty("chromeDriverPath");
        String driverPath;
        if (osName.equals("Mac OS X")) {
            setControlKey(Keys.COMMAND);
            if (System.getProperty("os.arch").equals("aarch64")) {
                driverPath = "bin/mac_arm64/chromedriver";
            } else {
                driverPath = "bin/mac64/chromedriver";
            }
        } else if (osName.equals("Linux")) {
            setControlKey(Keys.CONTROL);
            driverPath = "bin/linux64/chromedriver";
        } else {
            driverPath = "";
            getLogger().warning("No driver available for " + osName);
        }
        if (overridePath != null) {
            System.setProperty("webdriver.chrome.driver", overridePath);
        } else {
            System.setProperty("webdriver.chrome.driver", driverPath);
        }
    }

    protected String getUrl() {
        return _url;
    }

    protected void setUrl(String url) {
        _url = url;
    }

    private void setControlKey(Keys key) {
        _controlKey = key;
    }

    protected Keys getControlKey(Keys key) {
        return _controlKey;
    }

    protected WebDriver getDriver() {
        return _driver;
    }

    private void setDriver(WebDriver driver) {
        _driver = driver;
    }
}
