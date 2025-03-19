package org.opendatamesh.dpds.referencehandler.visitorsimpl.internals;

import org.opendatamesh.dpds.model.internals.ApplicationComponent;
import org.opendatamesh.dpds.model.internals.InfrastructuralComponent;
import org.opendatamesh.dpds.model.internals.LifecycleTaskInfo;
import org.opendatamesh.dpds.referencehandler.visitorsimpl.RefVisitor;
import org.opendatamesh.dpds.visitors.internals.ApplicationComponentVisitor;
import org.opendatamesh.dpds.visitors.internals.InfrastructuralComponentVisitor;
import org.opendatamesh.dpds.visitors.internals.InternalComponentsVisitor;
import org.opendatamesh.dpds.visitors.internals.LifecycleTaskInfoVisitor;

public class InternalComponentsRefVisitor extends RefVisitor implements InternalComponentsVisitor {
    public InternalComponentsRefVisitor(RefVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(ApplicationComponent applicationComponent) {
        referenceHandler.handleComponentBaseReference(applicationComponent);
        ApplicationComponentVisitor visitor = new ApplicationComponentRefVisitor(this);
        if (applicationComponent.getExternalDocs() != null) {
            visitor.visit(applicationComponent.getExternalDocs());
        }
    }

    @Override
    public void visit(InfrastructuralComponent infrastructuralComponent) {
        referenceHandler.handleComponentBaseReference(infrastructuralComponent);
        InfrastructuralComponentVisitor visitor = new InfrastructuralComponentRefVisitor(this);
        if (infrastructuralComponent.getExternalDocs() != null) {
            visitor.visit(infrastructuralComponent.getExternalDocs());
        }
    }

    @Override
    public void visit(LifecycleTaskInfo lifecycleTaskInfo) {
        referenceHandler.handleComponentBaseReference(lifecycleTaskInfo);
        LifecycleTaskInfoVisitor visitor = new LifecycleTaskInfoRefVisitor(this);
        if (lifecycleTaskInfo.getService() != null) {
            visitor.visit(lifecycleTaskInfo.getService());
        }
        if (lifecycleTaskInfo.getTemplate() != null) {
            visitor.visit(lifecycleTaskInfo.getTemplate());
        }
    }
}
