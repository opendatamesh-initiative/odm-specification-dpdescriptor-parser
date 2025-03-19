package org.opendatamesh.dpds.referencehandler.visitorsimpl.interfaces;

import org.opendatamesh.dpds.referencehandler.visitorsimpl.RefVisitor;
import org.opendatamesh.dpds.model.interfaces.Port;
import org.opendatamesh.dpds.referencehandler.visitorsimpl.interfaces.port.PortRefVisitor;
import org.opendatamesh.dpds.visitors.interfaces.InterfaceComponentsVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.PortVisitor;

public class InterfaceComponentsRefVisitor extends RefVisitor implements InterfaceComponentsVisitor {
    public InterfaceComponentsRefVisitor(RefVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(Port port) {
        referenceHandler.handleComponentBaseReference(port);
        PortVisitor visitor = new PortRefVisitor(this);
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
