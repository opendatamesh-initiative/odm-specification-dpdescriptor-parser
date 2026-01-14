package org.opendatamesh.dpds.extensions.visitorsimpl.components;

import org.opendatamesh.dpds.extensions.visitorsimpl.ExtensionVisitor;
import org.opendatamesh.dpds.extensions.visitorsimpl.core.StandardDefinitionExtensionVisitorImpl;
import org.opendatamesh.dpds.extensions.visitorsimpl.interfaces.port.PortExtensionVisitor;
import org.opendatamesh.dpds.extensions.visitorsimpl.internals.ApplicationComponentExtensionVisitorImpl;
import org.opendatamesh.dpds.extensions.visitorsimpl.internals.InfrastructuralComponentExtensionVisitorImpl;
import org.opendatamesh.dpds.model.core.ComponentBase;
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
