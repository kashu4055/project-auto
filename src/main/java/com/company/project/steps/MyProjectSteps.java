package com.company.project.steps;

import com.company.project.pages.Pages;

public abstract class MyProjectSteps {

	private Pages pages;

	public MyProjectSteps(final Pages pages) {
		this.pages = pages;
	}
	
	protected Pages getPages() {
		return pages;
	}
	
}
