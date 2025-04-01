package org.opendatamesh.dpds.parser;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.opendatamesh.dpds.extensions.ComponentBaseExtendedConverter;
import org.opendatamesh.dpds.extensions.DefinitionConverter;
import org.opendatamesh.dpds.extensions.visitorsimpl.DataProductVersionExtensionVisitorImpl;
import org.opendatamesh.dpds.extensions.visitorsimpl.ExtensionHandler;
import org.opendatamesh.dpds.model.DataProductVersion;
import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.visitors.DataProductVersionVisitor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.opendatamesh.dpds.extensions.visitorsimpl.ExtensionHandler.ExtensionHandlerStatus.DESERIALIZING;
import static org.opendatamesh.dpds.extensions.visitorsimpl.ExtensionHandler.ExtensionHandlerStatus.SERIALIZING;

class ParserImpl implements Parser {

    private final ObjectMapper objectMapper;
    private final List<ComponentBaseExtendedConverter<ComponentBase>> componentBaseExtendedConverters = new ArrayList<>();
    private final List<DefinitionConverter<ComponentBase>> definitionConverters = new ArrayList<>();

    public ParserImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public DataProductVersion deserialize(JsonNode raw) throws IOException {
        // Deep copying the raw jsonNode object so it can be modified without affecting
        // the original object passed as parameter
        JsonNode jsonNode = raw.deepCopy();

        DataProductVersion dataProductVersion = objectMapper.treeToValue(jsonNode, DataProductVersion.class);
        if (!componentBaseExtendedConverters.isEmpty() || !definitionConverters.isEmpty()) {
            ExtensionHandler extensionHandler = new ExtensionHandler(DESERIALIZING, componentBaseExtendedConverters, definitionConverters, objectMapper);
            DataProductVersionVisitor visitor = new DataProductVersionExtensionVisitorImpl(extensionHandler);
            extensionHandler.handleComponentBaseExtension(dataProductVersion, null);
            if (dataProductVersion.getInterfaceComponents() != null) {
                visitor.visit(dataProductVersion.getInterfaceComponents());
            }
            if (dataProductVersion.getInternalComponents() != null) {
                visitor.visit(dataProductVersion.getInternalComponents());
            }
            if (dataProductVersion.getComponents() != null) {
                visitor.visit(dataProductVersion.getComponents());
            }
        }
        return dataProductVersion;

    }

    @Override
    public JsonNode serialize(DataProductVersion product) throws IOException{
        // Deep copying the descriptor object so it can be modified without affecting
        // the original object passed as parameter
        DataProductVersion dataProductVersion = deepCopy(product);
        if (!componentBaseExtendedConverters.isEmpty() || !definitionConverters.isEmpty()) {
            ExtensionHandler extensionHandler = new ExtensionHandler(SERIALIZING, componentBaseExtendedConverters, definitionConverters, objectMapper);
            DataProductVersionVisitor visitor = new DataProductVersionExtensionVisitorImpl(extensionHandler);
            extensionHandler.handleComponentBaseExtension(dataProductVersion, null);
            if (dataProductVersion.getInterfaceComponents() != null) {
                visitor.visit(dataProductVersion.getInterfaceComponents());
            }
            if (dataProductVersion.getInternalComponents() != null) {
                visitor.visit(dataProductVersion.getInternalComponents());
            }
            if (dataProductVersion.getComponents() != null) {
                visitor.visit(dataProductVersion.getComponents());
            }
        }
        return objectMapper.valueToTree(dataProductVersion);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T extends ComponentBase> Parser register(ComponentBaseExtendedConverter<T> componentBaseExtendedConverter) {
        this.componentBaseExtendedConverters.add((ComponentBaseExtendedConverter<ComponentBase>) componentBaseExtendedConverter);
        return this;
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T extends ComponentBase> Parser register(DefinitionConverter<T> definitionConverter) {
        this.definitionConverters.add((DefinitionConverter<ComponentBase>) definitionConverter);
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
