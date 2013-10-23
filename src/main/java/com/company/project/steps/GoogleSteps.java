package com.company.project.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;

public class GoogleSteps {

    @Given("ao acessar o site do google")
    public void acessarPagina() {
    	// TODO Abrir a página utilizando selenium
    }

    @When("pesquisar no google por $texto")
    public void pesquisar(@Named("texto") String texto) {
    	// TODO Pesquisar texto no google utilizando selenium
    	System.out.println("google thread " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName() + " - texto: " + texto);
    }

}