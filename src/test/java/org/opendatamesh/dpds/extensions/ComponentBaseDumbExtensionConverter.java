package org.opendatamesh.dpds.extensions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.opendatamesh.dpds.model.core.ComponentBase;

class ComponentBaseDumbExtensionConverter implements ComponentBaseExtendedConverter<ComponentBaseDumbExtension> {

    @Override
    public boolean supports(String key, Class<? extends ComponentBase> parentClass) {
        return parentClass == null && "dumb_extension".equalsIgnoreCase(key);
    }

    @Override
    public ComponentBaseDumbExtension deserialize(ObjectMapper defaultMapper, JsonNode jsonNode) {
        try {
            return defaultMapper.treeToValue(jsonNode, ComponentBaseDumbExtension.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonNode serialize(ObjectMapper defaultMapper, ComponentBaseDumbExtension value) {
        return defaultMapper.valueToTree(value);
    }
}
