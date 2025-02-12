package org.opendatamesh.dpds.visitors.internals;

import org.opendatamesh.dpds.model.core.ExternalResourceDPDS;
import org.opendatamesh.dpds.model.core.StandardDefinitionDPDS;

public interface LifecycleTaskInfoDPDSVisitor {
    void visit(ExternalResourceDPDS externalResourceDPDS);

    void visit(StandardDefinitionDPDS standardDefinitionDPDS);
}
