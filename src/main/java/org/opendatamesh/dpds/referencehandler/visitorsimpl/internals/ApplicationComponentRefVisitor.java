package org.opendatamesh.dpds.referencehandler.visitorsimpl.internals;

import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.referencehandler.visitorsimpl.RefVisitor;
import org.opendatamesh.dpds.visitors.internals.ApplicationComponentVisitor;

public class ApplicationComponentRefVisitor extends RefVisitor implements ApplicationComponentVisitor {
    public ApplicationComponentRefVisitor(RefVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(ExternalDocs externalDocs) {
        referenceHandler.handleComponentBaseReference(externalDocs);
    }
}
