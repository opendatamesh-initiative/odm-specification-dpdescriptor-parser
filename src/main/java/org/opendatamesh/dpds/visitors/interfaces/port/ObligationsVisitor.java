package org.opendatamesh.dpds.visitors.interfaces.port;

import org.opendatamesh.dpds.model.core.StandardDefinition;

public interface ObligationsVisitor {
    void visit(StandardDefinition standardDefinition);
}
