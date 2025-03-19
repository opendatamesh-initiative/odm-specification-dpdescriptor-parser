package org.opendatamesh.dpds.visitors.interfaces;

import org.opendatamesh.dpds.model.interfaces.Port;

public interface InterfaceComponentsVisitor {

    void visit(Port port);
}
