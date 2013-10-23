package com.company.project.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;

import com.company.project.pages.Pages;

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