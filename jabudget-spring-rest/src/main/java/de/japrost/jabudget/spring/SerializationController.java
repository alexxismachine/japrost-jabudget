package de.japrost.jabudget.spring;

import static de.japrost.jabudget.spring.PathMapping.BASE;
import static de.japrost.jabudget.spring.PathMapping.SERIALIZATION_DESERIALIZE;
import static de.japrost.jabudget.spring.PathMapping.SERIALIZATION_SERIALIZE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.japrost.jabudget.service.SerializationService;

/**
 * Controller to expose serialization.
 */
@RestController
@RequestMapping(BASE)
public class SerializationController {

	private final SerializationService serializationService;

	/**
	 * Instantiate with necessary dependencies.
	 * 
	 * @param serializationService the SerializationService to use.
	 */
	public SerializationController(SerializationService serializationService) {
		this.serializationService = serializationService;
	}

	/**
	 * Serialize to the configured default serialization store.
	 */
	@GetMapping(SERIALIZATION_SERIALIZE)
	public void defaultSerialize() {
		serializationService.serialize();
	}

	/**
	 * Deserialize from the configured default serialization store.
	 */
	// TODO GET is not the right verb since deserialization changes the whole system.
	@GetMapping(SERIALIZATION_DESERIALIZE)
	public void defaultDeserialize() {
		serializationService.deserialize();
	}
}
