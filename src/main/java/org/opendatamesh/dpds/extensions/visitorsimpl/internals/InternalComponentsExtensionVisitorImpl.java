package org.opendatamesh.dpds.extensions.visitorsimpl.internals;

import org.opendatamesh.dpds.extensions.visitorsimpl.ExtensionVisitor;
import org.opendatamesh.dpds.model.internals.ApplicationComponent;
import org.opendatamesh.dpds.model.internals.InfrastructuralComponent;
import org.opendatamesh.dpds.model.internals.LifecycleTaskInfo;
import org.opendatamesh.dpds.visitors.internals.ApplicationComponentVisitor;
import org.opendatamesh.dpds.visitors.internals.InfrastructuralComponentVisitor;
import org.opendatamesh.dpds.visitors.internals.InternalComponentsVisitor;
import org.opendatamesh.dpds.visitors.internals.LifecycleTaskInfoVisitor;

public class InternalComponentsExtensionVisitorImpl extends ExtensionVisitor implements InternalComponentsVisitor {
    public InternalComponentsExtensionVisitorImpl(ExtensionVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(ApplicationComponent applicationComponent) {
        extensionHandler.handleComponentBaseExtension(applicationComponent, ApplicationComponent.class);
        ApplicationComponentVisitor visitor = new ApplicationComponentExtensionVisitorImpl(this);
        if (applicationComponent.getExternalDocs() != null) {
            visitor.visit(applicationComponent.getExternalDocs());
        }
    }

    @Override
    public void visit(InfrastructuralComponent infrastructuralComponent) {
        extensionHandler.handleComponentBaseExtension(infrastructuralComponent, InfrastructuralComponent.class);
        InfrastructuralComponentVisitor visitor = new InfrastructuralComponentExtensionVisitorImpl(this);
        if (infrastructuralComponent.getExternalDocs() != null) {
            visitor.visit(infrastructuralComponent.getExternalDocs());
        }
    }

    @Override
    public void visit(LifecycleTaskInfo lifecycleTaskInfo) {
        extensionHandler.handleComponentBaseExtension(lifecycleTaskInfo, LifecycleTaskInfo.class);
        LifecycleTaskInfoVisitor visitor = new LifecycleTaskInfoExtensionVisitorImpl(this);
        if (lifecycleTaskInfo.getService() != null) {
            visitor.visit(lifecycleTaskInfo.getService());
        }
        if (lifecycleTaskInfo.getTemplate() != null) {
            visitor.visit(lifecycleTaskInfo.getTemplate());
        }
    }
}
