package org.opendatamesh.dpds.extensions.visitorsimpl.interfaces.port;

import org.opendatamesh.dpds.extensions.visitorsimpl.ExtensionVisitor;
import org.opendatamesh.dpds.extensions.visitorsimpl.core.StandardDefinitionExtensionVisitorImpl;
import org.opendatamesh.dpds.model.core.StandardDefinition;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.ObligationsVisitor;

public class ObligationsExtensionVisitor extends ExtensionVisitor implements ObligationsVisitor {
    public ObligationsExtensionVisitor(ExtensionVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(StandardDefinition standardDefinition) {
        extensionHandler.handleComponentBaseExtension(standardDefinition, StandardDefinition.class);
        extensionHandler.handleDefinition(standardDefinition);
        StandardDefinitionVisitor visitor = new StandardDefinitionExtensionVisitorImpl(this);
        if (standardDefinition.getExternalDocs() != null) {
            visitor.visit(standardDefinition.getExternalDocs());
        }
        if (standardDefinition.getDefinition() != null) {
            visitor.visit(standardDefinition.getDefinition());
        }
    }
}
