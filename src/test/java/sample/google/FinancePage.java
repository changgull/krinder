package sample.google;

import dev.changgull.core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class FinancePage extends BasePage<FinancePage> {
    @FindBy(xpath = "//div[.//*[local-name()='svg']]/div/div/input[starts-with(@aria-label,'Search for stocks')]")
    WebElement inputStockSearch;
    @FindBy(xpath = "//div[@data-last-price]//div[starts-with(text(),'$')]")
    WebElement labelStockPrice;

    FinancePage() {
        setUrl(getProperty("url.google.finance"));
    }

    public FinancePage verifySearchBoxVisible() {
        Assert.assertTrue(inputStockSearch.isDisplayed(), "Stock search box is displayed");
        return this;
    }

    public FinancePage searchStock(String ticker) throws InterruptedException {
        inputStockSearch.click();
        Thread.sleep(1000);
        inputStockSearch.sendKeys(ticker);
        Thread.sleep(1000);
        inputStockSearch.sendKeys("\n");
        getWait().until(ExpectedConditions.urlContains(ticker));
        return this;
    }

    public FinancePage verifyPriceIsWithinRange(Double lowerBound, Double upperBound) {
        getWait().until(ExpectedConditions.visibilityOf(labelStockPrice));
        Double priceRead = Double.valueOf(labelStockPrice.getText().replace("$",""));
        getLogger().info("Stock price read: " + priceRead);
        Assert.assertTrue(priceRead > lowerBound && priceRead < upperBound,
                String.format("%.2f should be between %.2f and %.2f", priceRead, lowerBound, upperBound));
        return this;
    }
}
