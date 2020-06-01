package TestRun;

import DriverFactory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

public abstract class BaseClass {

    public static WebDriver driver;
    public WebDriverWait wait;

    protected static final Logger logger = LogManager.getLogger("pom");


    @BeforeAll
    public static void setUp() {
        String browser = System.getProperty("browser");
        driver = WebDriverFactory.create(browser);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        logger.info("webdriver configured");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null)
            driver.quit();
    }

    private boolean isElementDisplayed(WebElement element) {
        Boolean displaying;
        try {
            displaying = element.isDisplayed();
        } catch (NoSuchElementException e) {
            displaying = false;
        }

        return displaying;
    }

    private boolean isElementDisplayedByLocator(By locator) {
        Boolean displaying;
        try {
            displaying = driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            displaying = false;
        }
        return displaying;
    }

        public void checkElementIsNotDisplayed (WebElement element){
            if (isElementDisplayed(element) == true) {
                try {
                    wait.until(ExpectedConditions.invisibilityOf(element));
                } catch (TimeoutException te) {
                    System.out.println("TimeoutException occurred while wait.until invisibility " + element);
                }
            }
            assumeFalse(isElementDisplayed(element), "Element is displayed on the page, but should not!");
        }

        public void clickElement (WebElement element){
            try {
                waitAndCheckVisibility(element, true);
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            } catch (ElementClickInterceptedException e) {
                System.out.println("ElementClickInterceptedException occurred while wait.until" +
                        "(ExpectedConditions.elementToBeClickable)" +
                        " {ELEMENT: " + element.toString() + "}");

                if (element.isDisplayed()) {
                    wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                } else {
                    fail("Couldn't click element. It's not displayed " + element.toString());
                }
            } catch (ElementNotInteractableException e) {
                System.out.println("ElementNotInteractableException occurred while wait.until" +
                        "(ExpectedConditions.elementToBeClickable)" +
                        " {ELEMENT: " + element.toString() + "}");
                driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
                if (element.isDisplayed()) {
                    element.click();
                } else {
                    fail("Couldn't click element. It's not displayed " + element.toString());
                }

            }
        }

        public void waitAndCheckVisibility (WebElement element, Boolean expectedVisibility){
            if (expectedVisibility) {
                wait.until(ExpectedConditions.visibilityOf(element));
                assertTrue(element.isDisplayed(), "Element should be displayed " + element);
            } else {
                wait.until(ExpectedConditions.invisibilityOf(element));
                checkElementIsNotDisplayed(element);
            }

        }


    }


