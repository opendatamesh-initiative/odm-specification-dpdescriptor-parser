package org.opendatamesh.dpds.model.core;

import org.opendatamesh.dpds.visitors.components.ComponentsVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.ExpectationsVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.ObligationsVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.PromisesVisitor;
import org.opendatamesh.dpds.visitors.internals.LifecycleTaskInfoVisitor;

import java.util.ArrayList;
import java.util.List;


public class StandardDefinition extends ComponentBase {

    private String id;
    private String fullyQualifiedName;
    private String entityType;
    private String name;
    private String version;
    private String displayName;
    private String description;

    private String specification;
    private String specificationVersion;

    private ComponentBase definition;

    private String componentGroup;
    private List<String> tags = new ArrayList<>();
    private ExternalDocs externalDocs;

    public void accept(PromisesVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(LifecycleTaskInfoVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(ExpectationsVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(ObligationsVisitor visitor) {
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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getSpecificationVersion() {
        return specificationVersion;
    }

    public void setSpecificationVersion(String specificationVersion) {
        this.specificationVersion = specificationVersion;
    }

    public ComponentBase getDefinition() {
        return definition;
    }

    public void setDefinition(ComponentBase definition) {
        this.definition = definition;
    }

    public String getComponentGroup() {
        return componentGroup;
    }

    public void setComponentGroup(String componentGroup) {
        this.componentGroup = componentGroup;
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
