package com.company.project.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;

import com.company.project.pages.Pages;

/**
 * <p>
 * Fonte utilizado no evento <a href="http://www.thedevelopersconference.com.br/#portoalegre">TDC2013</a> realizado em Porto Alegre
 * para a palestra <a href="http://prezi.com/wter-r9fgvqz/?utm_campaign=share&utm_medium=copy&rc=ex0share">"Execução de testes em paralelo com JBehave e Selenium Grid"</a></b>.
 * </p>
 * 
 * @author Marcelo Tocchetto 
 */
public class BingSteps extends MyProjectSteps {

    public BingSteps(Pages pages) {
		super(pages);
	}

	@Given("ao acessar o site do bing")
    public void acessarPagina() {
		getPages().getBingPage().acessarPagina();
    }

    @When("pesquisar no bing por $texto")
    public void pesquisar(@Named("texto") String texto) {
       	getPages().getBingPage().pesquisar(texto);
    }

}