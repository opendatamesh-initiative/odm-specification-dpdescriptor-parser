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
            applicationComponent.getExternalDocs().accept(visitor);
        }
    }

    @Override
    public void visit(InfrastructuralComponent infrastructuralComponent) {
        extensionHandler.handleComponentBaseExtension(infrastructuralComponent, InfrastructuralComponent.class);
        InfrastructuralComponentVisitor visitor = new InfrastructuralComponentExtensionVisitorImpl(this);
        if (infrastructuralComponent.getExternalDocs() != null) {
            infrastructuralComponent.getExternalDocs().accept(visitor);
        }
    }

    @Override
    public void visit(LifecycleTaskInfo lifecycleTaskInfo) {
        extensionHandler.handleComponentBaseExtension(lifecycleTaskInfo, LifecycleTaskInfo.class);
        LifecycleTaskInfoVisitor visitor = new LifecycleTaskInfoExtensionVisitorImpl(this);
        if (lifecycleTaskInfo.getService() != null) {
            lifecycleTaskInfo.getService().accept(visitor);
        }
        if (lifecycleTaskInfo.getTemplate() != null) {
            lifecycleTaskInfo.getTemplate().accept(visitor);
        }
    }
}
