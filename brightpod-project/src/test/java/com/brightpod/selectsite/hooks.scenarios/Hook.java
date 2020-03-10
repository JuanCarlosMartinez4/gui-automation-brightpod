package com.brightpod.selectsite.hooks.scenarios;

import brightpod.PageTransporter;
import brightpod.SettingTextLink;
import com.brightpod.selectsite.steps.StepUtil;
import core.WebDriverManager;
import entities.Context;
import entities.Pod;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * Executes actions for scenarios.
 */
public class Hook {

    // Entities
    private Context context;
    private Pod pod;

    // Pages
    private SettingTextLink setting;

    /**
     * Generates instance of Hook.
     * @param context value.
     */
    public Hook(final Context context) {
        this.context = context;
        this.pod = context.getPod();
    }

    @Before("@Pod or @TaskList or @Task")
    public void beforeScenario() {
        String page = "/projects";
        PageTransporter.goToUrl(page);
    }

    @After("@Del")
    public void afterScenario() {
        StepUtil.searchElement(pod.getPodName());
        setting = new SettingTextLink();
        setting = new SettingTextLink();
        setting.archivePod();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot =
                ((TakesScreenshot) WebDriverManager.getInstance().getWebDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png", "testing");
        }
    }
}
