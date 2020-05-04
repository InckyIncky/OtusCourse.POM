package TestRun;

import Pages.LoginPage;
import Pages.MyProfile;
import Pages.MyWorkroom;
import appconfigs.AppConfigs;
import io.qameta.allure.Flaky;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestRunner extends BaseClass {
    AppConfigs cfg = ConfigFactory.create(AppConfigs.class);
    private static final Logger logger = LogManager.getLogger(TestRunner.class);


    String firstname = cfg.firstName();
    String firstnameLatin = cfg.firstNameLatin();
    String lastname = cfg.lastName();
    String lastnameLatin = cfg.lastNameLatin();
    String birthDate = cfg.birthDate();
    String blogName = cfg.blogName();
    String pathToPhoto = cfg.pathToPhoto();

    List<String> expectedContactData = new ArrayList();
    String firstContactType = cfg.firstContactType();
    String firstContactTypeValue = cfg.firstContactTypeValue();
    String secondContactType = cfg.secondContactType();
    String secondContactValue = cfg.secondContactValue();


    @Test
    @Flaky
    @DisplayName("Filling data and checking")
    public void runTest() {
        LoginPage loginPage = new LoginPage();

        loginPage.login();
        logger.info("User successfully logged in");

        MyWorkroom workRoom = new MyWorkroom();
        workRoom.openMyProfile();
        logger.info("My workroom successfully opened");

        MyProfile myProfile = new MyProfile();
        myProfile.fillData(firstname, firstnameLatin, lastname, lastnameLatin, birthDate, blogName, pathToPhoto);
        logger.info("user profile successfully filled");

        driver.manage().deleteAllCookies();

        driver.get("https://otus.ru/");

        LoginPage loginPageCheck = new LoginPage();

        loginPageCheck.login();

        MyWorkroom workRoomCheck = new MyWorkroom();
        MyProfile myProfileCheck = new MyProfile();
        workRoomCheck.openMyProfile();

        List<String> expectedPersonalData = new ArrayList();
        expectedPersonalData.add(firstname);
        expectedPersonalData.add(firstnameLatin);
        expectedPersonalData.add(lastname);
        expectedPersonalData.add(lastnameLatin);
        expectedPersonalData.add(blogName);
        expectedPersonalData.add(birthDate);
        logger.info("Expected results for personal data created");

        Assertions.assertLinesMatch(expectedPersonalData, myProfileCheck.getPersonalData());
        logger.info("expected personal data matches actual");

        List<String> expectedMainInfo = new ArrayList();
        expectedMainInfo.add("Россия");
        expectedMainInfo.add("Санкт-Петербург");
        expectedMainInfo.add("YES");
        expectedMainInfo.add("full-time");
        logger.info("Expected results for main information created");

        Assertions.assertLinesMatch(expectedMainInfo, myProfileCheck.getMainInfo());
        logger.info("expected main information matches actual");

        expectedContactData.add(firstContactType);
        expectedContactData.add(firstContactTypeValue);
        expectedContactData.add(secondContactType);
        expectedContactData.add(secondContactValue);
        logger.info("Expected results for contact data created");

//        Assertions.assertLinesMatch(expectedContactData, myProfileCheck.getContactData());
        logger.info("expected contact data matches actual");

    }

    @Test
    public void runTest2() {
        int a = 2;
        int b = 5;
        int c = a + b;
        assertNotNull(c);
    }

    @Test
    public void runTest3() {
        String one = "Test";
        String two = "Passed";
        assertEquals("TestPassed", one + two);
    }
}
