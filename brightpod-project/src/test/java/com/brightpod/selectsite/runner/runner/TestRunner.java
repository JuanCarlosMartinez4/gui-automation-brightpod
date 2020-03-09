package com.brightpod.selectsite.runner.runner;

import core.WebDriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import report.Report;

public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeTest
    public void beforeExecution() {
        System.out.println("I am in before Test");
    }

    @AfterTest
    public void afterExecution() {
        System.out.println("I am in after Test");
        WebDriverManager.getInstance().quitDriver();
        Report.getInstance().generateReport();
    }
}
