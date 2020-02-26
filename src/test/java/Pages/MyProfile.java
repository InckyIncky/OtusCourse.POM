package Pages;

import TestRun.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyProfile extends BaseClass {
    private static final By FIRSTNAME_LOCATOR = By.id("id_fname");
    private static final By FIRSTNAME_LATIN_LOCATOR = By.id("id_fname_latin");
    private static final By LASTNAME_LOCATOR = By.id("id_lname");
    private static final By LASTNAME_LATIN_LOCATOR = By.id("id_lname_latin");
    private static final By BLOG_NAME_LOCATOR = By.id("id_blog_name");
    private static final By ADD_PHOTO_LOCATOR = By.cssSelector("input[class='file-in-button js-avatar-file']");
    private static final By BIRTH_DATE_LOCATOR = By.name("date_of_birth");
    private static final By ADD_CONTACT_LOCATOR = By.cssSelector("button[class='lk-cv-block__action lk-cv-block__action_md-no-spacing js-formset-add js-lk-cv-custom-select-add']");
    private static final By CONTACT_TYPE_LOCATOR = By.xpath(".//span[text()='Способ связи']");
    private static final By CONTACT_VK = By.cssSelector("button[title='VK']");
    private static final By CONTACT_VALUE = By.id("id_contact-0-value");
    private static final By CONTACT_TELEGA = By.cssSelector("button[title='Telegram']");
    private static final By CONTACT_VALUE2 = By.id("id_contact-1-value");
    private static final By SAVE_CONTINUE = By.cssSelector("button[name='continue']");

    public static void fillData(String firstname, String firstnameLatin, String lastname, String lastnameLatin, String birthDate, String blogName, String pathToPhoto) {
        fillPersonalData(firstname, firstnameLatin, lastname, lastnameLatin, birthDate, blogName, pathToPhoto);
        fillAdditionalContactInfo();

        driver.findElement(SAVE_CONTINUE).click();
    }

    public static void fillPersonalData(String firstname, String firstnameLatin, String lastname, String lastnameLatin, String birthDate, String blogName, String pathToPhoto) {
        driver.findElement(FIRSTNAME_LOCATOR).clear();
        driver.findElement(FIRSTNAME_LOCATOR).sendKeys(firstname);
        driver.findElement(FIRSTNAME_LATIN_LOCATOR).clear();
        driver.findElement(FIRSTNAME_LATIN_LOCATOR).sendKeys(firstnameLatin);
        driver.findElement(LASTNAME_LOCATOR).clear();
        driver.findElement(LASTNAME_LOCATOR).sendKeys(lastname);
        driver.findElement(LASTNAME_LATIN_LOCATOR).sendKeys(lastnameLatin);
        driver.findElement(BLOG_NAME_LOCATOR).clear();
        driver.findElement(BLOG_NAME_LOCATOR).sendKeys(blogName);
        driver.findElement(BIRTH_DATE_LOCATOR).clear();
        driver.findElement(BIRTH_DATE_LOCATOR).sendKeys(birthDate);
        unhide(driver, driver.findElement(ADD_PHOTO_LOCATOR));
        driver.findElement(ADD_PHOTO_LOCATOR).sendKeys(pathToPhoto);
        driver.findElement(By.cssSelector("button[class='button button_blue js-choose-crop']")).click();

    }

    public static void fillAdditionalContactInfo() {
        driver.findElement(CONTACT_TYPE_LOCATOR).click();
        driver.findElement(CONTACT_VK).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(CONTACT_VALUE)).sendKeys("some.vk.com");

//        driver.findElement(ADD_CONTACT_LOCATOR).click();
//        driver.findElement(CONTACT_TYPE_LOCATOR).click();
//        Actions hover = new Actions(driver);
//        hover.moveToElement(driver.findElement(CONTACT_TELEGA)).perform();
//        driver.findElement(CONTACT_TELEGA).click();
//        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(CONTACT_VALUE2)).sendKeys("123456789");

        driver.findElement(SAVE_CONTINUE).click();
    }

    public static void unhide(WebDriver driver, WebElement element) {
        String unhideScript = "arguments[0].style.opacity=1;"
                + "arguments[0].style['transform'] = 'translate(0px, 0px) scale(1)';"
                + "arguments[0].style['MozTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['WebkitTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['msTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['OTransform']='translate(0px, 0px) scale(1)';"
                + "return true;";
        ((JavascriptExecutor) driver).executeScript(unhideScript, element);
    }
}
