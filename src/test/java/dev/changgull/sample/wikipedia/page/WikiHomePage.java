package dev.changgull.sample.wikipedia.page;

import dev.changgull.core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class WikiHomePage extends BasePage<WikiHomePage> {
    @FindBy(xpath = "//input[@id='searchInput']")
    WebElement inputSearch;
    public WikiHomePage() {
        setUrl(getProperty("url.wikipedia.home"));
    }

    public WikiHomePage verifySearchBoxExists() {
        Assert.assertTrue(inputSearch.isDisplayed(), "Search input box displayed");
        return this;
    }

    public WikiHomePage search(String searchKeyword) {
        inputSearch.sendKeys(searchKeyword);
        inputSearch.submit();
        getWait().until(ExpectedConditions.urlContains("/wiki/"));
        return this;
    }
}
