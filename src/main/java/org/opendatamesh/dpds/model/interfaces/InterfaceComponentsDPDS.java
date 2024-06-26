package org.opendatamesh.dpds.model.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.opendatamesh.dpds.model.core.EntityTypeDPDS;
import org.opendatamesh.dpds.model.core.ComponentContainerDPDS;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "inputPorts", "outputPorts",  "discoveryPorts", "observabilityPorts", "controlPorts"})
public class InterfaceComponentsDPDS extends ComponentContainerDPDS{

    @JsonProperty("inputPorts")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(description = "List of the input Ports of the Interface Components", required = true)
    private List<PortDPDS> inputPorts = new ArrayList<PortDPDS>();

    @JsonProperty("outputPorts")
    @Schema(description = "List of the output Ports of the Interface Components", required = true)
    private List<PortDPDS> outputPorts = new ArrayList<PortDPDS>();

    @JsonProperty("discoveryPorts")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(description = "List of the discovery Ports of the Interface Components", required = true)
    private List<PortDPDS> discoveryPorts = new ArrayList<PortDPDS>();

    @JsonProperty("observabilityPorts")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(description = "List of the observability Ports of the Interface Components", required = true)
    private List<PortDPDS> observabilityPorts = new ArrayList<PortDPDS>();

    @JsonProperty("controlPorts")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(description = "List of the control Ports of the Interface Components", required = true)
    private List<PortDPDS> controlPorts = new ArrayList<PortDPDS>();

    public boolean hasPorts(EntityTypeDPDS entityType) {
        List<PortDPDS> ports = getPortListByEntityType(entityType);
        return ports != null && !ports.isEmpty();
    }

    public List<PortDPDS> getPortListByEntityType(EntityTypeDPDS entityType) {
        switch (entityType) {
            case OUTPUTPORT:
                return outputPorts;
            case INPUTPORT:
                return inputPorts;
            case CONTROLPORT:
                return controlPorts;
            case DISCOVERYPORT:
                return discoveryPorts;
            case OBSERVABILITYPORT:
                return observabilityPorts;
            default:
                return null;
        }
    }
}
