package org.opendatamesh.dpds.extensions;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomDefinitionConverter implements DefinitionConverter<CustomDefinition> {
    @Override
    public boolean supports(String specification, String specificationVersion) {
        return "my_specification".equalsIgnoreCase(specification) && "1.0.0".equalsIgnoreCase(specificationVersion);
    }

    @Override
    public CustomDefinition deserialize(ObjectMapper defaultMapper, JsonNode jsonNode) throws JacksonException {
        return defaultMapper.treeToValue(jsonNode, CustomDefinition.class);
    }

    @Override
    public JsonNode serialize(ObjectMapper defaultMapper, CustomDefinition value) throws JacksonException {
        return defaultMapper.valueToTree(value);
    }
}
