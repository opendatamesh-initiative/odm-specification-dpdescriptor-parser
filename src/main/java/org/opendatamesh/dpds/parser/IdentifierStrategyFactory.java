package org.opendatamesh.dpds.parser;

/**
 * Factory class for creating instances of {@link IdentifierStrategy}.
 * This class provides methods to obtain the default identifier strategy
 * or a customized one based on the specified organization.
 */
public abstract class IdentifierStrategyFactory {

    /**
     * Private constructor to prevent instantiation of the factory class.
     */
    private IdentifierStrategyFactory() {
        // Prevent instantiation
    }

    /**
     * Returns the default identifier strategy with the organization set to "org.opendatamesh".
     *
     * @return the default {@link IdentifierStrategy} instance
     */
    public static IdentifierStrategy getDefault() {
        return new IdentifierStrategyDefault("org.opendatamesh");
    }

    /**
     * Returns the default identifier strategy customized with the specified organization.
     *
     * @param organization the organization to be used in the identifier strategy
     * @return the customized {@link IdentifierStrategy} instance
     */
    public static IdentifierStrategy getDefault(String organization) {
        return new IdentifierStrategyDefault(organization);
    }
}

