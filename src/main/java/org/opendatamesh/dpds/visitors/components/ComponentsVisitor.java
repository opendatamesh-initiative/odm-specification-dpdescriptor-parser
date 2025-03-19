package org.opendatamesh.dpds.visitors.components;

import org.opendatamesh.dpds.model.core.StandardDefinition;
import org.opendatamesh.dpds.model.interfaces.Port;
import org.opendatamesh.dpds.model.internals.ApplicationComponent;
import org.opendatamesh.dpds.model.internals.InfrastructuralComponent;

public interface ComponentsVisitor {
    void visit(Port port);

    void visit(ApplicationComponent applicationComponent);

    void visit(InfrastructuralComponent infrastructuralComponent);

    void visit(StandardDefinition standardDefinition);
}
