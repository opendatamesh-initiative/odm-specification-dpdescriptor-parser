package org.opendatamesh.dpds.visitors.core;


import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.ExternalDocs;

public interface StandardDefinitionVisitor<T extends ComponentBase> extends ComponentBaseVisitor<T> {
    void visit(ExternalDocs externalDocs);
}
