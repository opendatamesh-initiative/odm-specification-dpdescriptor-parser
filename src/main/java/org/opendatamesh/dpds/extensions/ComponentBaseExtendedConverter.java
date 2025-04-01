package org.opendatamesh.dpds.extensions;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.opendatamesh.dpds.model.core.ComponentBase;

public interface ComponentBaseExtendedConverter<T extends ComponentBase> {

    boolean supports(String key, Class<? extends ComponentBase> parentClass);

    T deserialize(ObjectMapper defaultMapper, JsonNode jsonNode) throws JacksonException;

    JsonNode serialize(ObjectMapper defaultMapper, T value) throws JacksonException;
}
