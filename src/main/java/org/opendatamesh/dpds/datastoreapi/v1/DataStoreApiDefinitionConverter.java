package org.opendatamesh.dpds.datastoreapi.v1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApi;
import org.opendatamesh.dpds.datastoreapi.v1.parser.DataStoreApiParser;
import org.opendatamesh.dpds.extensions.DefinitionConverter;

import java.io.IOException;

public class DataStoreApiDefinitionConverter implements DefinitionConverter<DataStoreApi> {

    private static final String SPECIFICATION = "datastoreapi";
    private static final String VERSION = "^1(\\.\\d+){0,2}$";
    private final DataStoreApiParser parser;

    public DataStoreApiDefinitionConverter(DataStoreApiParser parser) {
        this.parser = parser;
    }

    @Override
    public boolean supports(String specification, String specificationVersion) {
        return SPECIFICATION.equalsIgnoreCase(specification) &&
                specificationVersion.matches(VERSION);
    }

    @Override
    public DataStoreApi deserialize(ObjectMapper defaultMapper, JsonNode jsonNode) throws IOException {
        return parser.deserialize(jsonNode);
    }

    @Override
    public JsonNode serialize(ObjectMapper defaultMapper, DataStoreApi value) throws IOException {
        return parser.serialize(value);
    }
}
