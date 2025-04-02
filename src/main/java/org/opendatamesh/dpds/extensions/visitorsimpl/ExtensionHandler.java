package org.opendatamesh.dpds.extensions.visitorsimpl;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.opendatamesh.dpds.extensions.ComponentBaseExtendedConverter;
import org.opendatamesh.dpds.extensions.DefinitionConverter;
import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.StandardDefinition;

import java.util.*;

public class ExtensionHandler {

    private final ExtensionHandlerStatus status;
    private final List<ComponentBaseExtendedConverter<ComponentBase>> componentBaseExtendedConverters;
    private final List<DefinitionConverter<ComponentBase>> definitionConverters;
    private final ObjectMapper mapper;

    public ExtensionHandler(
            ExtensionHandlerStatus status,
            List<ComponentBaseExtendedConverter<ComponentBase>> componentBaseExtendedConverters,
            List<DefinitionConverter<ComponentBase>> definitionConverters,
            ObjectMapper mapper
    ) {
        this.status = status;
        this.componentBaseExtendedConverters = componentBaseExtendedConverters;
        this.definitionConverters = definitionConverters;
        this.mapper = mapper;
    }

    public void handleComponentBaseExtension(ComponentBase componentBase, Class<? extends ComponentBase> parentClazz) {
        try {
            switch (status) {
                case SERIALIZING:
                    for (Map.Entry<String, ComponentBase> parsedProperty : componentBase.getParsedProperties().entrySet()) {
                        Optional<ComponentBaseExtendedConverter<ComponentBase>> extendedComponentBaseConverter = findSupportedExtensionConverter(parentClazz, parsedProperty.getKey());
                        if (extendedComponentBaseConverter.isPresent()) {
                            componentBase.addAdditionalProperty(
                                    parsedProperty.getKey(),
                                    extendedComponentBaseConverter.get()
                                            .serialize(mapper, parsedProperty.getValue())
                            );
                        } else {
                            throw new IllegalStateException("No ComponentBaseExtendedConverter has been registered on the Parser that can handle this property: " + parsedProperty.getKey() + " " + mapper.writeValueAsString(parsedProperty.getValue()));
                        }
                    }
                    break;
                case DESERIALIZING:
                    Set<String> keys = new HashSet<>(componentBase.getAdditionalProperties().keySet());
                    for (String key : keys) {
                        Optional<ComponentBaseExtendedConverter<ComponentBase>> extendedComponentBaseConverter = findSupportedExtensionConverter(parentClazz, key);
                        if (extendedComponentBaseConverter.isPresent()) {
                            ComponentBase c = extendedComponentBaseConverter.get().
                                    deserialize(mapper, componentBase.getAdditionalProperties().remove(key));
                            componentBase.addParsedProperty(key, c);
                        }
                    }
                    break;
            }
        } catch (JacksonException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<ComponentBaseExtendedConverter<ComponentBase>> findSupportedExtensionConverter(
            Class<? extends ComponentBase> parentClazz, String key
    ) {
        return componentBaseExtendedConverters.stream()
                .filter(e -> e.supports(key, parentClazz))
                .findFirst();
    }

    private Optional<DefinitionConverter<ComponentBase>> findSupportedDefinitionConverter(
            String definition, String definitionVersion
    ) {
        return definitionConverters.stream()
                .filter(e -> e.supports(definition, definitionVersion))
                .findFirst();
    }

    public void handleDefinition(StandardDefinition standardDefinition) {
        Optional<DefinitionConverter<ComponentBase>> supportedDefinitionConverter = findSupportedDefinitionConverter(standardDefinition.getSpecification(), standardDefinition.getSpecificationVersion());
        if (supportedDefinitionConverter.isEmpty()) {
            return;
        }
        try {
            JsonNode rawDefinition;
            switch (status) {
                case SERIALIZING:
                    rawDefinition = supportedDefinitionConverter.get().serialize(mapper, standardDefinition.getDefinition());
                    standardDefinition.setDefinition(mapper.treeToValue(rawDefinition, ComponentBase.class));
                    break;
                case DESERIALIZING:
                    rawDefinition = mapper.valueToTree(standardDefinition.getDefinition());
                    standardDefinition.setDefinition(supportedDefinitionConverter.get().deserialize(mapper, rawDefinition));
                    break;
            }
        } catch (JacksonException e) {
            throw new RuntimeException(e);
        }
    }

    public enum ExtensionHandlerStatus {
        SERIALIZING,
        DESERIALIZING
    }
}
