package org.opendatamesh.dpds.extensions.visitorsimpl.components;

import org.opendatamesh.dpds.extensions.visitorsimpl.ExtensionVisitor;
import org.opendatamesh.dpds.extensions.visitorsimpl.core.StandardDefinitionExtensionVisitorImpl;
import org.opendatamesh.dpds.extensions.visitorsimpl.interfaces.port.PortExtensionVisitor;
import org.opendatamesh.dpds.extensions.visitorsimpl.internals.ApplicationComponentExtensionVisitorImpl;
import org.opendatamesh.dpds.extensions.visitorsimpl.internals.InfrastructuralComponentExtensionVisitorImpl;
import org.opendatamesh.dpds.model.core.StandardDefinition;
import org.opendatamesh.dpds.model.interfaces.Port;
import org.opendatamesh.dpds.model.internals.ApplicationComponent;
import org.opendatamesh.dpds.model.internals.InfrastructuralComponent;
import org.opendatamesh.dpds.visitors.components.ComponentsVisitor;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionVisitor;
import org.opendatamesh.dpds.visitors.interfaces.port.PortVisitor;
import org.opendatamesh.dpds.visitors.internals.ApplicationComponentVisitor;
import org.opendatamesh.dpds.visitors.internals.InfrastructuralComponentVisitor;

public class ComponentsExtensionVisitorImpl extends ExtensionVisitor implements ComponentsVisitor {
    public ComponentsExtensionVisitorImpl(ExtensionVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(Port port) {
        extensionHandler.handleComponentBaseExtension(port, Port.class);
        PortVisitor visitor = new PortExtensionVisitor(this);
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
