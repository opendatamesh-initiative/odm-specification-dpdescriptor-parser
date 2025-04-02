package org.opendatamesh.dpds.references.files.visitorsimpl.internals;

import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.references.files.visitorsimpl.RefVisitor;
import org.opendatamesh.dpds.visitors.internals.InfrastructuralComponentVisitor;

public class InfrastructuralComponentRefVisitor extends RefVisitor implements InfrastructuralComponentVisitor {
    public InfrastructuralComponentRefVisitor(RefVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(ExternalDocs externalDocs) {
        referenceFileHandler.handleComponentBaseReference(externalDocs);
    }
}
