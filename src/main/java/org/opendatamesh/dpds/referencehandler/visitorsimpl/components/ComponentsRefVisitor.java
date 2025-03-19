package org.opendatamesh.dpds.referencehandler.visitorsimpl.components;

import org.opendatamesh.dpds.model.core.StandardDefinition;
import org.opendatamesh.dpds.model.interfaces.Port;
import org.opendatamesh.dpds.model.internals.ApplicationComponent;
import org.opendatamesh.dpds.model.internals.InfrastructuralComponent;
import org.opendatamesh.dpds.referencehandler.visitorsimpl.RefVisitor;
import org.opendatamesh.dpds.referencehandler.visitorsimpl.core.StandardDefinitionRefVisitor;
import org.opendatamesh.dpds.referencehandler.visitorsimpl.interfaces.port.PortRefVisitor;
import org.opendatamesh.dpds.referencehandler.visitorsimpl.internals.ApplicationComponentRefVisitor;
import org.opendatamesh.dpds.referencehandler.visitorsimpl.internals.InfrastructuralComponentRefVisitor;
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
        referenceHandler.handleComponentBaseReference(port);
        PortVisitor visitor = new PortRefVisitor(this);
        if (port.getPromises() != null) {
            visitor.visit(port.getPromises());
        }
        if (port.getObligations() != null) {
            visitor.visit(port.getObligations());
        }
        if (port.getExpectations() != null) {
            visitor.visit(port.getExpectations());
        }
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
    public void visit(StandardDefinition standardDefinition) {
        referenceHandler.handleComponentBaseReference(standardDefinition);
        StandardDefinitionVisitor visitor = new StandardDefinitionRefVisitor(this);
        if (standardDefinition.getExternalDocs() != null) {
            visitor.visit(standardDefinition.getExternalDocs());
        }
        if (standardDefinition.getDefinition() != null) {
            visitor.visit(standardDefinition.getDefinition());
        }
    }
}
