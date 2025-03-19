package org.opendatamesh.dpds.visitors.internals;

import org.opendatamesh.dpds.model.core.ExternalDocs;

public interface ApplicationComponentVisitor {
    void visit(ExternalDocs externalDocs);
}
