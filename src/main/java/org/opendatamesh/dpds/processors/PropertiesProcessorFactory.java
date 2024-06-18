package org.opendatamesh.dpds.processors;

import org.opendatamesh.dpds.parser.ParseContext;

public class PropertiesProcessorFactory {
    public static PropertiesProcessor getPropertyProcessor(ProcessorType type, ParseContext parseContext) {
        switch (type) {
            case READ_ONLY:
                return new ReadOnlyPropertiesProcessor(parseContext);
            case API_DEFINITION:
                return new ApiDefinitionsProcessor(parseContext);
            case REFERENCES:
                return new ReferencesProcessor(parseContext);
            default:
               throw new RuntimeException("Properties Processor not found");
        }
    }
}
