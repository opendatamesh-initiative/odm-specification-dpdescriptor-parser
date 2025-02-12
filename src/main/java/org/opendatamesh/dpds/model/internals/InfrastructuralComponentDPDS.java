package org.opendatamesh.dpds.model.internals;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.opendatamesh.dpds.model.core.ComponentDPDS;
import org.opendatamesh.dpds.visitors.core.ComponentsDPDSVisitor;
import org.opendatamesh.dpds.visitors.internals.InternalComponentsDPDSVisitor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
public class InfrastructuralComponentDPDS extends ComponentDPDS {

    @JsonProperty("platform")
    @Schema(description = "Infrastructural Component platform", required = true)
    private String platform;
    
    @JsonProperty("infrastructureType")
    @Schema(description = "Infrastructural Component infrastructure type", required = true)
    private String infrastructureType;

    @JsonProperty("dependsOn")
    @Schema(description = "List of dependencies of the Infrastructural Component")
    private List<String> dependsOn = new ArrayList<String>();

    public void accept(InternalComponentsDPDSVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(ComponentsDPDSVisitor visitor) {
        visitor.visit(this);
    }
}
