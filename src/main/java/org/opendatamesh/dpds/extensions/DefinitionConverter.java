package org.opendatamesh.dpds.extensions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.opendatamesh.dpds.model.core.ComponentBase;

import java.io.IOException;

public interface DefinitionConverter<T extends ComponentBase> {
    boolean supports(String specification, String specificationVersion);

    T deserialize(ObjectMapper defaultMapper, JsonNode jsonNode) throws IOException;

    JsonNode serialize(ObjectMapper defaultMapper, T value) throws IOException;
}
