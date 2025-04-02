package org.opendatamesh.dpds.parser;

import com.fasterxml.jackson.databind.JsonNode;
import org.opendatamesh.dpds.extensions.ComponentBaseExtendedConverter;
import org.opendatamesh.dpds.extensions.DefinitionConverter;
import org.opendatamesh.dpds.model.DataProductVersion;
import org.opendatamesh.dpds.model.core.ComponentBase;

import java.io.IOException;

/**
 * The {@code Parser} interface provides methods for serializing and deserializing
 * {@link DataProductVersion} objects. It also allows the registration of custom
 * converters for handling extended components and definitions.
 */
public interface Parser {

    /**
     * Deserializes a JSON representation into a {@link DataProductVersion} instance.
     *
     * @param jsonNode the JSON node representing the data product version
     * @return the deserialized {@link DataProductVersion} instance
     * @throws IOException if an error occurs during deserialization
     */
    DataProductVersion deserialize(JsonNode jsonNode) throws IOException;

    /**
     * Serializes a {@link DataProductVersion} instance into a JSON representation.
     *
     * @param dataProductVersion the data product version instance to serialize
     * @return the JSON representation of the data product version
     * @throws IOException if an error occurs during serialization
     */
    JsonNode serialize(DataProductVersion dataProductVersion) throws IOException;

    /**
     * Registers a {@link ComponentBaseExtendedConverter} for handling extended components.
     *
     * @param componentBaseExtendedConverter the converter to register
     * @param <T>                            the type of component base the converter handles
     * @return the updated {@code Parser} instance
     */
    <T extends ComponentBase> Parser register(ComponentBaseExtendedConverter<T> componentBaseExtendedConverter);

    /**
     * Registers a {@link DefinitionConverter} for handling definition components.
     *
     * @param definitionConverter the converter to register
     * @param <T>                 the type of component base the converter handles
     * @return the updated {@code Parser} instance
     */
    <T extends ComponentBase> Parser register(DefinitionConverter<T> definitionConverter);
}