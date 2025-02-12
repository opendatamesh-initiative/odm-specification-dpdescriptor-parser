package org.opendatamesh.dpds.model.definitions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionDPDSVisitor;

import java.net.URI;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiDefinitionReferenceDPDS extends DefinitionReferenceDPDS {
    @JsonProperty("baseUri")
    URI baseUri;

    @JsonProperty("endpoints")
    List<ApiDefinitionEndpointDPDS> endpoints;

    @Override
    public void accept(StandardDefinitionDPDSVisitor visitor) {
        super.accept(visitor);
        visitor.visit(this);
    }
}
