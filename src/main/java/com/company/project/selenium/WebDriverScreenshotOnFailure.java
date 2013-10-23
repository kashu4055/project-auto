package com.company.project.selenium;

import java.io.File;
import java.text.MessageFormat;
import java.util.UUID;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterScenario.Outcome;
import org.jbehave.core.annotations.ScenarioType;
import org.jbehave.core.failures.PendingStepFound;
import org.jbehave.core.failures.UUIDExceptionWrapper;
import org.jbehave.core.reporters.StoryReporterBuilder;

/**
 * WebDriverSteps that save screenshot upon failure in a scenario outcome.
 * Not all WebDriver implementations support the screenshot capability
 */
public class WebDriverScreenshotOnFailure extends WebDriverPage {

    public static final String DEFAULT_SCREENSHOT_PATH_PATTERN = "{0}/screenshots/failed-scenario-{1}.png";
    protected final StoryReporterBuilder reporterBuilder;
    protected final String screenshotPathPattern;

    public WebDriverScreenshotOnFailure(WebDriverManager driverManager) {
        this(driverManager, new StoryReporterBuilder());
    }

    public WebDriverScreenshotOnFailure(WebDriverManager driverManager, StoryReporterBuilder reporterBuilder) {
        this(driverManager, reporterBuilder, DEFAULT_SCREENSHOT_PATH_PATTERN);
    }

    public WebDriverScreenshotOnFailure(WebDriverManager driverManager, StoryReporterBuilder reporterBuilder, String screenshotPathPattern) {
        super(driverManager);
        this.reporterBuilder = reporterBuilder;
        this.screenshotPathPattern = screenshotPathPattern;
    }

    @AfterScenario(uponOutcome = Outcome.FAILURE, uponType = ScenarioType.NORMAL)
    public void afterNormalScenarioFailure(UUIDExceptionWrapper uuidWrappedFailure) throws Exception {
    	afterScenarioFailure(uuidWrappedFailure);
    }
    
    @AfterScenario(uponOutcome = Outcome.FAILURE, uponType = ScenarioType.EXAMPLE)
    public void afterExampleScenarioFailure(UUIDExceptionWrapper uuidWrappedFailure) throws Exception {
    	afterScenarioFailure(uuidWrappedFailure);
    }
    
    public void afterScenarioFailure(UUIDExceptionWrapper uuidWrappedFailure) throws Exception {
        if (uuidWrappedFailure instanceof PendingStepFound) {
            return; // we don't take screen-shots for Pending Steps
        }
        String screenshotPath = screenshotPath(uuidWrappedFailure.getUUID());
        String currentUrl = "[unknown page title]";
        try {
            currentUrl = getWebDriver().getCurrentUrl();
        } catch (Exception e) {
        }
        
        boolean savedIt = false;
        try {
            savedIt = saveScreenshotTo(screenshotPath);
        } catch (Exception e) {
            System.err.println("Screenshot of page '" + currentUrl + "' has **NOT** been saved to '" + screenshotPath + "' because error '" + e.getMessage() + "' encountered. Stack trace follows:");
            e.printStackTrace();
            return;
        }
        
        if (savedIt) {
            System.out.println("Screenshot of page '" + currentUrl + "' has been saved to '" + screenshotPath +"' with " + new File(screenshotPath).length() + " bytes");
        } else {
            System.err.println("Screenshot of page '" + currentUrl + "' has **NOT** been saved. If there is no error, perhaps the WebDriver type you are using is not compatible with taking screenshots");
        }
    }

    protected String screenshotPath(UUID uuid) {
        return MessageFormat.format(screenshotPathPattern, reporterBuilder.outputDirectory(), uuid);
    }

}
