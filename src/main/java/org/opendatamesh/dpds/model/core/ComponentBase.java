package org.opendatamesh.dpds.model.core;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.opendatamesh.dpds.visitors.core.ComponentBaseVisitor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ComponentBase implements Serializable {

    private Map<String, JsonNode> additionalProperties = new HashMap<>();

    @JsonIgnore
    private Map<String, ComponentBase> parsedProperties = new HashMap<>();

    //Multi-file descriptor handling
    @JsonProperty("$ref")
    private String ref;
    private String mediaType;

    @Deprecated
    private URI baseUri;

    public void accept(ComponentBaseVisitor visitor) {
        visitor.visit(this);
    }

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

    @JsonIgnore
    public <T extends ComponentBase> void addParsedProperty(String key, T value) {
        parsedProperties.put(key, value);
    }

    @JsonIgnore
    public Map<String, ComponentBase> getParsedProperties() {
        return parsedProperties;
    }

    @JsonIgnore
    public void setParsedProperties(Map<String, ComponentBase> parsedProperties) {
        this.parsedProperties = parsedProperties;
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

    @Deprecated
    public URI getBaseUri() {
        return baseUri;
    }

    @Deprecated
    public void setBaseUri(URI baseUri) {
        this.baseUri = baseUri;
    }


    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(new ObjectMapper().writeValueAsString(additionalProperties));
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        String json = (String) in.readObject();
        additionalProperties = new ObjectMapper().readValue(json, new TypeReference<Map<String, JsonNode>>() {});
    }
}
