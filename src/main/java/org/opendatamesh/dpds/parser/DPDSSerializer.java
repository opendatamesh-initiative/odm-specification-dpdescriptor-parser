package org.opendatamesh.dpds.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import org.opendatamesh.dpds.utils.ObjectMapperFactory;
import org.opendatamesh.dpds.model.DataProductVersionDPDS;
import org.opendatamesh.dpds.model.core.ComponentDPDS;
import org.opendatamesh.dpds.model.core.EntityTypeDPDS;
import org.opendatamesh.dpds.model.core.StandardDefinitionDPDS;
import org.opendatamesh.dpds.model.interfaces.InterfaceComponentsDPDS;
import org.opendatamesh.dpds.model.interfaces.PortDPDS;
import org.opendatamesh.dpds.model.internals.InternalComponentsDPDS;
import org.opendatamesh.dpds.model.internals.LifecycleInfoDPDS;
import org.opendatamesh.dpds.model.internals.LifecycleTaskInfoDPDS;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class DPDSSerializer {

    ObjectMapper mapper;
    boolean prettyPrint;

    public static DPDSSerializer DEFAULT_JSON_SERIALIZER;
    public static DPDSSerializer DEFAULT_YAML_SERIALIZER;

    static {
        DEFAULT_JSON_SERIALIZER = new DPDSSerializer("json", true);
        DEFAULT_YAML_SERIALIZER = new DPDSSerializer("yaml", true);
    }

    public DPDSSerializer() {
        this("json", true);
    }

    public DPDSSerializer(String mediaType, boolean prettyPrint) {
        if (mediaType.equalsIgnoreCase("yaml")) {
            mapper = ObjectMapperFactory.YAML_MAPPER;
        } else {
            mapper = ObjectMapperFactory.JSON_MAPPER;
        }

        this.prettyPrint = prettyPrint;
    }

    public String serialize(
            DataProductVersionDPDS descriptorResource,
            String form) throws JsonProcessingException {

        String result = null;

        if (form.equalsIgnoreCase("canonical")) {
            result = serializeToCanonicalForm(descriptorResource);
        } else {
            result = writeValueAsString(descriptorResource);
        }

        return result;
    }

    public String serializeToCanonicalForm(
            DataProductVersionDPDS dataProductVersion) throws JsonProcessingException {

        String result = null;
        ObjectNode resultRootNode = null;

        String rootRawContent = dataProductVersion.getRawContent();

        ObjectNode rootNode = (ObjectNode) ObjectMapperFactory.getRightMapper(rootRawContent).readTree(rootRawContent);

        resultRootNode = mapper.createObjectNode();
        resultRootNode.set("dataProductDescriptor", rootNode.get("dataProductDescriptor"));
        resultRootNode.set("info", rootNode.get("info"));

        if (dataProductVersion.getInterfaceComponents() != null) {
            resultRootNode.set("interfaceComponents",
                    getInterfaceComponentsRawContent(dataProductVersion.getInterfaceComponents()));
        }
        if (dataProductVersion.getInternalComponents() != null) {
            resultRootNode.set("internalComponents",
                    getInternalComponentsRawContent(dataProductVersion.getInternalComponents()));
        }

        result = writeValueAsString(resultRootNode);

        return result;
    }

    private String writeValueAsString(Object value) throws JsonProcessingException {
        String result = null;

        if (prettyPrint) {
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } else {
            result = mapper.writeValueAsString(value);
        }

        return result;
    }

    public String serialize(
            InterfaceComponentsDPDS interfaceComponents,
            EntityTypeDPDS entityType,
            String form) throws JsonProcessingException {

        String result = null;

        if (form.equalsIgnoreCase("canonical")) {
            result = serializeToCanonicalForm(interfaceComponents, entityType);
        } else {
            result = writeValueAsString(interfaceComponents);

        }

        return result;
    }

    public String serializeToCanonicalForm(
            InterfaceComponentsDPDS interfaceComponents,
            EntityTypeDPDS entityType) throws JsonProcessingException {

        JsonNode interfacesComponentsNode = null;
        if (entityType != null) {
            interfacesComponentsNode = getInterfaceComponentsRawContent(interfaceComponents,
                    new HashSet<EntityTypeDPDS>(Arrays.asList(entityType)));
        } else {
            interfacesComponentsNode = getInterfaceComponentsRawContent(interfaceComponents);
        }

        return writeValueAsString(interfacesComponentsNode);
    }

    public String serializeToCanonicalForm(
            InternalComponentsDPDS internalComponents,
            EntityTypeDPDS entityType) throws JsonProcessingException {

        JsonNode internalComponentsNode = null;
        if (entityType != null) {
            internalComponentsNode = getInternalComponentsRawContent(internalComponents,
                    new HashSet<EntityTypeDPDS>(Arrays.asList(entityType)));
        } else {
            internalComponentsNode = getInternalComponentsRawContent(internalComponents);
        }

        return writeValueAsString(internalComponentsNode);
    }

    public <T extends ComponentDPDS> String serialize(
            List<T> components,
            String form) throws JsonProcessingException {

        String result = null;

        if (form.equalsIgnoreCase("canonical")) {
            result = serializeToCanonicalForm(components);
        } else {
            result = writeValueAsString(components);

        }

        return result;
    }

    public <T extends ComponentDPDS> String serializeToCanonicalForm(
            List<T> components) throws JsonProcessingException {

        String result = writeValueAsString(getComponetsRawContent(components));
        return result;
    }

    private JsonNode getInterfaceComponentsRawContent(InterfaceComponentsDPDS resources)
            throws JsonProcessingException {
        return getInterfaceComponentsRawContent(resources,
                new HashSet<EntityTypeDPDS>(Arrays.asList(EntityTypeDPDS.values())));
    }

    private JsonNode getInterfaceComponentsRawContent(
            InterfaceComponentsDPDS interfaceComponentsResource,
            Set<EntityTypeDPDS> inludedInterfaceComponentTypes) throws JsonProcessingException {

        ObjectNode interfaceComponentsNode = mapper.createObjectNode();

        ArrayNode portsNode = null;
        if (inludedInterfaceComponentTypes.contains(EntityTypeDPDS.INPUTPORT)) {
            portsNode = getComponetsRawContent(interfaceComponentsResource.getInputPorts());
            if (portsNode.size() > 0)
                interfaceComponentsNode.set(EntityTypeDPDS.INPUTPORT.groupingPropertyName(), portsNode);
        }

        if (inludedInterfaceComponentTypes.contains(EntityTypeDPDS.OUTPUTPORT)) {
            portsNode = getComponetsRawContent(interfaceComponentsResource.getOutputPorts());
            // include even if it is empty to make the validator happy :)
            interfaceComponentsNode.set(EntityTypeDPDS.OUTPUTPORT.groupingPropertyName(), portsNode);
        }

        if (inludedInterfaceComponentTypes.contains(EntityTypeDPDS.CONTROLPORT)) {
            portsNode = getComponetsRawContent(interfaceComponentsResource.getControlPorts());
            if (portsNode.size() > 0)
                interfaceComponentsNode.set(EntityTypeDPDS.CONTROLPORT.groupingPropertyName(), portsNode);
        }

        if (inludedInterfaceComponentTypes.contains(EntityTypeDPDS.DISCOVERYPORT)) {
            portsNode = getComponetsRawContent(interfaceComponentsResource.getDiscoveryPorts());
            if (portsNode.size() > 0)
                interfaceComponentsNode.set(EntityTypeDPDS.DISCOVERYPORT.groupingPropertyName(), portsNode);
        }

        if (inludedInterfaceComponentTypes.contains(EntityTypeDPDS.OBSERVABILITYPORT)) {
            portsNode = getComponetsRawContent(interfaceComponentsResource.getObservabilityPorts());
            if (portsNode.size() > 0)
                interfaceComponentsNode.set(EntityTypeDPDS.OBSERVABILITYPORT.groupingPropertyName(), portsNode);
        }

        return interfaceComponentsNode;
    }

    private JsonNode getInternalComponentsRawContent(InternalComponentsDPDS resources) throws JsonProcessingException {
        return getInternalComponentsRawContent(resources,
                new HashSet<EntityTypeDPDS>(Arrays.asList(EntityTypeDPDS.values())));
    }

    private JsonNode getInternalComponentsRawContent(InternalComponentsDPDS resources,
            Set<EntityTypeDPDS> inludedInternalComponentTypes)
            throws JsonProcessingException {

        ObjectNode internalComponentsNode = mapper.createObjectNode();

        ArrayNode appNodes = null;
        if (inludedInternalComponentTypes.contains(EntityTypeDPDS.APPLICATION)) {
            appNodes = getComponetsRawContent(resources.getApplicationComponents());
            if (appNodes.size() > 0)
                internalComponentsNode.set(EntityTypeDPDS.APPLICATION.groupingPropertyName(), appNodes);
        }

        ArrayNode infraNodes = null;
        if (inludedInternalComponentTypes.contains(EntityTypeDPDS.INFRASTRUCTURE)) {
            infraNodes = getComponetsRawContent(resources.getInfrastructuralComponents());
            if (infraNodes.size() > 0)
                internalComponentsNode.set(EntityTypeDPDS.INFRASTRUCTURE.groupingPropertyName(), infraNodes);
        }

        internalComponentsNode.set("lifecycleInfo", getLifecycleInfoRawContent(resources.getLifecycleInfo()));

        return internalComponentsNode;
    }

    private ObjectNode getLifecycleInfoRawContent(LifecycleInfoDPDS lifecycleInfo) throws JsonProcessingException {

        if (lifecycleInfo == null)
            return null; // nothing to do

        ObjectNode lifecycleNode = mapper.createObjectNode();

        Set<String> stageNames = lifecycleInfo.getStageNames();
        for (String stageName : stageNames) {

            ArrayNode tasksInfoNode = mapper.createArrayNode();

            for (LifecycleTaskInfoDPDS taskInfo : lifecycleInfo.getTasksInfo(stageName)) {
                ObjectNode taskInfoNode = (ObjectNode) mapper.readTree(taskInfo.getRawContent());
                taskInfoNode.remove("stageName");

                if (taskInfo.hasTemplate()) {
                    ObjectNode templateNode = getComponetRawContent(taskInfo.getTemplate());
                    taskInfoNode.set("template", templateNode);  
                }
                tasksInfoNode.add(taskInfoNode);
            }
            lifecycleNode.set(stageName, tasksInfoNode);
        }

        return lifecycleNode;
    }

    private ArrayNode getComponetsRawContent(List<? extends ComponentDPDS> components) throws JsonProcessingException {

        ArrayNode interfaceComponentsNode = mapper.createArrayNode();

        for (ComponentDPDS component : components) {
            JsonNode componentNode = getComponetRawContent(component);
            interfaceComponentsNode.add(componentNode);
        }

        return interfaceComponentsNode;
    }

    private ObjectNode getComponetRawContent(ComponentDPDS componentResource) throws JsonProcessingException {
        ObjectNode componentNode = null;

        String componentContent = componentResource.getRawContent();
        ObjectMapper mapper = ObjectMapperFactory.getRightMapper(componentContent);
        componentNode = (ObjectNode) mapper.readTree(componentContent);

        if (componentResource instanceof PortDPDS) {
            PortDPDS portResource = (PortDPDS) componentResource;
            if (portResource.hasApi()) {
                ObjectNode apiNode = getComponetRawContent(portResource.getPromises().getApi());
                ObjectNode promisesNode = (ObjectNode) componentNode.get("promises");
                promisesNode.set("api", apiNode);
            }
        }

        if (componentResource instanceof StandardDefinitionDPDS) {
            StandardDefinitionDPDS stdDefRes = (StandardDefinitionDPDS) componentResource;

            if (stdDefRes.getDefinition() != null) {
                ObjectNode defNode = null;
                if (stdDefRes.getDefinition().isRef()) {
                    defNode = mapper.valueToTree(stdDefRes.getDefinition());
                } else {
                    defNode = (ObjectNode) mapper.readTree(stdDefRes.getDefinition().getRawContent());
                }

                componentNode.set("definition", defNode);
            }
        }

        return componentNode;
    }

}
