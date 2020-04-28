package Pages;

import TestRun.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyWorkroom extends BaseClass {


    private static final By PERSONAL_MENU_LOCATOR = By.cssSelector("div.header2-menu__icon");
    private static final By MY_PROFILE_LOCATOR = By.cssSelector("b.header2-menu__dropdown-text_name");
    Actions hover = new Actions(driver);
    WebDriverWait wait = new WebDriverWait(driver, 20);

    public void openMyProfile(){
        wait.until(ExpectedConditions.elementToBeClickable(PERSONAL_MENU_LOCATOR));
        hover.moveToElement(driver.findElement(PERSONAL_MENU_LOCATOR)).perform();
        hover.moveToElement(driver.findElement(MY_PROFILE_LOCATOR)).click().build().perform();

    }
}
