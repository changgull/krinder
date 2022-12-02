package sample.google;

import dev.changgull.core.BaseTest;
import org.testng.annotations.Test;

public class SearchStockTest extends BaseTest {
    @Test
    void searchAndVerifyPrice() throws InterruptedException {
        new FinancePage()
                .openPage()
                .verifySearchBoxVisible()
                .searchStock("TSLA")
                .verifyPriceIsWithinRange(50.0, 500.0)
                .closePage();
    }
}
