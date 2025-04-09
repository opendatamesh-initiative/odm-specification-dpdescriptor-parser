package org.opendatamesh.dpds.datastoreapi.v1;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.opendatamesh.dpds.datastoreapi.v1.extensions.DataStoreApiStandardDefinitionConverter;

class DumbDataStoreApiStandardDefinitionObjectConverter implements DataStoreApiStandardDefinitionConverter<DumbDataStoreApiStandardDefinitionObject> {

    private static final String SPECIFICATION = "dumb_specification";
    private static final String VERSION = "^1(\\.\\d+){0,2}$";

    DumbDataStoreApiStandardDefinitionObjectConverter() {
    }

    @Override
    public boolean supports(String specification, String specificationVersion) {
        return SPECIFICATION.equalsIgnoreCase(specification) && specificationVersion.matches(VERSION);
    }

    @Override
    public DumbDataStoreApiStandardDefinitionObject deserialize(ObjectMapper defaultMapper, JsonNode jsonNode) throws JacksonException {
        return defaultMapper.treeToValue(jsonNode, DumbDataStoreApiStandardDefinitionObject.class);
    }

    @Override
    public JsonNode serialize(ObjectMapper defaultMapper, DumbDataStoreApiStandardDefinitionObject value) throws JacksonException {
        return defaultMapper.valueToTree(value);
    }
}
