package com.company.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.company.project.selenium.WebDriverManager;
import com.company.project.selenium.WebDriverPage;

/**
 * <p>
 * Classe gerada/adaptada a partir do <a href="http://jbehave.org/reference/stable/archetypes.html">jbehave-simple-archetype</a>.<br/>
 * Fonte utilizado no evento <a href="http://www.thedevelopersconference.com.br/#portoalegre">TDC2013</a> realizado em Porto Alegre
 * para a palestra <a href="http://prezi.com/wter-r9fgvqz/?utm_campaign=share&utm_medium=copy&rc=ex0share">"Execução de testes em paralelo com JBehave e Selenium Grid"</a></b>.
 * </p>
 * 
 * @author Marcelo Tocchetto  
 */
public class BingPage extends WebDriverPage {

	private static String BING_URL = "http://br.bing.com";
    private static By CAMPO_PESQUISA = By.id("sb_form_q");
    private static By BOTAO_PESQUISA = By.id("sb_form_go");
    private static By DIV_PRINCIPAL_APOS_PESQUISA = By.id("sw_main");

    public BingPage(final WebDriverManager webDriverManager) {
    	super(webDriverManager);
    }

    public BingPage acessarPagina() {
        getWebDriver().get(BING_URL);
        return this;
    }

    public void clicarBotaoPesquisa() {
    	waitForElementVisible(BOTAO_PESQUISA).click();
        waitForElementVisible(DIV_PRINCIPAL_APOS_PESQUISA);
    }

    public void pesquisar(final String texto) {
        preencherCampoPesquisa(texto);
        clicarBotaoPesquisa();
    }

    public void preencherCampoPesquisa(final String texto) {
        WebElement campoPesquisa = waitForElementVisible(CAMPO_PESQUISA);
        campoPesquisa.clear();
        campoPesquisa.sendKeys(texto);
        campoPesquisa.sendKeys(Keys.ENTER);
    }

}