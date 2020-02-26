package Pages;

import TestRun.BaseClass;
import org.openqa.selenium.By;

public class LoginPage extends BaseClass {
    private static final By loginButtonLocator = By.cssSelector("span.header2__auth-reg");
    private static final By loginLocator = By.cssSelector("input[class*='js-email-input']");
    private static final By passwordLocator = By.cssSelector("input[class*='js-psw-input']");
    private static final By submitButton = By.cssSelector("div[class='new-input-line new-input-line_last new-input-line_relative']");

    public static void login() {
        driver.get("https://otus.ru/");

        driver.findElement(loginButtonLocator).click();

        driver.findElement(loginLocator).sendKeys("nikolay.antonov@t-systems.com");
        driver.findElement(passwordLocator).sendKeys("2FATexUc!3mQkS2");
        driver.findElement(submitButton).click();

    }
}
