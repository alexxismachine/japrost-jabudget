module de.japrost.jabudget.vaadin.spring {
    exports de.japrost.jabudget.vaadin.spring;

    requires vaadin.server;
    requires vaadin.shared;
    requires vaadin.spring;
    requires de.japrost.jabudget.domain;
    requires de.japrost.jabudget.service;
}