package org.opendatamesh.dpds.referencehandler.visitorsimpl.info;

import org.opendatamesh.dpds.model.info.ContactPoint;
import org.opendatamesh.dpds.model.info.Owner;
import org.opendatamesh.dpds.referencehandler.visitorsimpl.RefVisitor;
import org.opendatamesh.dpds.visitors.info.InfoVisitor;

public class InfoRefVisitorImpl extends RefVisitor implements InfoVisitor {

    public InfoRefVisitorImpl(RefVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(Owner owner) {
        referenceHandler.handleComponentBaseReference(owner);
    }

    @Override
    public void visit(ContactPoint contactPoint) {
        referenceHandler.handleComponentBaseReference(contactPoint);
    }
}
