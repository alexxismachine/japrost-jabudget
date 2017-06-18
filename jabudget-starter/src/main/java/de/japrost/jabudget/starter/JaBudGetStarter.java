package de.japrost.jabudget.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = { de.japrost.jabudget.starter.JaBudGetConfig.class,
		de.japrost.jabudget.vaadin.spring.JaBudGetUI.class, de.japrost.jabudget.spring.AccountController.class })
public class JaBudGetStarter {

	public static void main(final String[] args) {
		SpringApplication.run(JaBudGetStarter.class, args);
	}
}
