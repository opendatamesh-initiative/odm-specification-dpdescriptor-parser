package org.opendatamesh.dpds.visitors.internals;

import org.opendatamesh.dpds.model.core.ExternalDocs;

public interface InfrastructuralComponentVisitor {
    void visit(ExternalDocs externalDocs);
}
