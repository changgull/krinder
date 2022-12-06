package dev.changgull.sample.wikipedia;

import dev.changgull.core.BaseTest;
import dev.changgull.sample.wikipedia.page.WikiContentPage;
import dev.changgull.sample.wikipedia.page.WikiHomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SearchWikiTest extends BaseTest {
    WikiHomePage page;
    @Test
    void searchWikipedia() {
        page = new WikiHomePage()
                .openPage()
                .verifySearchBoxExists()
                .search("testng");
    }

    @Test(dependsOnMethods = "searchWikipedia")
    void verifyWikipediaContent() {
        new WikiContentPage()
                .continueFrom(page)
                .verifyTitle("TestNG")
                .verifyExternalLinkUrl("http://testng.org/doc/");
    }

    @AfterClass(alwaysRun = true)
    void cleanUp() {
        safeClose(page);
    }
}
