package dev.changgull.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
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

    public WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(Long.valueOf(getProperty("page.implicitTimeoutSec"))));
    }

    private void setDriverPath() {
        String osName = System.getProperty("os.name");
        String driverPath;
        if (osName.equals("Mac OS X")) {
            setControlKey(Keys.COMMAND);
            if (System.getProperty("os.arch").equals("aarch64")) {
                driverPath = "bin/mac_arm64/chromedriver";
            } else {
                driverPath =  "bin/mac64/chromedriver";
            }
        } else if (osName.equals("Linux")) {
            setControlKey(Keys.CONTROL);
            driverPath =  "bin/linux64/chromedriver";
        } else {
            driverPath = "";
            getLogger().warning("No driver available for " + osName);
        }
        System.setProperty("webdriver.chrome.driver", driverPath);
    }

    public T takeScreenShot() {
        try {
            Long timeEpoch = Instant.now().getEpochSecond();
            String filePath = "target/screenshots/" + getClass().getName() + timeEpoch + ".png";
            File screenShotFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShotFile, new File(filePath));
        } catch (IOException e) {
            getLogger().warning("Failed to save screenshot");
            e.printStackTrace();
        }
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
