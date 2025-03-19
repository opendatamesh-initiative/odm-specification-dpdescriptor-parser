package org.opendatamesh.dpds.referencehandler.visitorsimpl.internals;

import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.referencehandler.visitorsimpl.RefVisitor;
import org.opendatamesh.dpds.visitors.internals.InfrastructuralComponentVisitor;

public class InfrastructuralComponentRefVisitor extends RefVisitor implements InfrastructuralComponentVisitor {
    public InfrastructuralComponentRefVisitor(RefVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(ExternalDocs externalDocs) {
        referenceHandler.handleComponentBaseReference(externalDocs);
    }
}
