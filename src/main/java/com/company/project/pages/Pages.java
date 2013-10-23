package com.company.project.pages;

import com.company.project.selenium.WebDriverManager;


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
