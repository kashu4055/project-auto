package com.company.project.selenium;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.web.selenium.PropertyWebDriverProvider;
import org.jbehave.web.selenium.RemoteWebDriverProvider;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * <p>
 * Fonte utilizado no evento <a href="http://www.thedevelopersconference.com.br/#portoalegre">TDC2013</a> realizado em Porto Alegre
 * para a palestra <a href="http://prezi.com/wter-r9fgvqz/?utm_campaign=share&utm_medium=copy&rc=ex0share">"Execução de testes em paralelo com JBehave e Selenium Grid"</a></b>.
 * </p>
 * 
 * @author Marcelo Tocchetto
 */
public class WebDriverManager {
	
	private enum WebDriverMode { LOCAL, REMOTE };
	private static final String WEBDRIVER_MODE = "webdriver.mode";
	private static final String BROWSER_VERSION = "browser.version";
	private static WebDriverProvider driverProvider;
	private static final ThreadLocal<WebDriver> instances = new ThreadLocal<WebDriver>();

    @BeforeStories
    public synchronized void beforeStories() {
        driverProvider = getDriverProvider();
    }

    public WebDriver getWebDriver() {
        WebDriver webDriver = instances.get();
        if (webDriver == null) {
            webDriver = createWebDriver();
            instances.set(webDriver);
        }
        return webDriver;
    }

    @AfterStory
    public synchronized void kill() {
        driverProvider.end();
        instances.set(null);
    }

    protected WebDriver createWebDriver() {
        driverProvider.initialize();
        return driverProvider.get();
    }

    protected synchronized WebDriverProvider getDriverProvider() {
        if (driverProvider == null) {
        	String mode = System.getProperty(WEBDRIVER_MODE, WebDriverMode.LOCAL.name());
        	switch (WebDriverMode.valueOf(mode)) {
			case LOCAL:
				driverProvider = new PropertyWebDriverProvider();
				break;
			case REMOTE:
				// A URL para o HUB do selenium grid deve ser informada na propriedade de sistema REMOTE_WEBDRIVER_URL
				// Ex.: http://localhost:4444/wd/hub
				DesiredCapabilities desiredCapabilities = makeDesiredCapabilities();
				driverProvider = new RemoteWebDriverProvider(desiredCapabilities);
				break;
			default:
				break;
			}
        }
        return driverProvider;
    }
    
    /**
     * Para mais detalhes sobre DesiredCapabilities veja a <a href="https://code.google.com/p/selenium/wiki/DesiredCapabilities">documentação</a>
     * disponível no site do selenium.
     */
    protected DesiredCapabilities makeDesiredCapabilities() {
    	String browserVersion = System.getProperty(BROWSER_VERSION, "15.0.1");
    	
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        desiredCapabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        desiredCapabilities.setVersion(browserVersion);
        return desiredCapabilities;
    }    

}