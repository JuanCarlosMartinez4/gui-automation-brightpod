package core;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    public static WebDriver getWebDriver(String browserName) {
        Map<String, IDriver> mapDrivers = new HashMap<>();///cambiar a estatico o singleton
        mapDrivers.put("Chrome", new Chrome());
        mapDrivers.put("Firefox", new Firefox());
        return mapDrivers.get(browserName).initDriver();
    }
}
