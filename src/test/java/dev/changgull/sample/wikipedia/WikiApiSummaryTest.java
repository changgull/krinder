package dev.changgull.sample.wikipedia;

import dev.changgull.core.BaseTest;
import dev.changgull.sample.wikipedia.api.WikipediaApi;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WikiApiSummaryTest extends BaseTest {
    @Test
    void verifyApiSummary() {
        JsonPath p = new WikipediaApi()
                .getRequest()
                .get("page/summary/TestNG")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();

        Assert.assertEquals(p.getString("title"), "TestNG");
        Assert.assertTrue(p.getString("extract").contains("Cédric Beust"));
    }
}
