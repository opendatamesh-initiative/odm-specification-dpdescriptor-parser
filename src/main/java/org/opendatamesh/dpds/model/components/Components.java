package org.opendatamesh.dpds.model.components;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.StandardDefinition;
import org.opendatamesh.dpds.model.interfaces.Port;
import org.opendatamesh.dpds.model.internals.ApplicationComponent;
import org.opendatamesh.dpds.model.internals.InfrastructuralComponent;
import org.opendatamesh.dpds.visitors.DataProductVersionVisitor;

import java.util.HashMap;
import java.util.Map;

public class Components extends ComponentBase {

    private Map<String, Port> inputPorts = new HashMap<>();
    private Map<String, Port> outputPorts = new HashMap<>();
    private Map<String, Port> discoveryPorts = new HashMap<>();
    private Map<String, Port> observabilityPorts = new HashMap<>();
    private Map<String, Port> controlPorts = new HashMap<>();

    private Map<String, ApplicationComponent> applicationComponents = new HashMap<>();
    private Map<String, InfrastructuralComponent> infrastructuralComponents = new HashMap<>();
    private Map<String, StandardDefinition> apis = new HashMap<>();
    private Map<String, StandardDefinition> templates = new HashMap<>();

    public void accept(DataProductVersionVisitor visitor) {
        visitor.visit(this);
    }

    public Map<String, Port> getInputPorts() {
        return inputPorts;
    }

    public void setInputPorts(Map<String, Port> inputPorts) {
        this.inputPorts = inputPorts;
    }

    public Map<String, Port> getOutputPorts() {
        return outputPorts;
    }

    public void setOutputPorts(Map<String, Port> outputPorts) {
        this.outputPorts = outputPorts;
    }

    public Map<String, Port> getDiscoveryPorts() {
        return discoveryPorts;
    }

    public void setDiscoveryPorts(Map<String, Port> discoveryPorts) {
        this.discoveryPorts = discoveryPorts;
    }

    public Map<String, Port> getObservabilityPorts() {
        return observabilityPorts;
    }

    public void setObservabilityPorts(Map<String, Port> observabilityPorts) {
        this.observabilityPorts = observabilityPorts;
    }

    public Map<String, Port> getControlPorts() {
        return controlPorts;
    }

    public void setControlPorts(Map<String, Port> controlPorts) {
        this.controlPorts = controlPorts;
    }

    public Map<String, ApplicationComponent> getApplicationComponents() {
        return applicationComponents;
    }

    public void setApplicationComponents(Map<String, ApplicationComponent> applicationComponents) {
        this.applicationComponents = applicationComponents;
    }

    public Map<String, InfrastructuralComponent> getInfrastructuralComponents() {
        return infrastructuralComponents;
    }

    public void setInfrastructuralComponents(Map<String, InfrastructuralComponent> infrastructuralComponents) {
        this.infrastructuralComponents = infrastructuralComponents;
    }

    public Map<String, StandardDefinition> getApis() {
        return apis;
    }

    public void setApis(Map<String, StandardDefinition> apis) {
        this.apis = apis;
    }

    public Map<String, StandardDefinition> getTemplates() {
        return templates;
    }

    public void setTemplates(Map<String, StandardDefinition> templates) {
        this.templates = templates;
    }
}
