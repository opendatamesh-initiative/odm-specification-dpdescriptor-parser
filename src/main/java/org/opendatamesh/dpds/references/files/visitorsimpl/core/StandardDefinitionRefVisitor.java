package org.opendatamesh.dpds.references.files.visitorsimpl.core;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.references.files.visitorsimpl.RefVisitor;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionVisitor;

public class StandardDefinitionRefVisitor extends RefVisitor implements StandardDefinitionVisitor {

    public StandardDefinitionRefVisitor(RefVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(ExternalDocs externalDocs) {
        referenceFileHandler.handleComponentBaseReference(externalDocs);
    }

    @Override
    public void visit(ComponentBase definition) {
        referenceFileHandler.handleComponentBaseReference(definition);
    }
}
