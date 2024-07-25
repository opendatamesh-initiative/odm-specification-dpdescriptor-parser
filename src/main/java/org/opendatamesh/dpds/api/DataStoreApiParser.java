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
import java.util.Collections;
import java.util.List;

class DataStoreApiParser implements ApiParser {
    private static final Logger logger = LoggerFactory.getLogger(DataStoreApiParser.class);
    protected URI baseUri;
    protected UriFetcher fetcher = new UriFetcher();

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
        List<ApiDefinitionEndpointDPDS> endpoints = new ArrayList<>();

        if (!"application/json".equalsIgnoreCase(mediaType)) {
            throw new DeserializationException("Impossible to parse api definition encoded in [" + mediaType + "]");
        }

        ObjectMapper mapper = ObjectMapperFactory.JSON_MAPPER;
        try {
            ObjectNode apiNode = (ObjectNode) mapper.readTree(rawContent);
            if (apiNode == null || !apiNode.has("datastoreapi")) return Collections.emptyList();

            if (!apiNode.at("/schema/tables").isMissingNode()) {
                ArrayNode tables = (ArrayNode) apiNode.at("/schema/tables");
                if (tables != null) {
                    for (int i = 0; i < tables.size(); i++) {
                        ApiDefinitionEndpointDPDS endpoint = new ApiDefinitionEndpointDPDS();
                        String name = null;
                        String schemaMediaType = null;
                        String tableSchema = null;
                        ObjectNode table = (ObjectNode) tables.get(i);

                        if (table != null) {
                            JsonNode nameNode = table.get("name");
                            if (nameNode != null) {
                                name = nameNode.asText();
                            } else {
                                name = "endpoint-" + (i + 1);
                            }

                            JsonNode refNode = table.at("/definition/$ref");
                            if (!refNode.isMissingNode()) {
                                tableSchema = fetcher.fetchRelativeResource(baseUri, new URI(refNode.asText()));
                            } else {
                                JsonNode definitionNode = table.at("/definition");
                                if (definitionNode != null) {
                                    tableSchema = mapper.writeValueAsString(definitionNode);
                                }
                            }

                            JsonNode mediaTypeNode = table.at("/definition/mediaType");
                            if (!mediaTypeNode.isMissingNode()) {
                                schemaMediaType = mediaTypeNode.asText();
                            } else {
                                schemaMediaType = "application/json";
                            }

                            if (name != null && tableSchema != null && schemaMediaType != null) {
                                ApiDefinitionEndpointDPDS.Schema schema = new ApiDefinitionEndpointDPDS.Schema();
                                schema.setMediaType(schemaMediaType);
                                schema.setContent(tableSchema);

                                endpoint.setName(name);
                                endpoint.setSchema(schema);
                                endpoints.add(endpoint);
                            }
                        }
                    }
                }
            } else if (!apiNode.at("/schema").isMissingNode()) {
                JsonNode rawSchema = apiNode.at("/schema");
                if (rawSchema != null) {
                    ApiDefinitionEndpointDPDS endpoint = new ApiDefinitionEndpointDPDS();
                    String name = null;
                    String schemaMediaType = null;
                    String realSchema = null;

                    JsonNode nameNode = rawSchema.get("name");
                    if (nameNode != null) {
                        name = nameNode.asText();
                    } else {
                        name = "endpoint-1";
                    }

                    realSchema = mapper.writeValueAsString(rawSchema);

                    JsonNode mediaTypeNode = rawSchema.at("/mediaType");
                    if (!mediaTypeNode.isMissingNode()) {
                        schemaMediaType = mediaTypeNode.asText();
                    } else {
                        schemaMediaType = "application/json";
                    }

                    if (name != null && realSchema != null && schemaMediaType != null) {
                        ApiDefinitionEndpointDPDS.Schema schema = new ApiDefinitionEndpointDPDS.Schema();
                        schema.setMediaType(schemaMediaType);
                        schema.setContent(realSchema);

                        endpoint.setName(name);
                        endpoint.setSchema(schema);
                        endpoints.add(endpoint);
                    }
                }
            }
        } catch (JsonProcessingException | URISyntaxException e) {
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
