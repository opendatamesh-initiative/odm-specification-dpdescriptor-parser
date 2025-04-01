package org.opendatamesh.dpds.extensions.visitorsimpl.internals;

import org.opendatamesh.dpds.extensions.visitorsimpl.ExtensionVisitor;
import org.opendatamesh.dpds.extensions.visitorsimpl.core.StandardDefinitionExtensionVisitorImpl;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.model.core.StandardDefinition;
import org.opendatamesh.dpds.model.internals.LifecycleTaskInfo;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionVisitor;
import org.opendatamesh.dpds.visitors.internals.LifecycleTaskInfoVisitor;

public class LifecycleTaskInfoExtensionVisitorImpl extends ExtensionVisitor implements LifecycleTaskInfoVisitor {
    public LifecycleTaskInfoExtensionVisitorImpl(ExtensionVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(ExternalDocs externalDocs) {
        extensionHandler.handleComponentBaseExtension(externalDocs, LifecycleTaskInfo.class);
    }

    @Override
    public void visit(StandardDefinition standardDefinition) {
        extensionHandler.handleComponentBaseExtension(standardDefinition, LifecycleTaskInfo.class);
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
