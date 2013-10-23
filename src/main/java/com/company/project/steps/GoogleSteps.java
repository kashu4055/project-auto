package com.company.project.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;

import com.company.project.pages.Pages;

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