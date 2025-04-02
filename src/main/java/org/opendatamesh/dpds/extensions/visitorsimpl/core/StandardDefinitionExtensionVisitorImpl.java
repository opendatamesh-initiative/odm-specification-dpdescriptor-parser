package org.opendatamesh.dpds.extensions.visitorsimpl.core;

import org.opendatamesh.dpds.extensions.visitorsimpl.ExtensionVisitor;
import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.model.core.StandardDefinition;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionVisitor;

public class StandardDefinitionExtensionVisitorImpl extends ExtensionVisitor implements StandardDefinitionVisitor {

    public StandardDefinitionExtensionVisitorImpl(ExtensionVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(ExternalDocs externalDocs) {
        extensionHandler.handleComponentBaseExtension(externalDocs, StandardDefinition.class);
    }

    @Override
    public void visit(ComponentBase definition) {
        //DO NOTHING
    }
}
