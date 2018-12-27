module de.japrost.jabudget.repository {
    exports de.japrost.jabudget.repository;
	// TODO do not export implementations?
    exports de.japrost.jabudget.repository.inmemory;

	requires de.japrost.jabudget.domain;

}
