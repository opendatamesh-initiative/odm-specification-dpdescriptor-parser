package org.opendatamesh.dpds.model.interfaces;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.visitors.DataProductVersionVisitor;

import java.util.ArrayList;
import java.util.List;

public class InterfaceComponents extends ComponentBase {

    private List<Port> inputPorts = new ArrayList<>();
    private List<Port> outputPorts = new ArrayList<>();
    private List<Port> discoveryPorts = new ArrayList<>();
    private List<Port> observabilityPorts = new ArrayList<>();
    private List<Port> controlPorts = new ArrayList<>();

    public void accept(DataProductVersionVisitor visitor) {
        visitor.visit(this);
    }

    public List<Port> getInputPorts() {
        return inputPorts;
    }

    public void setInputPorts(List<Port> inputPorts) {
        this.inputPorts = inputPorts;
    }

    public List<Port> getOutputPorts() {
        return outputPorts;
    }

    public void setOutputPorts(List<Port> outputPorts) {
        this.outputPorts = outputPorts;
    }

    public List<Port> getDiscoveryPorts() {
        return discoveryPorts;
    }

    public void setDiscoveryPorts(List<Port> discoveryPorts) {
        this.discoveryPorts = discoveryPorts;
    }

    public List<Port> getObservabilityPorts() {
        return observabilityPorts;
    }

    public void setObservabilityPorts(List<Port> observabilityPorts) {
        this.observabilityPorts = observabilityPorts;
    }

    public List<Port> getControlPorts() {
        return controlPorts;
    }

    public void setControlPorts(List<Port> controlPorts) {
        this.controlPorts = controlPorts;
    }
}
