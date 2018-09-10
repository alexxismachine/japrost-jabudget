package de.japrost.jabudget.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import de.japrost.jabudget.spring.config.SpringRestConfiguration;

/**
 * Spring-Boot main-class to start JaBudget with Vaadin-UI, Spring-REST controller using a in-memory repository.
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = { de.japrost.jabudget.starter.JaBudGetConfig.class,
		de.japrost.jabudget.vaadin.spring.JaBudGetUI.class })
@Import({ SpringRestConfiguration.class })
public class JaBudGetStarter {

	/**
	 * Start the Spring-Boot application.
	 *
	 * @param args directly passed to Spring-Boot.
	 */
	public static void main(final String[] args) {
		SpringApplication.run(JaBudGetStarter.class, args);
	}
}
