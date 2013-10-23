package com.company.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.company.project.selenium.WebDriverManager;
import com.company.project.selenium.WebDriverPage;

public class GooglePage extends WebDriverPage {
	
	private static String GOOGLE_URL = "http://www.google.com.br";
    private static By CAMPO_PESQUISA = By.name("q");
    private static By BOTAO_PESQUISA = By.name("btnG");
    private static By DIV_PRINCIPAL_APOS_PESQUISA = By.id("main");
    
	public GooglePage(final WebDriverManager webDriverManager) {
		super(webDriverManager);
	}
	
	public GooglePage acessarPagina() {
		getWebDriver().get(GOOGLE_URL);
		return this;
	}
	
	public GooglePage pesquisar(final String texto) {
        preencherCampoPesquisa(texto);
        clicarBotaoPesquisa();
		return this;
	}

    public void clicarBotaoPesquisa() {
        waitForElementVisible(BOTAO_PESQUISA).click();
        waitForElementVisible(DIV_PRINCIPAL_APOS_PESQUISA);
    }
    
    public void preencherCampoPesquisa(final String texto) {
        WebElement campoPesquisa = waitForElementVisible(CAMPO_PESQUISA);
        campoPesquisa.clear();
        campoPesquisa.sendKeys(texto);
        campoPesquisa.sendKeys(Keys.ENTER);
    }

}