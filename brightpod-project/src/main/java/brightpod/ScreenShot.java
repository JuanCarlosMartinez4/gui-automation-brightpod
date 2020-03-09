package brightpod;

import core.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenShot {
    private static final String FILE_PATH = "screenshot/";
    private static final String FILE_EXTENSION = ".jpg";
    public static void captureScreenShot(final String ScreenShotName) {
        WebDriver webDriver = WebDriverManager.getInstance().getWebDriver();
        int iterator = 0;
        iterator = iterator + 1;
        String name = "ScreenShot";
        String nameIterator = name + String.valueOf(iterator);
        try {
            File screenshot=((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot,new File(FILE_PATH + nameIterator + ScreenShotName + FILE_EXTENSION));
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
