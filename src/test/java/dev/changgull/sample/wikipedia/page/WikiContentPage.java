package dev.changgull.sample.wikipedia.page;

import dev.changgull.core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.testng.Assert;

import java.util.List;

public class WikiContentPage extends BasePage<WikiContentPage> {

    @FindBy(xpath = "//h1[@id='firstHeading']/span")
    WebElement labelTitle;
    @FindAll(@FindBy(xpath = "//h2[span[@id='External_links']]/following-sibling::ul/li/a"))
    List<WebElement> listExternalLinks;

    public WikiContentPage verifyTitle(String title) {
        Assert.assertEquals(labelTitle.getText(), title, "Content title");
        return this;
    }

    public WikiContentPage verifyExternalLinkUrl(String externalLinkUrl) {
        Assert.assertTrue(listExternalLinks.size() >= 1, "Content has more than 1 external links");
        Assert.assertEquals(listExternalLinks.get(0).getAttribute("href"), externalLinkUrl, "First external url");
        return this;
    }
}
