package com.company.project.steps;

import com.company.project.pages.Pages;

/**
 * <p>
 * Fonte utilizado no evento <a href="http://www.thedevelopersconference.com.br/#portoalegre">TDC2013</a> realizado em Porto Alegre
 * para a palestra <a href="http://prezi.com/wter-r9fgvqz/?utm_campaign=share&utm_medium=copy&rc=ex0share">"Execução de testes em paralelo com JBehave e Selenium Grid"</a></b>.
 * </p>
 * 
 * @author Marcelo Tocchetto
 */
public abstract class MyProjectSteps {

	private Pages pages;

	public MyProjectSteps(final Pages pages) {
		this.pages = pages;
	}
	
	protected Pages getPages() {
		return pages;
	}
	
}
