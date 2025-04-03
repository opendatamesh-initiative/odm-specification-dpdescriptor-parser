package org.opendatamesh.dpds.extensions.visitorsimpl.internals;

import org.opendatamesh.dpds.extensions.visitorsimpl.ExtensionVisitor;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.visitors.internals.InfrastructuralComponentVisitor;

public class InfrastructuralComponentExtensionVisitorImpl extends ExtensionVisitor implements InfrastructuralComponentVisitor {
    public InfrastructuralComponentExtensionVisitorImpl(ExtensionVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(ExternalDocs externalDocs) {
        extensionHandler.handleComponentBaseExtension(externalDocs, ExternalDocs.class);
    }
}
