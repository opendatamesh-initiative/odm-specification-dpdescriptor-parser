package org.opendatamesh.dpds.model.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.opendatamesh.dpds.visitors.DataProductVersionDPDSVisitor;
import org.opendatamesh.dpds.visitors.core.ComponentDPDSVisitor;
import org.opendatamesh.dpds.visitors.core.SpecificationExtensionPointDPDSVisitor;
import org.opendatamesh.dpds.visitors.internals.LifecycleTaskInfoDPDSVisitor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalResourceDPDS {

    @JsonProperty("description")
    @Schema(description = "External Resource description", required = true)
    private String description;

    @JsonProperty("mediaType")
    @Schema(description = "Media Type of the External Resource", required = true)
    private String mediaType;

    @JsonProperty("$href")
    @Schema(description = "URL of the External Resource", required = true)
    private String href;

    public void accept(DataProductVersionDPDSVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(SpecificationExtensionPointDPDSVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(LifecycleTaskInfoDPDSVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(ComponentDPDSVisitor visitor) {
        visitor.visit(this);
    }
}
