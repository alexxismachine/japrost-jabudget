package de.japrost.jabudget.spring;

/**
 * Paths for the REST-Mapping
 */
// TODO no constants interface?
public interface PathMapping {
static final  String ID_PARAM = "id";
	static final  String ID_PATH = "/{"+ID_PARAM+"}";

	/** Base path for rest calls */
	static final String BASE = "/rest";

	/** Base path for Account handling */
	static final String ACCOUNTS = "/accounts";
	/** Path for Account entities */
	static final String ACCOUNTS_ID = ACCOUNTS + ID_PATH;

	/** Base path for Account-Entry entities */
	static final String ACCOUNTS_ENTRIES = ACCOUNTS_ID + "/entries";

	/** Base path for serialization features */
	static final String SERIALIZATION = "/impexp";
	/** Path for serialization */
	static final String SERIALIZATION_SERIALIZE = SERIALIZATION + "/ser";
	/** Path for deserialization */
	static final String SERIALIZATION_DESERIALIZE = SERIALIZATION + "/des";


}
