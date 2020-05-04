package Pages;

import TestRun.BaseClass;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class MyProfile extends BaseClass {
    private static final Logger logger = LogManager.getLogger(MyProfile.class);


    private static final By FIRSTNAME_LOCATOR = By.id("id_fname");
    private static final By FIRSTNAME_LATIN_LOCATOR = By.id("id_fname_latin");
    private static final By LASTNAME_LOCATOR = By.id("id_lname");
    private static final By LASTNAME_LATIN_LOCATOR = By.id("id_lname_latin");
    private static final By BLOG_NAME_LOCATOR = By.id("id_blog_name");
    private static final By ADD_PHOTO_LOCATOR = By.cssSelector("input[class='file-in-button js-avatar-file']");
    private static final By BIRTH_DATE_LOCATOR = By.name("date_of_birth");


    private static final By ADD_COUNTRY = By.cssSelector("div[data-ajax-slave='/lk/biography/cv/lookup/cities/by_country/']");
    private static final By COUNTRY_VALUE = By.cssSelector("button[title='Россия']");
    private static final By ADD_CITY = By.cssSelector("div[class='select lk-cv-block__input lk-cv-block__input_full js-lk-cv-dependent-slave-city js-lk-cv-custom-select']");
    private static final By CITY_VALUE = By.cssSelector("button[title='Санкт-Петербург']");
    private static final By ADD_ENGLISH_LEVEL = By.cssSelector("div[class='select lk-cv-block__input lk-cv-block__input_full js-lk-cv-custom-select']");
    private static final By ENGLISH_LEVEL_VALUE = By.cssSelector("button[title='Средний (Intermediate)']");
    private static final By RELOCATION_YES_RADIO_BUTTON = By.xpath("(//label[@class='radio radio_light4 radio_size-sm radio_vertical-center lk-cv-block__radio'])[2]");
    private static final By RELOCATION_YES_CHECK = By.xpath("//input[@id='id_ready_to_relocate_1']");
    private static final By SET_FULL_DAY = By.xpath("//*[child::input[@title='Полный день']]");
    private static final By CHECK_FULL_DAY = By.xpath("//input[@title='Полный день']");
    private static final By CHECK_PART_TIME = By.xpath("//input[@title='Гибкий график']");
    private static final By CHECK_REMOTE = By.xpath("input[@title='Удаленно']");


    private static final By ADD_CONTACT_LOCATOR = By.cssSelector("button[class='lk-cv-block__action " +
            "lk-cv-block__action_md-no-spacing js-formset-add js-lk-cv-custom-select-add']");
    private static final By CONTACT_TYPE_LOCATOR = By.cssSelector("div[class='select lk-cv-block__input " +
            "lk-cv-block__input_3 lk-cv-block__input_md-4 js-lk-cv-custom-select']");
    private static final By SECOND_CONTACT_TYPE_LOCATOR = By.xpath("(//div[@class='select lk-cv-block__input " +
            "lk-cv-block__input_3 lk-cv-block__input_md-4 js-lk-cv-custom-select'])[2]");
    private static final By CONTACT_VK = By.cssSelector("button[title='VK']");
    private static final By CONTACT_VALUE = By.id("id_contact-0-value");
    private static final By CONTACT_TELEGA = By.cssSelector("div[class='lk-cv-block__select-options lk-cv-block__select-options_left " +
            "js-custom-select-options-container'] > div > button[data-value='telegram']");
    private static final By CONTACT_VALUE2 = By.id("id_contact-1-value");
    private static final By SAVE_CONTINUE = By.cssSelector("button[name='continue']");

    private static final By GET_FIRST_CONTACT_TYPE = By.cssSelector("div[class='input input_full lk-cv-block__input " +
            "input_straight-bottom-right input_straight-top-right input_no-border-right lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation']");

    private static final By GET_SECOND_CONTACT_TYPE = By.xpath("(//div[@class='input input_full lk-cv-block__input input_straight-bottom-right" +
            " input_straight-top-right input_no-border-right lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation'])[2]");

    @FindBy(css = "div[data-ajax-slave='/lk/biography/cv/lookup/cities/by_country/']")
    private static WebElement EL1;

    WebDriverWait wait = new WebDriverWait(driver, 20);
    Actions action = new Actions(driver);

    public void fillData(String firstname, String firstnameLatin, String lastname, String lastnameLatin, String birthDate, String blogName, String pathToPhoto) {
        fillPersonalData(firstname, firstnameLatin, lastname, lastnameLatin, birthDate, blogName, pathToPhoto);

        fillMainInfo();

        fillAdditionalContactInfo();

        driver.findElement(SAVE_CONTINUE).click();
    }

    @Step("Fill personal data")
    public void fillPersonalData(String firstname, String firstnameLatin, String lastname, String lastnameLatin, String birthDate, String blogName, String pathToPhoto) {
        driver.findElement(FIRSTNAME_LOCATOR).clear();
        driver.findElement(FIRSTNAME_LOCATOR).sendKeys(firstname);
        driver.findElement(FIRSTNAME_LATIN_LOCATOR).clear();
        driver.findElement(FIRSTNAME_LATIN_LOCATOR).sendKeys(firstnameLatin);
        driver.findElement(LASTNAME_LOCATOR).clear();
        driver.findElement(LASTNAME_LOCATOR).sendKeys(lastname);
        driver.findElement(LASTNAME_LATIN_LOCATOR).clear();
        driver.findElement(LASTNAME_LATIN_LOCATOR).sendKeys(lastnameLatin);
        driver.findElement(BLOG_NAME_LOCATOR).clear();
        driver.findElement(BLOG_NAME_LOCATOR).sendKeys(blogName);
        driver.findElement(BIRTH_DATE_LOCATOR).clear();
        driver.findElement(BIRTH_DATE_LOCATOR).sendKeys(birthDate);
        unhide(driver, driver.findElement(ADD_PHOTO_LOCATOR));

        driver.findElement(ADD_PHOTO_LOCATOR).sendKeys(pathToPhoto);
        driver.findElement(By.cssSelector("button[class='button button_blue js-choose-crop']")).click();

        logger.info("Personal data filled");

    }

    @Step("Fill main info")
    public void fillMainInfo() {
//        @FindBy(css = "div[data-ajax-slave='/lk/biography/cv/lookup/cities/by_country/']")
//        WebElement el1;
//        clickElement(EL1);
//        clickElement(driver.findElement(COUNTRY_VALUE));
//        clickElement(driver.findElement(ADD_CITY));
//        clickElement(driver.findElement(CITY_VALUE));
//        clickElement(driver.findElement(ADD_ENGLISH_LEVEL));
//        clickElement(driver.findElement(ENGLISH_LEVEL_VALUE));

        driver.findElement(ADD_COUNTRY).click();
        driver.findElement(COUNTRY_VALUE).click();
        driver.findElement(ADD_CITY).click();
        driver.findElement(CITY_VALUE).click();
        driver.findElement(ADD_ENGLISH_LEVEL).click();
        driver.findElement(ENGLISH_LEVEL_VALUE).click();

        if (!driver.findElement(RELOCATION_YES_CHECK).isSelected()) {

            clickElement(driver.findElement(RELOCATION_YES_RADIO_BUTTON));
//        driver.findElement(RELOCATION_YES_RADIO_BUTTON).click();
        }
        if (!driver.findElement(CHECK_FULL_DAY).isSelected()) {

            clickElement(driver.findElement(SET_FULL_DAY));
//            driver.findElement(SET_FULL_DAY).click();
        }

        logger.info("Main info filled");
    }

    @Step("Fill additional info")
    public void fillAdditionalContactInfo() {
//        wait.until(ExpectedConditions.elementToBeClickable(CONTACT_TYPE_LOCATOR));
//        WebElement el = driver.findElement(CONTACT_TYPE_LOCATOR);
//        clickElement(el);
//        clickElement(driver.findElement(CONTACT_VK));
//        clickElement(driver.findElement(CONTACT_VALUE));

        driver.findElement(CONTACT_TYPE_LOCATOR).click();
        driver.findElement(CONTACT_VK).click();
        driver.findElement(CONTACT_VALUE).clear();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(CONTACT_VALUE)).sendKeys("some.vk.com");

//        clickElement(driver.findElement(ADD_CONTACT_LOCATOR));
//        clickElement(driver.findElement(SECOND_CONTACT_TYPE_LOCATOR));

        driver.findElement(ADD_CONTACT_LOCATOR).click();
        driver.findElement(SECOND_CONTACT_TYPE_LOCATOR).click();

        action.moveToElement(driver.findElement(CONTACT_TELEGA)).perform();
//        clickElement(driver.findElement(CONTACT_TELEGA));

        driver.findElement(CONTACT_TELEGA).click();
        driver.findElement(CONTACT_VALUE2).clear();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(CONTACT_VALUE2)).sendKeys("123456789");

        logger.info("Contact data filled");

