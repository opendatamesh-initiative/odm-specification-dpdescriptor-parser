package org.opendatamesh.dpds.visitors.interfaces.port;

import org.opendatamesh.dpds.model.core.StandardDefinition;

public interface ExpectationsVisitor {
    void visit(StandardDefinition standardDefinition);
}
