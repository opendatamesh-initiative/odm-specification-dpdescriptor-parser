package org.opendatamesh.dpds.extensions.visitorsimpl.info;

import org.opendatamesh.dpds.extensions.visitorsimpl.ExtensionVisitor;
import org.opendatamesh.dpds.model.info.ContactPoint;
import org.opendatamesh.dpds.model.info.Info;
import org.opendatamesh.dpds.model.info.Owner;
import org.opendatamesh.dpds.visitors.info.InfoVisitor;

public class InfoExtensionVisitorImpl extends ExtensionVisitor implements InfoVisitor {

    public InfoExtensionVisitorImpl(ExtensionVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(Owner owner) {
        extensionHandler.handleComponentBaseExtension(owner, Info.class);
    }

    @Override
    public void visit(ContactPoint contactPoint) {
        extensionHandler.handleComponentBaseExtension(contactPoint, Info.class);
    }
}
