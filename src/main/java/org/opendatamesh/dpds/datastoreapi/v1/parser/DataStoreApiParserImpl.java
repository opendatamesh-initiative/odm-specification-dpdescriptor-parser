package org.opendatamesh.dpds.datastoreapi.v1.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.opendatamesh.dpds.datastoreapi.v1.extensions.DataStoreApiStandardDefinitionConverter;
import org.opendatamesh.dpds.datastoreapi.v1.extensions.visitorimpl.DataStoreApiExtensionVisitorImpl;
import org.opendatamesh.dpds.datastoreapi.v1.extensions.visitorimpl.ExtensionHandler;
import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApi;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiVisitor;
import org.opendatamesh.dpds.extensions.ComponentBaseExtendedConverter;
import org.opendatamesh.dpds.model.core.ComponentBase;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class DataStoreApiParserImpl implements DataStoreApiParser {

    private final ObjectMapper objectMapper;
    private final List<ComponentBaseExtendedConverter<ComponentBase>> componentBaseExtendedConverters = new ArrayList<>();
    private final List<DataStoreApiStandardDefinitionConverter<ComponentBase>> tableConverters = new ArrayList<>();

    public DataStoreApiParserImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public DataStoreApi deserialize(JsonNode raw) throws IOException {
        // Deep copying the raw jsonNode object so it can be modified without affecting
        // the original object passed as parameter
        JsonNode jsonNode = raw.deepCopy();

        DataStoreApi dataStoreApi = objectMapper.treeToValue(jsonNode, DataStoreApi.class);
        if (!componentBaseExtendedConverters.isEmpty() || !tableConverters.isEmpty()) {
            ExtensionHandler extensionHandler = new ExtensionHandler(ExtensionHandler.ExtensionHandlerStatus.DESERIALIZING, componentBaseExtendedConverters, tableConverters, objectMapper);
            DataStoreApiVisitor visitor = new DataStoreApiExtensionVisitorImpl(extensionHandler);
            extensionHandler.handleComponentBaseExtension(dataStoreApi, DataStoreApi.class);

            if (dataStoreApi.getInfo() != null) {
                visitor.visit(dataStoreApi.getInfo());
            }
            if (dataStoreApi.getServices() != null) {
                dataStoreApi.getServices().forEach((key, value) -> visitor.visit(value));
            }
            if (dataStoreApi.getSchema() != null) {
                visitor.visit(dataStoreApi.getSchema());
            }
        }
        return dataStoreApi;

    }

    @Override
    public JsonNode serialize(DataStoreApi dataStore) throws IOException {
        // Deep copying the descriptor object so it can be modified without affecting
        // the original object passed as parameter
        DataStoreApi dataStoreApi = deepCopy(dataStore);
        if (!componentBaseExtendedConverters.isEmpty() || !tableConverters.isEmpty()) {
            ExtensionHandler extensionHandler = new ExtensionHandler(ExtensionHandler.ExtensionHandlerStatus.SERIALIZING, componentBaseExtendedConverters, tableConverters, objectMapper);
            DataStoreApiVisitor visitor = new DataStoreApiExtensionVisitorImpl(extensionHandler);
            extensionHandler.handleComponentBaseExtension(dataStoreApi, DataStoreApi.class);

            if (dataStoreApi.getInfo() != null) {
                visitor.visit(dataStoreApi.getInfo());
            }
            if (dataStoreApi.getServices() != null) {
                dataStoreApi.getServices().forEach((key, value) -> visitor.visit(value));
            }
            if (dataStoreApi.getSchema() != null) {
                visitor.visit(dataStoreApi.getSchema());
            }
        }
        return objectMapper.valueToTree(dataStoreApi);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T extends ComponentBase> DataStoreApiParser register(ComponentBaseExtendedConverter<T> componentBaseExtendedConverter) {
        this.componentBaseExtendedConverters.add((ComponentBaseExtendedConverter<ComponentBase>) componentBaseExtendedConverter);
        return this;
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T extends ComponentBase> DataStoreApiParser register(DataStoreApiStandardDefinitionConverter<T> dataStoreApiStandardDefinitionConverter) {
        this.tableConverters.add((DataStoreApiStandardDefinitionConverter<ComponentBase>) dataStoreApiStandardDefinitionConverter);
        return this;
    }

    @SuppressWarnings({"unchecked"})
    private <T extends Serializable> T deepCopy(T object) throws IOException {
        // Write the object to a byte array output stream
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            out.flush();

            // Read the object back from the byte array input stream
            try (ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
                 ObjectInputStream in = new ObjectInputStream(bis)) {
                return (T) in.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
