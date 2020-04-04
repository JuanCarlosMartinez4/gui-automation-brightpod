package core;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    private static final String CHROME = "Chrome";
    private static final String FIREFOX = "Firefox";
    public static WebDriver getWebDriver(String browserName) {
        Map<String, IDriver> mapDrivers = new HashMap<>();
        mapDrivers.put(CHROME, new Chrome());
        mapDrivers.put(FIREFOX, new Firefox());
        return mapDrivers.get(browserName).initDriver();
    }
}
