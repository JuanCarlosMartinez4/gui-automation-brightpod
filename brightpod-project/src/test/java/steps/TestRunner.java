package steps;

import core.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;

public class TestRunner extends AbstractTestNGCucumberTests {

    @Before
    public void beforeExecution() {
        System.out.println("I am in before");
    }

    @After
    public void afterExecution() {
        System.out.println("I am in after");
        WebDriverManager.getInstance().quitDriver();
    }
}
