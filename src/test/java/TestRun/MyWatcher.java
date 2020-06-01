package TestRun;

import java.util.function.Function;
import java.util.stream.Collectors;

import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.*;

import java.util.Optional;


public class MyWatcher extends BaseClass implements TestWatcher/*, AfterAllCallback*/ {

//    public static WebDriver driver;

//    private static final Logger logger = LogManager.getLogger("pom");

//    private List<TestResultStatus> testResultsStatus = new ArrayList<>();
//
//    private enum TestResultStatus {
//        SUCCESSFUL, ABORTED, FAILED, DISABLED;
//    }
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        logger.info("test disabled for {}", context.getDisplayName());

//        testResultsStatus.add(TestResultStatus.DISABLED);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        logger.info("test {} successful", context.getDisplayName());
        System.out.println("Watcher says: Successful!");
        screenShot();
//        testResultsStatus.add(TestResultStatus.SUCCESSFUL);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        logger.info("test {} aborted", context.getDisplayName());
        screenShot();
//        testResultsStatus.add(TestResultStatus.ABORTED);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.info("test {} failed", context.getDisplayName());
        screenShot();
        System.out.println("Watcher says: Failed!");
//        testResultsStatus.add(TestResultStatus.FAILED);

    }

//    @Override
//    public void afterAll(ExtensionContext extensionContext) throws Exception {
//        Map<TestResultStatus, Long> summary = testResultsStatus.stream()
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenShot(byte[] screenShot) {
        return screenShot;
    }

    public void screenShot() {
        if(driver == null) {
            logger.info("Driver for screenshot not found");
            System.out.println("Driver for screenshot not found");
            return;
        }
        saveScreenShot(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
    }
}
