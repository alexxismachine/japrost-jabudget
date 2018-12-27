module de.japrost.jabudget.spring.rest {
    exports de.japrost.jabudget.spring.config;

    requires spring.context;
    requires spring.web;
    requires de.japrost.jabudget.domain;
    requires de.japrost.jabudget.service;
}