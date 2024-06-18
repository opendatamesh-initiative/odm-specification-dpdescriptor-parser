package org.opendatamesh.dpds.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
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

class AsyncApiParser implements ApiParser {

    private static final Logger logger = LoggerFactory.getLogger(AsyncApiParser.class);
    protected URI baseUri;
    protected UriFetcher fetcher;

    public AsyncApiParser(URI baseUri) {
        this.baseUri = baseUri;
        this.fetcher = new UriFetcher();
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
            if (!apiNode.has("asyncapi")) return null;
            if (apiNode.get("channels") != null) {
                ObjectNode channels = (ObjectNode) apiNode.get("channels");
                Iterator<String> channelNames = channels.fieldNames();
                int i = 0;
                while (channelNames.hasNext()) {
                    ObjectNode channel = (ObjectNode) channels.get(channelNames.next());
                    ObjectNode subscribeOperation = (ObjectNode) channel.get("subscribe");
                    if (subscribeOperation == null) continue;
                    ObjectNode message = (ObjectNode) subscribeOperation.get("message");
                    if (message == null) continue;
                    JsonNode payload = message.get("payload");
                    if (payload == null) continue;


                    ApiDefinitionEndpointDPDS endpoint;
                    String name = null, schemaMediaType = null, operationSchema = null;

                    if (subscribeOperation.get("operationId") != null) {
                        name = subscribeOperation.get("operationId").asText();
                    } else {
                        name = "endpoint-" + (i + 1);
                    }

                    if (payload.get("$ref") != null) {
                        operationSchema = fetcher.fetchRelativeResource(baseUri, new URI(payload.get("$ref").asText()));
                    } else {
                        operationSchema = payload.toPrettyString();
                    }

                    if (message.get("contentType") != null) {
                        schemaMediaType = message.get("contentType").asText();
                    } else {
                        schemaMediaType = "application/json";
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
}
