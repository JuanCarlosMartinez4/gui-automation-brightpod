package brightpod;

import core.WebDriverManager;

public class PageTransporter {

    public static void goToUrl(String pageUrl) {
        String url = "https://app.brightpod.com" + pageUrl;
        WebDriverManager.getInstance().getWebDriver().navigate().to(url);
    }
}
