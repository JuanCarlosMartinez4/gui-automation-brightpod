package com.brightpod.selectsite.runner;

import core.WebDriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import report.Report;

public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeTest
    public void beforeExecution() {
    }

    @AfterTest
    public void afterExecution() {
        WebDriverManager.getInstance().quitDriver();
        Report.getInstance().generateReport();
    }
}
