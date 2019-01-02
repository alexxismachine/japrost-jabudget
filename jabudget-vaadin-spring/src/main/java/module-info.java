module de.japrost.jabudget.vaadin.spring {
    exports de.japrost.jabudget.vaadin.spring;

    requires flow.data;
    requires flow.server;
    requires java.sql;
    requires spring.context;
    requires vaadin.button.flow;
    requires vaadin.core;
    requires vaadin.form.layout.flow;
    requires vaadin.grid.flow;
    requires vaadin.notification.flow;
    requires vaadin.ordered.layout.flow;
    requires vaadin.spring;
    requires vaadin.text.field.flow;
    requires de.japrost.jabudget.domain;
    requires de.japrost.jabudget.service;

}