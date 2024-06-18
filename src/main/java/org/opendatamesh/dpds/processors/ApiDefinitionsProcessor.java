package org.opendatamesh.dpds.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.opendatamesh.dpds.api.ApiParser;
import org.opendatamesh.dpds.api.ApiParserFactory;
import org.opendatamesh.dpds.exceptions.DeserializationException;
import org.opendatamesh.dpds.exceptions.FetchException;
import org.opendatamesh.dpds.exceptions.UnresolvableReferenceException;
import org.opendatamesh.dpds.model.DataProductVersionDPDS;
import org.opendatamesh.dpds.model.core.EntityTypeDPDS;
import org.opendatamesh.dpds.model.core.StandardDefinitionDPDS;
import org.opendatamesh.dpds.model.definitions.ApiDefinitionReferenceDPDS;
import org.opendatamesh.dpds.model.definitions.DefinitionReferenceDPDS;
import org.opendatamesh.dpds.model.interfaces.InterfaceComponentsDPDS;
import org.opendatamesh.dpds.model.interfaces.PortDPDS;
import org.opendatamesh.dpds.parser.ParseContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

class ApiDefinitionsProcessor implements PropertiesProcessor {

    ParseContext context;

    private static final Logger logger = LoggerFactory.getLogger(ApiDefinitionsProcessor.class);

    public ApiDefinitionsProcessor(ParseContext context) {
        this.context = context;
    }

    public void process() throws UnresolvableReferenceException, DeserializationException {

        DataProductVersionDPDS parsedContent = context.getResult().getDescriptorDocument();
        Objects.requireNonNull(parsedContent, "Impossible to prcess API definitions. Descriptor document is null");

        InterfaceComponentsDPDS interfaceComponents = parsedContent.getInterfaceComponents();
        if (interfaceComponents != null) {
            if (interfaceComponents.hasPorts(EntityTypeDPDS.INPUTPORT)) {
                processApiDefinitions(interfaceComponents.getInputPorts());
            }

            if (interfaceComponents.hasPorts(EntityTypeDPDS.OUTPUTPORT)) {
                processApiDefinitions(interfaceComponents.getOutputPorts());
            }

            if (interfaceComponents.hasPorts(EntityTypeDPDS.DISCOVERYPORT)) {
                processApiDefinitions(interfaceComponents.getDiscoveryPorts());
            }

            if (interfaceComponents.hasPorts(EntityTypeDPDS.OBSERVABILITYPORT)) {
                processApiDefinitions(interfaceComponents.getObservabilityPorts());
            }

            if (interfaceComponents.hasPorts(EntityTypeDPDS.CONTROLPORT)) {
                processApiDefinitions(interfaceComponents.getControlPorts());
            }

            logger.info("API definitions sucesfully processed");
        } else {
            logger.info("No API definition to process");
        }
    }

    private void processApiDefinitions(List<PortDPDS> ports) throws DeserializationException {

        Objects.requireNonNull(ports, "Parameter [ports] cannot be null");

        for (PortDPDS port : ports) {

            if (!port.hasApiDefinition()) {
                logger.debug("No API definition to process for port [" + port.getName() + "]");
                continue;
            }

            ObjectNode apiNode = null;
            try {
                apiNode = (ObjectNode) context.getMapper().readTree(port.getPromises().getApi().getRawContent());
            } catch (JsonProcessingException e) {
                throw new DeserializationException("Impossible to parse raw content of port [" + port.getName() + "]", e);
            }


            try {
                parseApiDefinition(port);
            } catch (FetchException e) {
                throw new DeserializationException(
                        "Impossible to parse api definition of port [" + port.getName() + "]", e);
            }
        }
    }

    private void parseApiDefinition(PortDPDS port) throws FetchException, DeserializationException {

        if (port == null || port.getPromises() == null || port.getPromises().getApi() == null
                || port.getPromises().getApi().getDefinition() == null)
            return;

        StandardDefinitionDPDS api = port.getPromises().getApi();
        String apiDefinitionRawContent = api.getDefinition().getRawContent();
        String apiDefinitionMediaType = api.getDefinition().getMediaType();
        String specification = api.getSpecification();

        ApiParser apiParser = ApiParserFactory.getApiParser(specification, context.getLocation().getRootDocumentBaseUri());
        if (apiParser == null) {
            System.out.println("\n\n====\n" + specification + "\n====\n\n"
                    + port.getPromises().getApi().getSpecification() + " not supported");
            return;
        }
        ApiDefinitionReferenceDPDS parsedApiDefinition = apiParser.parse(apiDefinitionRawContent, apiDefinitionMediaType);
        if (parsedApiDefinition != null) {
            DefinitionReferenceDPDS apiDefinition = api.getDefinition();
            parsedApiDefinition.setDescription(apiDefinition.getDescription());
            parsedApiDefinition.setMediaType(apiDefinition.getMediaType());
            parsedApiDefinition.setRef(apiDefinition.getRef());
            parsedApiDefinition.setRawContent(apiDefinition.getRawContent());
            api.setDefinition(parsedApiDefinition);
        }
    }
}
