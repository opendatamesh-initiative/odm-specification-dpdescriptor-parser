package org.opendatamesh.dpds.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.opendatamesh.dpds.visitors.DataProductVersionVisitor;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.PortVisitor;
import org.opendatamesh.dpds.visitors.internals.ApplicationComponentVisitor;
import org.opendatamesh.dpds.visitors.internals.InfrastructuralComponentVisitor;
import org.opendatamesh.dpds.visitors.internals.LifecycleTaskInfoVisitor;


public class ExternalDocs extends ComponentBase {

    private String description;
    @JsonProperty("$href")
    private String href;

    public void accept(DataProductVersionVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(LifecycleTaskInfoVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(ApplicationComponentVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(InfrastructuralComponentVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(PortVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(StandardDefinitionVisitor visitor) {
        visitor.visit(this);
    }

    public String getDescription() {
        return this.description;
    }

    public String getHref() {
        return this.href;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
