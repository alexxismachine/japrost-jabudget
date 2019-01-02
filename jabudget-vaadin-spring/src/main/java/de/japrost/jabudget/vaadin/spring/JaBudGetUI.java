package de.japrost.jabudget.vaadin.spring;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.router.Route;

import de.japrost.jabudget.domain.DomainException;
import de.japrost.jabudget.domain.account.Account;
import de.japrost.jabudget.service.AccountService;
import de.japrost.jabudget.service.SerializationService;

/**
 * Main UI for JaBudget.
 */
// this is a hack just to test main functionality
@Route("")
public class JaBudGetUI extends HorizontalLayout {

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
		setSizeFull();
		add(initAccountOverview());
		add(initAccountForm());
		add(initSerialization());
	}

	private VerticalLayout initAccountOverview() {
		final VerticalLayout layout = new VerticalLayout();
		final Grid<Account> accounts = new Grid<>(Account.class);
		// accounts.setCaption("Accounts"); // I18N?
		accounts.setColumns("id", "name");
		accountData = new ListDataProvider<>(accountService.retrieveAll());
		accounts.setDataProvider(accountData);
		accounts.addSelectionListener(this::itemClicked);
		layout.add(accounts);
		return layout;
	}

	private void itemClicked(final SelectionEvent<Grid<Account>, Account> e) {
		selectedAccount = e.getFirstSelectedItem().get();
		newId.setValue(selectedAccount.getId());
		newName.setValue(selectedAccount.getName());
		newAccount.setText("Update");
		delete.setEnabled(true);
	}

	private FormLayout initAccountForm() {
		final FormLayout newAccountLayout = new FormLayout();
		newId = new TextField();
		newId.setLabel("id");
		newId.setRequiredIndicatorVisible(true);
		newId.addValueChangeListener(this::newIdValueChanged);
		newAccountLayout.add(newId);
		newName = new TextField();
		newName.setLabel("name");
		newAccountLayout.add(newName);
		newAccount = new Button();
		newAccount.setText("New");
		newAccount.setEnabled(false);
		newAccount.addClickListener(this::createNewAccount);
		newAccountLayout.add(newAccount);
		clear = new Button();
		clear.setText("Clear");
		clear.setEnabled(false);
		clear.addClickListener(this::clearAccount);
		newAccountLayout.add(clear);
		delete = new Button();
		delete.setText("Delete");
		delete.setEnabled(false);
		delete.addClickListener(this::deleteAccount);
		newAccountLayout.add(delete);
		return newAccountLayout;
	}

	private void clearAccount(final ClickEvent<Button> event) {
		newId.clear();
		newName.clear();
		newAccount.setText("New");
	}

	private void deleteAccount(final ClickEvent<Button> event) {
		final Boolean erased = accountService.erase(newId.getValue());
		if (erased) {
			accountData.getItems().remove(selectedAccount);
			accountData.refreshAll();
			// clear after remove since the newIdValueChanged is faster and sets selectedAccount to null
			newId.clear();
			newName.clear();
			newAccount.setText("New");
		}
	}

	private void createNewAccount(final ClickEvent<Button> event) {
		Account account;
		try {
			// this is a hack just to test main functionality
			if (newAccount.getText().equals("New")) {
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
		newAccount.setText("New");
		accountData.refreshAll();
	}

	private void newIdValueChanged(final HasValue.ValueChangeEvent<String> event) {
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
		impoort.setText("Import");

		impoort.addClickListener(e -> {
			serializationService.deserialize();
			reloadAccounts();
		});
		layout.add(impoort);
		export = new Button();
		export.setText("Export");
		export.addClickListener(e -> serializationService.serialize());
		layout.add(export);
		return layout;
	}

	private void reloadAccounts() {
		accountData.getItems().clear();
		accountData.getItems().addAll(accountService.retrieveAll());
		accountData.refreshAll();
	}
}
