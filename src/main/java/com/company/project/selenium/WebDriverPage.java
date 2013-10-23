package com.company.project.selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverPage {
	
	private WebDriverManager webDriverManager;
	
	public WebDriverPage(final WebDriverManager webDriverManager) {
		this.webDriverManager = webDriverManager;
	}

	/**
	 * Método adaptado de DelegatingWebDriverProvider#saveScreenshotTo
	 * @param path
	 * @return
	 */
	protected boolean saveScreenshotTo(String path) {
		WebDriver driver = getWebDriver();
		if (driver instanceof TakesScreenshot) {
			try {
				File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File destFile = new File(path);
				FileUtils.copyFile(srcFile, destFile);
			} catch (IOException e) {
				throw new RuntimeException("Can't save file", e);
			}
			return true;
		}
		return false;
	}
	
	protected WebDriver getWebDriver() {
		return webDriverManager.getWebDriver();
	}
	
	protected WebElement waitForElementVisible(final By locator) {
        final WebDriverWait wait = new WebDriverWait(getWebDriver(), 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}