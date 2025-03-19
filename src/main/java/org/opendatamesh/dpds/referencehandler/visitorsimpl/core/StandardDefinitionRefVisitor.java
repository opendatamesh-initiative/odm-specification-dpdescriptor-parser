package org.opendatamesh.dpds.referencehandler.visitorsimpl.core;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.referencehandler.visitorsimpl.RefVisitor;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionVisitor;

public class StandardDefinitionRefVisitor extends RefVisitor implements StandardDefinitionVisitor {

    public StandardDefinitionRefVisitor(RefVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(ExternalDocs externalDocs) {
        referenceHandler.handleComponentBaseReference(externalDocs);
    }

    @Override
    public void visit(ComponentBase definition) {
        referenceHandler.handleComponentBaseReference(definition);
    }
}
