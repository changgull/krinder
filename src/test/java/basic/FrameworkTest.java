package basic;

import dev.changgull.core.BaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * Unit test for simple App.
 */
public class FrameworkTest extends BaseTest
{
    @Test
    public void verifyTestProperties()
    {
        String envName = getProperty("environment.name");
        getLogger().info(envName);
        Assert.assertTrue(getProperty("environment.name").length() > 0);
    }
    @Test
    public void printOsArch() {
        getLogger().info(System.getProperty("os.name"));
        getLogger().info(System.getProperty("os.arch"));
    }
}
