package org.opendatamesh.dpds.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
import java.util.List;

class DataStoreApiParser implements ApiParser {
    private static final Logger logger = LoggerFactory.getLogger(DataStoreApiParser.class);
    protected URI baseUri;
    protected UriFetcher fetcher;

    public DataStoreApiParser(URI baseUri) {
        this.baseUri = baseUri;
    }

    public ApiDefinitionReferenceDPDS parse(String rawContent, String mediaType) throws DeserializationException, FetchException {
        ApiDefinitionReferenceDPDS api = new ApiDefinitionReferenceDPDS();
        api.setBaseUri(baseUri);
        api.setRawContent(rawContent);
        api.setEndpoints(extractEndpoints(rawContent, resolveMediaType(mediaType)));
        return api;
    }

    private List<ApiDefinitionEndpointDPDS> extractEndpoints(String rawContent, String mediaType) throws DeserializationException, FetchException {
        List<ApiDefinitionEndpointDPDS> endpoints = new ArrayList<ApiDefinitionEndpointDPDS>();

        if (!"application/json".equalsIgnoreCase(mediaType)) {
            throw new DeserializationException("Impossible to parse api definition encoded in [" + mediaType + "]");
        }

        ObjectMapper mapper = ObjectMapperFactory.JSON_MAPPER;
        try {
            ObjectNode apiNode = (ObjectNode) mapper.readTree(rawContent);
            if (!apiNode.has("datastoreapi")) return null;
            if (!apiNode.at("/schema/tables").isMissingNode()) {
                ArrayNode tables = (ArrayNode) apiNode.at("/schema/tables");
                for (int i = 0; i < tables.size(); i++) {
                    ApiDefinitionEndpointDPDS endpoint;
                    String name = null, schemaMediaType = null, tableSchema = null;
                    ObjectNode table = (ObjectNode) tables.get(i);
                    if (table.get("name") != null) {
                        name = table.get("name").asText();
                    } else {
                        name = "endpoint-" + (i + 1);
                    }
                    if (!table.at("/definition/$ref").isMissingNode()) {
                        tableSchema = fetcher.fetchRelativeResource(baseUri, new URI(table.at("/definition/$ref").asText()));
                    } else {
                        tableSchema = mapper.writeValueAsString(table.at("/definition"));
                    }

                    if (!table.at("/definition/mediaType").isMissingNode()) {
                        schemaMediaType = table.at("/definition/mediaType").asText();
                    } else {
                        schemaMediaType = "application/json";
                    }
                    endpoint = new ApiDefinitionEndpointDPDS();
                    endpoint.setName(name);
                    ApiDefinitionEndpointDPDS.Schema schema = new ApiDefinitionEndpointDPDS.Schema();
                    schema.setMediaType(schemaMediaType);
                    schema.setContent(tableSchema);
                    endpoint.setSchema(schema);
                    endpoints.add(endpoint);
                }
            } else if (!apiNode.at("/schema").isMissingNode()) {
                JsonNode rawSchema = apiNode.at("/schema");
                ApiDefinitionEndpointDPDS endpoint;
                String name = null, schemaMediaType = null, realSchema = null;
                if (rawSchema.get("name") != null) {
                    name = rawSchema.get("name").asText();
                } else {
                    name = "endpoint-1";
                }
                realSchema = mapper.writeValueAsString(rawSchema);

                if (!rawSchema.at("/mediaType").isMissingNode()) {
                    schemaMediaType = rawSchema.at("/mediaType").asText();
                } else {
                    schemaMediaType = "application/json";
                }
                endpoint = new ApiDefinitionEndpointDPDS();
                endpoint.setName(name);
                ApiDefinitionEndpointDPDS.Schema schema = new ApiDefinitionEndpointDPDS.Schema();
                schema.setMediaType(schemaMediaType);
                schema.setContent(realSchema);
                endpoint.setSchema(schema);
                endpoints.add(endpoint);
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
}
