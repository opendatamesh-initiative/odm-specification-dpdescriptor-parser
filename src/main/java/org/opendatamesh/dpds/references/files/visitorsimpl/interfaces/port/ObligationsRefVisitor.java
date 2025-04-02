package org.opendatamesh.dpds.references.files.visitorsimpl.interfaces.port;

import org.opendatamesh.dpds.model.core.StandardDefinition;
import org.opendatamesh.dpds.references.files.visitorsimpl.RefVisitor;
import org.opendatamesh.dpds.references.files.visitorsimpl.core.StandardDefinitionRefVisitor;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.ObligationsVisitor;

public class ObligationsRefVisitor extends RefVisitor implements ObligationsVisitor {
    public ObligationsRefVisitor(RefVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(StandardDefinition standardDefinition) {
        referenceFileHandler.handleComponentBaseReference(standardDefinition);
        StandardDefinitionVisitor visitor = new StandardDefinitionRefVisitor(this);
        if (standardDefinition.getExternalDocs() != null) {
            visitor.visit(standardDefinition.getExternalDocs());
        }
        if (standardDefinition.getDefinition() != null) {
            visitor.visit(standardDefinition.getDefinition());
        }
    }
}
