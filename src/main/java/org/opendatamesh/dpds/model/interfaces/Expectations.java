package org.opendatamesh.dpds.model.interfaces;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.StandardDefinition;
import org.opendatamesh.dpds.visitors.interfaces.port.PortVisitor;

public class Expectations extends ComponentBase {

    private StandardDefinition audience;
    private StandardDefinition usage;

    public void accept(PortVisitor visitor) {
        visitor.visit(this);
    }

    public StandardDefinition getAudience() {
        return audience;
    }

    public void setAudience(StandardDefinition audience) {
        this.audience = audience;
    }

    public StandardDefinition getUsage() {
        return usage;
    }

    public void setUsage(StandardDefinition usage) {
        this.usage = usage;
    }
}
