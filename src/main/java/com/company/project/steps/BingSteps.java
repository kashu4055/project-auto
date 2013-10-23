package com.company.project.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;

public class BingSteps {

    @Given("ao acessar o site do bing")
    public void acessarPagina() {
        // TODO Abrir a página utilizando selenium
    }

    @When("pesquisar no bing por $texto")
    public void pesquisar(@Named("texto") String texto) {
    	// TODO Pesquisar texto no bing utilizando selenium
       	System.out.println("bing thread " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName() + " - texto: " + texto);
    }

}