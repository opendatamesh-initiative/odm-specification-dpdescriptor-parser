package org.opendatamesh.dpds.extensions.visitorsimpl.interfaces.port;

import org.opendatamesh.dpds.extensions.visitorsimpl.ExtensionVisitor;
import org.opendatamesh.dpds.extensions.visitorsimpl.core.StandardDefinitionExtensionVisitorImpl;
import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.StandardDefinition;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.ExpectationsVisitor;

public class ExpectationsExtensionVisitor extends ExtensionVisitor implements ExpectationsVisitor {
    public ExpectationsExtensionVisitor(ExtensionVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(StandardDefinition standardDefinition) {
        extensionHandler.handleComponentBaseExtension(standardDefinition, StandardDefinition.class);
        extensionHandler.handleDefinition(standardDefinition);
        StandardDefinitionVisitor<ComponentBase> visitor = new StandardDefinitionExtensionVisitorImpl(this);
        if (standardDefinition.getExternalDocs() != null) {
            standardDefinition.getExternalDocs().accept(visitor);
        }
        if (standardDefinition.getDefinition() != null) {
            standardDefinition.getDefinition().accept(visitor);
        }
    }
}
