package de.japrost.jabudget.vaadin.spring;

import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.service.AccountService;

/**
 * Main UI for JaBudget.
 */
@SpringUI()
public class JaBudGetUI extends UI {

	private static final long serialVersionUID = 1L;

	private final transient AccountService accountService;
	private ListDataProvider<Account> accountData;
	private Button newAccount;
	private TextField newId;
	private TextField newName;

	/**
	 * Instaciate with all dependencies.
	 * 
	 * @param accountService the {@link AccountService} to use.
	 */
	public JaBudGetUI(final AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * {@inheritDoc}<p>
	 * <strong>This implementation</strong> initializes all there currently is available (a SPA with a single page ;-).
	 */
	@Override
	protected void init(final VaadinRequest request) {
		final HorizontalLayout mainLayout = new HorizontalLayout();
		this.setContent(mainLayout);
		mainLayout.setSizeFull();
		mainLayout.addComponent(initAccountOverview());
		mainLayout.addComponent(initNewAccountForm());
	}

	private VerticalLayout initAccountOverview() {
		final VerticalLayout layout = new VerticalLayout();
		final Grid<Account> accounts = new Grid<>(Account.class);
		accounts.setCaption("Accounts"); // I18N?
		accounts.setColumns("id", "name");
		accountData = new ListDataProvider<>(accountService.retrieveAll());
		accounts.setDataProvider(accountData);
		layout.addComponent(accounts);
		return layout;
	}

	private FormLayout initNewAccountForm() {
		final FormLayout newAccountLayout = new FormLayout();
		newId = new TextField();
		newId.setCaption("id");
		newId.setRequiredIndicatorVisible(true);
		newId.addValueChangeListener(this::newIdValueChanged);
		newAccountLayout.addComponent(newId);
		newName = new TextField();
		newName.setCaption("name");
		newAccountLayout.addComponent(newName);
		newAccount = new Button();
		newAccount.setCaption("New");
		newAccount.setEnabled(false);
		newAccount.addClickListener(this::createNewAccount);
		newAccountLayout.addComponent(newAccount);
		return newAccountLayout;
	}

	private void createNewAccount(final Button.ClickEvent event) {
		final Account newAccount = accountService
				.create(Account.Builder.builder(newId.getValue()).setName(newName.getValue()).build());
		newId.clear();
		newName.clear();
		accountData.getItems().add(newAccount);
		accountData.refreshAll();
	}

	private void newIdValueChanged(final ValueChangeEvent<String> event) {
		if (event.getValue().length() == 0) {
			newAccount.setEnabled(false);
		} else {
			newAccount.setEnabled(true);
		}
	}
}
