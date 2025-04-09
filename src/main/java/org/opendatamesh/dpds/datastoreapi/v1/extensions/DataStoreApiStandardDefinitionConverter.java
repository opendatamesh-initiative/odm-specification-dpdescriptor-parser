package org.opendatamesh.dpds.datastoreapi.v1.extensions;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.opendatamesh.dpds.model.core.ComponentBase;

public interface DataStoreApiStandardDefinitionConverter<T extends ComponentBase> {
    boolean supports(String specification, String specificationVersion);

    T deserialize(ObjectMapper defaultMapper, JsonNode jsonNode) throws JacksonException;

    JsonNode serialize(ObjectMapper defaultMapper, T value) throws JacksonException;
}
