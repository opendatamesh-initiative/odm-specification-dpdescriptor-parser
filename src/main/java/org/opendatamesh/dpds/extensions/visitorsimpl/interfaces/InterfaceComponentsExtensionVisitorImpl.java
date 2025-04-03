package org.opendatamesh.dpds.extensions.visitorsimpl.interfaces;

import org.opendatamesh.dpds.extensions.visitorsimpl.ExtensionVisitor;
import org.opendatamesh.dpds.extensions.visitorsimpl.interfaces.port.PortExtensionVisitor;
import org.opendatamesh.dpds.model.interfaces.Port;
import org.opendatamesh.dpds.visitors.interfaces.InterfaceComponentsVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.PortVisitor;

public class InterfaceComponentsExtensionVisitorImpl extends ExtensionVisitor implements InterfaceComponentsVisitor {
    public InterfaceComponentsExtensionVisitorImpl(ExtensionVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(Port port) {
        extensionHandler.handleComponentBaseExtension(port, Port.class);
        PortVisitor visitor = new PortExtensionVisitor(this);
        if (port.getPromises() != null) {
            visitor.visit(port.getPromises());
        }
        if (port.getExpectations() != null) {
            visitor.visit(port.getExpectations());
        }
        if (port.getObligations() != null) {
            visitor.visit(port.getObligations());
        }
    }
}
