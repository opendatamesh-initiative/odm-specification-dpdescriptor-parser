package org.opendatamesh.dpds.references.files.visitorsimpl.components;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.StandardDefinition;
import org.opendatamesh.dpds.model.interfaces.Port;
import org.opendatamesh.dpds.model.internals.ApplicationComponent;
import org.opendatamesh.dpds.model.internals.InfrastructuralComponent;
import org.opendatamesh.dpds.references.files.visitorsimpl.RefVisitor;
import org.opendatamesh.dpds.references.files.visitorsimpl.core.StandardDefinitionRefVisitor;
import org.opendatamesh.dpds.references.files.visitorsimpl.interfaces.port.PortRefVisitor;
import org.opendatamesh.dpds.references.files.visitorsimpl.internals.ApplicationComponentRefVisitor;
import org.opendatamesh.dpds.references.files.visitorsimpl.internals.InfrastructuralComponentRefVisitor;
import org.opendatamesh.dpds.visitors.components.ComponentsVisitor;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.PortVisitor;
import org.opendatamesh.dpds.visitors.internals.ApplicationComponentVisitor;
import org.opendatamesh.dpds.visitors.internals.InfrastructuralComponentVisitor;

public class ComponentsRefVisitor extends RefVisitor implements ComponentsVisitor {
    public ComponentsRefVisitor(RefVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(Port port) {
        referenceFileHandler.handleComponentBaseReference(port);
        PortVisitor visitor = new PortRefVisitor(this);
        if (port.getPromises() != null) {
            port.getPromises().accept(visitor);
        }
        if (port.getObligations() != null) {
            port.getObligations().accept(visitor);
        }
        if (port.getExpectations() != null) {
            port.getExpectations().accept(visitor);
        }
    }

    @Override
    public void visit(ApplicationComponent applicationComponent) {
        referenceFileHandler.handleComponentBaseReference(applicationComponent);
        ApplicationComponentVisitor visitor = new ApplicationComponentRefVisitor(this);
        if (applicationComponent.getExternalDocs() != null) {
            applicationComponent.getExternalDocs().accept(visitor);
        }
    }

    @Override
    public void visit(InfrastructuralComponent infrastructuralComponent) {
        referenceFileHandler.handleComponentBaseReference(infrastructuralComponent);
        InfrastructuralComponentVisitor visitor = new InfrastructuralComponentRefVisitor(this);
        if (infrastructuralComponent.getExternalDocs() != null) {
            infrastructuralComponent.getExternalDocs().accept(visitor);
        }
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
