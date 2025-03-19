package org.opendatamesh.dpds.model.core;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ComponentBase {

    private Map<String, JsonNode> additionalProperties = new HashMap<>();

    //Multi-file descriptor handling
    @JsonProperty("$ref")
    private String ref;
    private String mediaType;
    private URI baseUri;

    @JsonAnySetter
    public void addAdditionalProperty(String key, JsonNode value) {
        additionalProperties.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, JsonNode> getAdditionalProperties() {
        return additionalProperties;
    }

    @JsonIgnore
    public void setAdditionalProperties(Map<String, JsonNode> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public URI getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(URI baseUri) {
        this.baseUri = baseUri;
    }
}
