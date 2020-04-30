package Pages;

import TestRun.BaseClass;
import appconfigs.AppConfigs;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;

public class LoginPage extends BaseClass {

    AppConfigs cfg = ConfigFactory.create(AppConfigs.class);



    private static final By loginButtonLocator = By.cssSelector("span.header2__auth-reg");
    private static final By loginLocator = By.cssSelector("input[class*='js-email-input']");
    private static final By passwordLocator = By.cssSelector("input[class*='js-psw-input']");
    private static final By submitButton = By.cssSelector("div[class='new-input-line new-input-line_last new-input-line_relative']");

    @Step("login")
    public void login() {
        driver.get("https://otus.ru/");

        driver.findElement(loginButtonLocator).click();

        driver.findElement(loginLocator).sendKeys(cfg.login());
        driver.findElement(passwordLocator).sendKeys(cfg.pw());
        driver.findElement(submitButton).click();

    }
}
