package org.opendatamesh.dpds.model.internals;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.visitors.components.ComponentsVisitor;
import org.opendatamesh.dpds.visitors.internals.InternalComponentsVisitor;

import java.util.ArrayList;
import java.util.List;

public class ApplicationComponent extends ComponentBase {

    private String id;
    private String fullyQualifiedName;
    private String entityType;
    private String name;
    private String version;
    private String displayName;
    private String description;
    private String platform;
    private String applicationType;

    private List<String> consumesFrom = new ArrayList<>();
    private List<String> providesTo = new ArrayList<>();
    private List<String> dependsOn = new ArrayList<>();

    private String componentGroup;
    private List<String> tags = new ArrayList<>();
    private ExternalDocs externalDocs;

    public void accept(InternalComponentsVisitor visitor) {
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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public List<String> getConsumesFrom() {
        return consumesFrom;
    }

    public void setConsumesFrom(List<String> consumesFrom) {
        this.consumesFrom = consumesFrom;
    }

    public List<String> getProvidesTo() {
        return providesTo;
    }

    public void setProvidesTo(List<String> providesTo) {
        this.providesTo = providesTo;
    }

    public List<String> getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(List<String> dependsOn) {
        this.dependsOn = dependsOn;
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
