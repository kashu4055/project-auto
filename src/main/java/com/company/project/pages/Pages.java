package com.company.project.pages;

import com.company.project.selenium.WebDriverManager;

/**
 * <p>
 * Fonte utilizado no evento <a href="http://www.thedevelopersconference.com.br/#portoalegre">TDC2013</a> realizado em Porto Alegre
 * para a palestra <a href="http://prezi.com/wter-r9fgvqz/?utm_campaign=share&utm_medium=copy&rc=ex0share">"Execução de testes em paralelo com JBehave e Selenium Grid"</a></b>.
 * </p>
 * 
 * @author Marcelo Tocchetto
 */
public class Pages {
	
	private WebDriverManager webDriverManager;
	
	public Pages(final WebDriverManager webDriverManager) {
		this.webDriverManager = webDriverManager; 
	}
	
	public GooglePage getGooglePage() {
		return new GooglePage(webDriverManager);
	}
	
	public BingPage getBingPage() {
		return new BingPage(webDriverManager);
	}

}
