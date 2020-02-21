package brightpod;

import core.WebDriverManager;

public abstract class BasePage {

    public abstract void executeAction();
    public void quit() {
        WebDriverManager.getInstance().quitDriver();
    }
}
