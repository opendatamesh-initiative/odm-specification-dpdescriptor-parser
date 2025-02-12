package org.opendatamesh.dpds.visitors.internals;

import org.opendatamesh.dpds.model.internals.ApplicationComponentDPDS;
import org.opendatamesh.dpds.model.internals.InfrastructuralComponentDPDS;
import org.opendatamesh.dpds.model.internals.LifecycleInfoDPDS;

public interface InternalComponentsDPDSVisitor {
    void visit(ApplicationComponentDPDS applicationComponentDPDS);

    void visit(InfrastructuralComponentDPDS infrastructuralComponentDPDS);

    void visit(LifecycleInfoDPDS lifecycleInfoDPDS);
}
