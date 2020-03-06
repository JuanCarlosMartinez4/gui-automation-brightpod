package runner;

import core.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestRunner extends AbstractTestNGCucumberTests {

    @Before

    @BeforeTest
    public void beforeExecution() {
        System.out.println("I am in before");
    }

    @AfterTest
    public void afterExecution() {
        System.out.println("I am in after");
        WebDriverManager.getInstance().quitDriver();
    }
}
