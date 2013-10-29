package com.company.project.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;

/**
 * <p>
 * Classe gerada/adaptada a partir do <a href="http://jbehave.org/reference/stable/archetypes.html">jbehave-simple-archetype</a>.<br/>
 * Fonte utilizado no evento <a href="http://www.thedevelopersconference.com.br/#portoalegre">TDC2013</a> realizado em Porto Alegre
 * para a palestra <a href="http://prezi.com/wter-r9fgvqz/?utm_campaign=share&utm_medium=copy&rc=ex0share">"Execução de testes em paralelo com JBehave e Selenium Grid"</a></b>.
 * </p>
 * 
 * @author Marcelo Tocchetto 
 */
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