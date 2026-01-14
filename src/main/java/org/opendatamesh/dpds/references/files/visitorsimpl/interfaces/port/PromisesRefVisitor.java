package org.opendatamesh.dpds.references.files.visitorsimpl.interfaces.port;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.StandardDefinition;
import org.opendatamesh.dpds.references.files.visitorsimpl.RefVisitor;
import org.opendatamesh.dpds.references.files.visitorsimpl.core.StandardDefinitionRefVisitor;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.PromisesVisitor;

public class PromisesRefVisitor extends RefVisitor implements PromisesVisitor {
    public PromisesRefVisitor(RefVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(StandardDefinition standardDefinition) {
        referenceFileHandler.handleComponentBaseReference(standardDefinition);
        StandardDefinitionVisitor<ComponentBase> visitor = new StandardDefinitionRefVisitor(this);
        if (standardDefinition.getExternalDocs() != null) {
            standardDefinition.getExternalDocs().accept(visitor);
        }
        if (standardDefinition.getDefinition() != null) {
            standardDefinition.getDefinition().accept(visitor);
        }
    }
}
