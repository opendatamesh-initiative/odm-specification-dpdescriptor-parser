package org.opendatamesh.dpds.visitors.core;

import org.opendatamesh.dpds.model.core.StandardDefinitionDPDS;
import org.opendatamesh.dpds.model.interfaces.PortDPDS;
import org.opendatamesh.dpds.model.internals.ApplicationComponentDPDS;
import org.opendatamesh.dpds.model.internals.InfrastructuralComponentDPDS;

public interface ComponentsDPDSVisitor {
    void visit(PortDPDS portDPDS);

    void visit(ApplicationComponentDPDS applicationComponentDPDS);

    void visit(InfrastructuralComponentDPDS infrastructuralComponentDPDS);

    void visit(StandardDefinitionDPDS standardDefinitionDPDS);
}
