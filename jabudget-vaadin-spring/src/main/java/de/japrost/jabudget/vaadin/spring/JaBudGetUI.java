package de.japrost.jabudget.vaadin.spring;

import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.service.AccountService;
import de.japrost.jabudget.service.SerializationService;

/**
 * Main UI for JaBudget.
 */
// this is a hack just to test main functionality
@SpringUI()
public class JaBudGetUI extends UI {

	private static final long serialVersionUID = 1L;

	// Account
	private final transient AccountService accountService;
	private ListDataProvider<Account> accountData;
	private Account selectedAccount;
	private Button newAccount;
	private Button clear;
	private Button delete;
	private TextField newId;
	private TextField newName;
	// Serialization
	private final transient SerializationService serializationService;
	private Button export;
	private Button impoort;

	/**
	 * Instantiate with all dependencies.
	 *
	 * @param accountService the {@link AccountService} to use.
	 * @param serializationService the {@link SerializationService} to use.
	 */
	public JaBudGetUI(final AccountService accountService, final SerializationService serializationService) {
		this.accountService = accountService;
		this.serializationService = serializationService;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * <strong>This implementation</strong> initializes all there currently is available (a SPA with a single page ;-).
	 */
	@Override
	protected void init(final VaadinRequest request) {
		final HorizontalLayout mainLayout = new HorizontalLayout();
		this.setContent(mainLayout);
		mainLayout.setSizeFull();
		mainLayout.addComponent(initAccountOverview());
		mainLayout.addComponent(initAccountForm());
		mainLayout.addComponent(initSerialization());
	}

	private VerticalLayout initAccountOverview() {
		final VerticalLayout layout = new VerticalLayout();
		final Grid<Account> accounts = new Grid<>(Account.class);
		accounts.setCaption("Accounts"); // I18N?
		accounts.setColumns("id", "name");
		accountData = new ListDataProvider<>(accountService.retrieveAll());
		accounts.setDataProvider(accountData);
		accounts.addItemClickListener(this::itemClicked);
		layout.addComponent(accounts);
		return layout;
	}

	private void itemClicked(final ItemClick<Account> e) {
		selectedAccount = e.getItem();
		newId.setValue(selectedAccount.getId());
		newName.setValue(selectedAccount.getName());
		newAccount.setCaption("Update");
		delete.setEnabled(true);
	}

	private FormLayout initAccountForm() {
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
		clear = new Button();
		clear.setCaption("Clear");
		clear.setEnabled(false);
		clear.addClickListener(this::clearAccount);
		newAccountLayout.addComponent(clear);
		delete = new Button();
		delete.setCaption("Delete");
		delete.setEnabled(false);
		delete.addClickListener(this::deleteAccount);
		newAccountLayout.addComponent(delete);
		return newAccountLayout;
	}

	private void clearAccount(final Button.ClickEvent event) {
		newId.clear();
		newName.clear();
		newAccount.setCaption("New");
	}

	private void deleteAccount(final Button.ClickEvent event) {
		final Boolean erased = accountService.erase(newId.getValue());
		if (erased) {
			accountData.getItems().remove(selectedAccount);
			accountData.refreshAll();
			// clear after remove since the newIdValueChanged is faster and sets selectedAccount to null
			newId.clear();
			newName.clear();
			newAccount.setCaption("New");
		}
	}

	private void createNewAccount(final Button.ClickEvent event) {
		Account account;
		try {
			// this is a hack just to test main functionality
			if (newAccount.getCaption().equals("New")) {
				account = accountService.create(Account.Builder.builder(newId.getValue()).setName(newName.getValue()).build());
				accountData.getItems().add(account);
			} else {
				account = accountService.update(Account.Builder.builder(newId.getValue()).setName(newName.getValue()).build());
				accountData.getItems().remove(account);
				accountData.getItems().add(account);
			}
		} catch (final DomainException e) {
			// FIXME show error message
			return;
		}
		newId.clear();
		newName.clear();
		newAccount.setCaption("New");
		accountData.refreshAll();
	}

	private void newIdValueChanged(final ValueChangeEvent<String> event) {
		if (event.getValue().length() == 0) {
			newAccount.setEnabled(false);
			clear.setEnabled(false);
			delete.setEnabled(false);
			selectedAccount = null;
		} else {
			newAccount.setEnabled(true);
			clear.setEnabled(true);
		}
	}

	private VerticalLayout initSerialization() {
		final VerticalLayout layout = new VerticalLayout();
		impoort = new Button();
		impoort.setCaption("Import");
		impoort.addClickListener(e -> {
			serializationService.deserialize();
			reloadAccounts();
		});
		layout.addComponent(impoort);
		export = new Button();
		export.setCaption("Export");
		export.addClickListener(e -> serializationService.serialize());
		layout.addComponent(export);
		return layout;
	}

	private void reloadAccounts() {
		accountData.getItems().clear();
		accountData.getItems().addAll(accountService.retrieveAll());
		accountData.refreshAll();
	}
}