//        clickElement(driver.findElement(SAVE_CONTINUE));
        driver.findElement(SAVE_CONTINUE).click();

        logger.info("Changes in \"my profile\" saved");
    }

    public void unhide(WebDriver driver, WebElement element) {
        String unhideScript = "arguments[0].style.opacity=1;"
                + "arguments[0].style['transform'] = 'translate(0px, 0px) scale(1)';"
                + "arguments[0].style['MozTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['WebkitTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['msTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['OTransform']='translate(0px, 0px) scale(1)';"
                + "return true;";
        ((JavascriptExecutor) driver).executeScript(unhideScript, element);
    }

    @Step("Get actual results for Personal data")
    public List<String> getPersonalData() {
        List<String> personalDataActual = new ArrayList();

        String nameActual = driver.findElement(FIRSTNAME_LOCATOR).getAttribute("value");
        personalDataActual.add(nameActual);

        String nameLatinActual = driver.findElement(FIRSTNAME_LATIN_LOCATOR).getAttribute("value");
        personalDataActual.add(nameLatinActual);

        String surnameActual = driver.findElement(LASTNAME_LOCATOR).getAttribute("value");
        personalDataActual.add(surnameActual);

        String surnameLatinActual = driver.findElement(LASTNAME_LATIN_LOCATOR).getAttribute("value");
        personalDataActual.add(surnameLatinActual);

        String blogNameActual = driver.findElement(BLOG_NAME_LOCATOR).getAttribute("value");
        personalDataActual.add(blogNameActual);

        String birthDateActual = driver.findElement(BIRTH_DATE_LOCATOR).getAttribute("value");
        personalDataActual.add(birthDateActual);

        logger.info("Actual personal data collected");
        return personalDataActual;
    }

    @Step("Get actual results for Main info")
    public List<String> getMainInfo() {
        List<String> mainInfoActual = new ArrayList();
        String countryActual = driver.findElement(ADD_COUNTRY).getText();
        mainInfoActual.add(countryActual);

        String cityActual = driver.findElement(ADD_CITY).getText();
        mainInfoActual.add(cityActual);

        String relocationActual;
        if (driver.findElement(RELOCATION_YES_CHECK).isSelected()) {
            relocationActual = "YES";
        } else relocationActual = "NO";
        mainInfoActual.add(relocationActual);

        String employmentActual;
        if (driver.findElement(CHECK_FULL_DAY).isSelected()) {
            employmentActual = "full-time";
        } else if (driver.findElement(CHECK_PART_TIME).isSelected()) {
            employmentActual = "part-time";
        } else if (driver.findElement(CHECK_REMOTE).isSelected()) {
            employmentActual = "remote";
        } else employmentActual = "not selected";
        mainInfoActual.add(employmentActual);

        logger.info("Actual main info collected");
        return mainInfoActual;
    }

    @Step("Get actual results for Contact data")
    public List<String> getContactData() {
        List<String> contactDataActual = new ArrayList();

        String firstContactTypeActual = driver.findElement(GET_FIRST_CONTACT_TYPE).getText();
        contactDataActual.add(firstContactTypeActual);
        String firstContactValueActual = driver.findElement(CONTACT_VALUE).getAttribute("value");
        contactDataActual.add(firstContactValueActual);
        String secondContactTypeActual = driver.findElement(GET_SECOND_CONTACT_TYPE).getText();
        contactDataActual.add(secondContactTypeActual);
        String secondContactValueActual = driver.findElement(CONTACT_VALUE2).getAttribute("value");
        contactDataActual.add(secondContactValueActual);

        logger.info("Actual contact data collected");

        return contactDataActual;
    }
}
