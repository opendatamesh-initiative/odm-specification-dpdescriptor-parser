package org.opendatamesh.dpds.model.internals;

import com.fasterxml.jackson.databind.JsonNode;
import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.model.core.StandardDefinition;
import org.opendatamesh.dpds.visitors.internals.InternalComponentsVisitor;

public class LifecycleTaskInfo extends ComponentBase {

    private String name;
    private Integer order;
    private ExternalDocs service;
    private StandardDefinition template;
    private JsonNode configurations;

    public void accept(InternalComponentsVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public ExternalDocs getService() {
        return service;
    }

    public void setService(ExternalDocs service) {
        this.service = service;
    }

    public StandardDefinition getTemplate() {
        return template;
    }

    public void setTemplate(StandardDefinition template) {
        this.template = template;
    }

    public JsonNode getConfigurations() {
        return configurations;
    }

    public void setConfigurations(JsonNode configurations) {
        this.configurations = configurations;
    }
}
