package org.opendatamesh.dpds.model.core;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import org.opendatamesh.dpds.model.definitions.DefinitionReferenceDPDS;
import org.opendatamesh.dpds.visitors.core.ComponentsDPDSVisitor;
import org.opendatamesh.dpds.visitors.interfaces.PromisesDPDSVisitor;
import org.opendatamesh.dpds.visitors.internals.LifecycleTaskInfoDPDSVisitor;

import java.util.Map;


@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StandardDefinitionDPDS extends ComponentDPDS {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @JsonProperty("specification")
    @Schema(description = "Standard Definition specification", required = true)
    private String specification;

    @JsonProperty("specificationVersion")
    @Schema(description = "Standard Definition specification version", required = true)
    private String specificationVersion;

    @Schema(description = "Definition Reference object of the Standard Definition", required = true)
    private Object definition;

    public StandardDefinitionDPDS() {
    }

    public void accept(PromisesDPDSVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(LifecycleTaskInfoDPDSVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(ComponentsDPDSVisitor visitor) {
        visitor.visit(this);
    }


    public String getSpecification() {
        return this.specification;
    }

    public String getSpecificationVersion() {
        return this.specificationVersion;
    }

    @Deprecated(since = "1.0.6", forRemoval = true)
    @JsonIgnore
    public DefinitionReferenceDPDS getDefinition() {
        if (isDefinitionJson()) {
            return null;
        }
        return (DefinitionReferenceDPDS) this.definition;
    }

    @JsonIgnore
    public DefinitionReferenceDPDS getDefinitionReference() {
        if (isDefinitionJson()) {
            return null;
        }
        return (DefinitionReferenceDPDS) this.definition;
    }

    @JsonIgnore
    public ObjectNode getDefinitionJson() {
        if (isDefinitionReference()) {
            return null;
        }
        return (ObjectNode) this.definition;
    }

    public boolean isDefinitionReference() {
        return this.definition instanceof DefinitionReferenceDPDS;
    }

    public boolean isDefinitionJson() {
        return this.definition instanceof ObjectNode;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }


    public void setSpecificationVersion(String specificationVersion) {
        this.specificationVersion = specificationVersion;
    }


    @JsonSetter("definition")
    public void setDefinition(Object definition) {
        if (definition instanceof DefinitionReferenceDPDS || definition instanceof ObjectNode) {
            this.definition = definition;
        } else if (definition instanceof Map) {
            try {
                this.definition = new ObjectMapper().convertValue(definition, DefinitionReferenceDPDS.class);
            } catch (Exception e) {
                this.definition = new ObjectMapper().valueToTree(definition);
            }
        }
    }

    @JsonGetter("definition")
    public Object getUntypedDefinition() {
        return this.definition;
    }

    public void setDefinition(DefinitionReferenceDPDS definition) {
        this.definition = definition;
    }

    public void setDefinition(ObjectNode definition) {
        this.definition = definition;
    }


    public String toString() {
        return "StandardDefinitionDPDS(specification=" + this.getSpecification() + ", specificationVersion=" + this.getSpecificationVersion() + ", definition=" + this.getDefinition() + ")";
    }
}
