package org.opendatamesh.dpds.model.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.opendatamesh.dpds.utils.ObjectMapperFactory;

import java.util.List;

public abstract class ComponentContainerDPDS {

    @JsonIgnore
    public ArrayNode getRawContent(
            List<? extends ComponentDPDS> components) throws JsonProcessingException {

        ObjectMapper objectMapper = ObjectMapperFactory.JSON_MAPPER;

        ArrayNode rawContentComponentNodes = objectMapper.createArrayNode();
        for (ComponentDPDS component : components) {
            String componentRawContent = component.getRawContent();
            ObjectNode componetNode = (ObjectNode) objectMapper.readTree(componentRawContent);
            rawContentComponentNodes.add(componetNode);
        }

        return rawContentComponentNodes;
    }

}
