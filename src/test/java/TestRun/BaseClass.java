package TestRun;

import DriverFactory.Browsers;
import DriverFactory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;


import java.util.concurrent.TimeUnit;

public abstract class BaseClass {

    public static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseClass.class);


    @BeforeAll
    public static void setUp() {
        driver = WebDriverFactory.create(Browsers.CHROME);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        logger.info("webdriver configured");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null)
            driver.quit();
    }
}


