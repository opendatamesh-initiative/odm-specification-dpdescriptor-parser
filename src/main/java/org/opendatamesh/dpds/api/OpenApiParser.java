package org.opendatamesh.dpds.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.opendatamesh.dpds.exceptions.DeserializationException;
import org.opendatamesh.dpds.exceptions.FetchException;
import org.opendatamesh.dpds.location.UriFetcher;
import org.opendatamesh.dpds.model.definitions.ApiDefinitionEndpointDPDS;
import org.opendatamesh.dpds.model.definitions.ApiDefinitionReferenceDPDS;
import org.opendatamesh.dpds.utils.ObjectMapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class OpenApiParser implements ApiParser {

    private static final Logger logger = LoggerFactory.getLogger(OpenApiParser.class);
    protected URI baseUri;
    protected UriFetcher fetcher;

    public OpenApiParser(URI baseUri) {
        this.baseUri = baseUri;
    }

    public ApiDefinitionReferenceDPDS parse(String rawContent, String mediaType) throws DeserializationException, FetchException {
        ApiDefinitionReferenceDPDS api = new ApiDefinitionReferenceDPDS();
        api.setBaseUri(baseUri);
        api.setRawContent(rawContent);
        api.setEndpoints(extractEndpoints(rawContent, resolveMediaType(mediaType)));
        return api;
    }

    private List<ApiDefinitionEndpointDPDS> extractEndpoints(String rawContent, String mediaType)
            throws DeserializationException, FetchException {
        List<ApiDefinitionEndpointDPDS> endpoints = new ArrayList<ApiDefinitionEndpointDPDS>();

        ObjectMapper mapper = getObjectMapper(mediaType);
        if (mapper == null) {
            throw new DeserializationException("Impossible to parse api definition encoded in [" + mediaType + "]");
        }

        try {
            ObjectNode apiNode = (ObjectNode) mapper.readTree(rawContent);
            if (!apiNode.has("openapi"))
                return null;
            if (apiNode.get("paths") != null) {
                ObjectNode paths = (ObjectNode) apiNode.get("paths");
                Iterator<String> pathNames = paths.fieldNames();
                int i = 0;
                while (pathNames.hasNext()) {
                    ObjectNode path = (ObjectNode) paths.get(pathNames.next());
                    ObjectNode getOperation = (ObjectNode) path.get("get");
                    if (getOperation == null)
                        continue;
                    ObjectNode responses = (ObjectNode) getOperation.get("responses");
                    if (responses == null)
                        continue;
                    ObjectNode response = (ObjectNode) responses.get("200");
                    if (response == null)
                        continue;
                    ObjectNode schema = (ObjectNode) response.get("schema");

                    ApiDefinitionEndpointDPDS endpoint;
                    String name = null, schemaMediaType = null, outputMediaType = null, operationSchema = null;

                    if (getOperation.get("operationId") != null) {
                        name = getOperation.get("operationId").asText();
                    } else {
                        name = "endpoint-" + (i + 1);
                    }

                    if (schema.get("$ref") != null) {

                        String schemaRef = schema.get("$ref").asText();
                        operationSchema = fetcher.fetchRelativeResource(baseUri, new URI(schemaRef));
                        if (schemaRef.endsWith(".yaml") || schemaRef.endsWith(".yaml")) {
                            schemaMediaType = "application/yaml";
                        } else if (schemaRef.endsWith(".json")) {
                            schemaMediaType = "application/json";
                        } else {
                            schemaMediaType = mediaType;
                        }
                    } else {
                        operationSchema = schema.toPrettyString();
                        schemaMediaType = mediaType;
                    }

                    if (getOperation.get("produces") != null) {
                        outputMediaType = getOperation.get("produces").asText();
                    } else {
                        outputMediaType = "application/json";
                    }

                    endpoint = new ApiDefinitionEndpointDPDS();
                    endpoint.setName(name);
                    ApiDefinitionEndpointDPDS.Schema s = new ApiDefinitionEndpointDPDS.Schema();
                    s.setMediaType(schemaMediaType);
                    s.setContent(operationSchema);
                    endpoint.setSchema(s);
                    endpoints.add(endpoint);

                    i++;

                }
            }
        } catch (JsonProcessingException e) {
            throw new DeserializationException("Impossible to parse api definition", e);
        } catch (URISyntaxException e) {
            throw new DeserializationException("Impossible to parse api definition", e);
        }

        return endpoints;
    }

    private String resolveMediaType(String mediaType) {

        if (mediaType == null) {
            logger.warn("Media type is not specified. Defualt media type [application/json] will be used");
            return "application/json";
        }

        mediaType = mediaType.toLowerCase().trim();
        if (mediaType.equalsIgnoreCase("text/json")) {
            logger.warn("Media type [text/json] is incorrect. Media type [application/json] will be used");
            mediaType = "application/json";
        } else if (mediaType.endsWith("+json")) {
            mediaType = "application/json";
        } else if (mediaType.equalsIgnoreCase("text/yaml")) {
            logger.warn("Media type [text/yaml] is incorrect. Media type [application/yaml] will be used");
            mediaType = "application/yaml";
        } else if (mediaType.endsWith("+yaml")) {
            mediaType = "application/yaml";
        }

        return mediaType;
    }

    private ObjectMapper getObjectMapper(String mediaType) {
        ObjectMapper mapper = null;
        if ("application/json".equals(mediaType)) {
            mapper = ObjectMapperFactory.JSON_MAPPER;
        } else if ("application/yaml".equals(mediaType)) {
            mapper = ObjectMapperFactory.YAML_MAPPER;
        }
        return mapper;
    }
}
