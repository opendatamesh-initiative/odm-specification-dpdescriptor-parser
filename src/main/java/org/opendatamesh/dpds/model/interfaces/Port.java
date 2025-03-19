package org.opendatamesh.dpds.model.interfaces;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.visitors.components.ComponentsVisitor;
import org.opendatamesh.dpds.visitors.interfaces.InterfaceComponentsVisitor;

import java.util.ArrayList;
import java.util.List;


public class Port extends ComponentBase {

    private String id;
    private String fullyQualifiedName;
    private String entityType;
    private String name;
    private String version;
    private String displayName;
    private String description;
    private String componentGroup;

    private Promises promises;
    private Expectations expectations;
    private Obligations obligations;

    private List<String> tags = new ArrayList<>();
    private ExternalDocs externalDocs;

    public void accept(InterfaceComponentsVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(ComponentsVisitor visitor) {
        visitor.visit(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullyQualifiedName() {
        return fullyQualifiedName;
    }

    public void setFullyQualifiedName(String fullyQualifiedName) {
        this.fullyQualifiedName = fullyQualifiedName;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComponentGroup() {
        return componentGroup;
    }

    public void setComponentGroup(String componentGroup) {
        this.componentGroup = componentGroup;
    }

    public Promises getPromises() {
        return promises;
    }

    public void setPromises(Promises promises) {
        this.promises = promises;
    }

    public Expectations getExpectations() {
        return expectations;
    }

    public void setExpectations(Expectations expectations) {
        this.expectations = expectations;
    }

    public Obligations getObligations() {
        return obligations;
    }

    public void setObligations(Obligations obligations) {
        this.obligations = obligations;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public ExternalDocs getExternalDocs() {
        return externalDocs;
    }

    public void setExternalDocs(ExternalDocs externalDocs) {
        this.externalDocs = externalDocs;
    }
}

