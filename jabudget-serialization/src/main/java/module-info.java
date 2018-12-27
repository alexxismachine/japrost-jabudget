module de.japrost.jabudget.serialization {
    exports de.japrost.jabudget.serialization;
    // TODO do not export implementations?
    exports de.japrost.jabudget.serialization.xstream;

    requires de.japrost.jabudget.domain;
    requires xstream;
}
