package org.opendatamesh.dpds.visitors;

import org.opendatamesh.dpds.model.core.ComponentsDPDS;
import org.opendatamesh.dpds.model.core.ExternalResourceDPDS;
import org.opendatamesh.dpds.model.info.InfoDPDS;
import org.opendatamesh.dpds.model.interfaces.InterfaceComponentsDPDS;
import org.opendatamesh.dpds.model.internals.InternalComponentsDPDS;

public interface DataProductVersionDPDSVisitor {
    void visit(InfoDPDS infoDPDS);

    void visit(InterfaceComponentsDPDS interfaceComponentsDPDS);

    void visit(InternalComponentsDPDS internalComponentsDPDS);

    void visit(ComponentsDPDS componentsDPDS);

    void visit(ExternalResourceDPDS externalResourceDPDS);
}
