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
public class GoogleSteps extends MyProjectSteps {

    public GoogleSteps(Pages pages) {
		super(pages);
	}

	@Given("ao acessar o site do google")
    public void acessarPagina() {
		getPages().getGooglePage().acessarPagina();
    }

    @When("pesquisar no google por $texto")
    public void pesquisar(@Named("texto") String texto) {
    	getPages().getGooglePage().pesquisar(texto);
    }

}