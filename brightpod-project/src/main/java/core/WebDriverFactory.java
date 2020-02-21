package core;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    public WebDriver getWebDriver(String browserName) {
        Map<String, IDriver> mapDrivers = new HashMap<>();
        mapDrivers.put("Chrome", new Chrome());
        mapDrivers.put("Firefox", new Firefox());
        return mapDrivers.get(browserName).initDriver();
    }
}
