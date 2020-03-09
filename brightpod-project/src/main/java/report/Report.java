package report;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates reports of scenarios.
 */
public class Report {

    /**
     * Allows to get report instance.
     * @return report instance.
     */
    public static Report getInstance() {
        return new Report();
    }

    /**
     * Generates reports.
     */
    public void generateReport() {
        final File reportOutputDirectory = new File("target");
        final List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber.json");
        final String projectName = "BRIGHTPOD-PROJECT";
        final boolean runWithJenkins = false;
        final Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.addClassifications("Platform", "WINDOWS");
        configuration.addClassifications("Branch", "RELEASE/1.0");
        final ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        final Reportable result = reportBuilder.generateReports();
    }
}
