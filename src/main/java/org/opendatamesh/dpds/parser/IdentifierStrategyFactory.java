package org.opendatamesh.dpds.parser;

public abstract class IdentifierStrategyFactory {
    private IdentifierStrategyFactory() {

    }

    public static IdentifierStrategy getDefault(String organization) {
        return new IdentifierStrategyDefault(organization);
    }
}
