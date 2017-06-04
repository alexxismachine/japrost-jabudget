package de.japrost.jabudget.vaadin.spring;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import de.japrost.jabudget.service.AccountService;

@SpringUI
public class JaBudGetUI extends UI {

	private final AccountService accountService;

	public JaBudGetUI(final AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	protected void init(final VaadinRequest request) {
		setContent(new Label(accountService.retrieveById("Test Account").getId()));
	}

}
