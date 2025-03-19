package org.opendatamesh.dpds.visitors.core;


import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.ExternalDocs;

public interface StandardDefinitionVisitor {
    void visit(ExternalDocs externalDocs);

    void visit(ComponentBase definition);
}
