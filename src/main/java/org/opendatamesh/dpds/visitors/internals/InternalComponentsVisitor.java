package org.opendatamesh.dpds.visitors.internals;

import org.opendatamesh.dpds.model.internals.ApplicationComponent;
import org.opendatamesh.dpds.model.internals.InfrastructuralComponent;
import org.opendatamesh.dpds.model.internals.LifecycleTaskInfo;

public interface InternalComponentsVisitor {
    void visit(ApplicationComponent applicationComponent);

    void visit(InfrastructuralComponent infrastructuralComponent);

    void visit(LifecycleTaskInfo lifecycleTaskInfo);
}
