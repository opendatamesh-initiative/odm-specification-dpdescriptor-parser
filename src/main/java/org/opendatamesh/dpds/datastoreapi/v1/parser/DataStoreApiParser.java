package org.opendatamesh.dpds.datastoreapi.v1.parser;

import com.fasterxml.jackson.databind.JsonNode;
import org.opendatamesh.dpds.datastoreapi.v1.extensions.DataStoreApiStandardDefinitionConverter;
import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApi;
import org.opendatamesh.dpds.extensions.ComponentBaseExtendedConverter;
import org.opendatamesh.dpds.model.core.ComponentBase;

import java.io.IOException;

/**
 * The {@code DataStoreApiParser} interface provides methods for serializing and deserializing
 * {@link DataStoreApi} objects. It also allows the registration of custom
 * converters for handling extended components and standard definition objects.
 */
public interface DataStoreApiParser {

    /**
     * Deserializes a JSON representation into a {@link DataStoreApi} instance.
     *
     * @param jsonNode the JSON node representing the dataStoreApi
     * @return the deserialized {@link DataStoreApi} instance
     * @throws IOException if an error occurs during deserialization
     */
    DataStoreApi deserialize(JsonNode jsonNode) throws IOException;

    /**
     * Serializes a {@link DataStoreApi} instance into a JSON representation.
     *
     * @param dataStoreApi the dataStoreApi instance to serialize
     * @return the JSON representation of the data product version
     * @throws IOException if an error occurs during serialization
     */
    JsonNode serialize(DataStoreApi dataStoreApi) throws IOException;

    /**
     * Registers a {@link ComponentBaseExtendedConverter} for handling extended components.
     *
     * @param componentBaseExtendedConverter the converter to register
     * @param <T>                            the type of component base the converter handles
     * @return the updated {@code Parser} instance
     */
    <T extends ComponentBase> DataStoreApiParser register(ComponentBaseExtendedConverter<T> componentBaseExtendedConverter);

    /**
     * Registers a {@link DataStoreApiStandardDefinitionConverter} for handling standard definition objects.
     *
     * @param dataStoreApiStandardDefinitionConverter the converter to register
     * @param <T>                        the type of component base the converter handles
     * @return the updated {@code Parser} instance
     */
    <T extends ComponentBase> DataStoreApiParser register(DataStoreApiStandardDefinitionConverter<T> dataStoreApiStandardDefinitionConverter);
}