package org.opendatamesh.dpds.datastoreapi.v1.model;

import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiSchemaVisitor;
import org.opendatamesh.dpds.model.core.ComponentBase;

public class DataStoreApiStandardDefinitionObject extends ComponentBase {

    private String id;
    private String name;
    private String version;
    private String description;
    private String specification;
    private String specificationVersion;
    private ComponentBase definition;
    private DataStoreApiExternalResourceObject externalDocs;

    public DataStoreApiStandardDefinitionObject() {
        //DO NOTHING
    }

    public void accept(DataStoreApiSchemaVisitor visitor) {
        visitor.visit(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public DataStoreApiExternalResourceObject getExternalDocs() {
        return externalDocs;
    }

    public void setExternalDocs(DataStoreApiExternalResourceObject externalDocs) {
        this.externalDocs = externalDocs;
    }
}
