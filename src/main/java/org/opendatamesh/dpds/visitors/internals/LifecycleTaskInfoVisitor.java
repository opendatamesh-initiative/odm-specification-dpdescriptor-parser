package org.opendatamesh.dpds.visitors.internals;

import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.model.core.StandardDefinition;

public interface LifecycleTaskInfoVisitor {
    void visit(ExternalDocs externalDocs);

    void visit(StandardDefinition standardDefinition);
}
