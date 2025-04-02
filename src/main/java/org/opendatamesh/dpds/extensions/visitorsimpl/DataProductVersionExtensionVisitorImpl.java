package org.opendatamesh.dpds.extensions.visitorsimpl;

import org.opendatamesh.dpds.extensions.visitorsimpl.components.ComponentsExtensionVisitorImpl;
import org.opendatamesh.dpds.extensions.visitorsimpl.info.InfoExtensionVisitorImpl;
import org.opendatamesh.dpds.extensions.visitorsimpl.interfaces.InterfaceComponentsExtensionVisitorImpl;
import org.opendatamesh.dpds.extensions.visitorsimpl.internals.InternalComponentsExtensionVisitorImpl;
import org.opendatamesh.dpds.model.DataProductVersion;
import org.opendatamesh.dpds.model.components.Components;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.model.info.Info;
import org.opendatamesh.dpds.model.interfaces.InterfaceComponents;
import org.opendatamesh.dpds.model.internals.InternalComponents;
import org.opendatamesh.dpds.visitors.DataProductVersionVisitor;
import org.opendatamesh.dpds.visitors.components.ComponentsVisitor;
import org.opendatamesh.dpds.visitors.info.InfoVisitor;
import org.opendatamesh.dpds.visitors.interfaces.InterfaceComponentsVisitor;
import org.opendatamesh.dpds.visitors.internals.InternalComponentsVisitor;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

public class DataProductVersionExtensionVisitorImpl extends ExtensionVisitor implements DataProductVersionVisitor {

    public DataProductVersionExtensionVisitorImpl(ExtensionHandler extensionHandler) {
        super(null);
        this.extensionHandler = extensionHandler;
    }

    @Override
    public void visit(Info info) {
        extensionHandler.handleComponentBaseExtension(info, DataProductVersion.class);
        InfoVisitor infoVisitor = new InfoExtensionVisitorImpl(this);
        if (info.getOwner() != null) {
            infoVisitor.visit(info.getOwner());
        }
        if (info.getContactPoints() != null) {
            info.getContactPoints().forEach(infoVisitor::visit);
        }
    }

    @Override
    public void visit(InterfaceComponents interfaceComponents) {
        extensionHandler.handleComponentBaseExtension(interfaceComponents, DataProductVersion.class);
        InterfaceComponentsVisitor visitor = new InterfaceComponentsExtensionVisitorImpl(this);
        Stream.of(
                        interfaceComponents.getInputPorts(),
                        interfaceComponents.getOutputPorts(),
                        interfaceComponents.getDiscoveryPorts(),
                        interfaceComponents.getObservabilityPorts(),
                        interfaceComponents.getControlPorts()
                )
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .forEach(visitor::visit);
    }

    @Override
    public void visit(InternalComponents internalComponents) {
        extensionHandler.handleComponentBaseExtension(internalComponents, DataProductVersion.class);
        InternalComponentsVisitor visitor = new InternalComponentsExtensionVisitorImpl(this);
        if (internalComponents.getApplicationComponents() != null) {
            internalComponents.getApplicationComponents().forEach(visitor::visit);
        }
        if (internalComponents.getInfrastructuralComponents() != null) {
            internalComponents.getInfrastructuralComponents().forEach(visitor::visit);
        }
        if (internalComponents.getLifecycleInfo() != null) {
            internalComponents.getLifecycleInfo()
                    .forEach((stageName, tasks) -> tasks.forEach(visitor::visit)
                    );
        }
    }

    @Override
    public void visit(Components components) {
        extensionHandler.handleComponentBaseExtension(components, DataProductVersion.class);
        ComponentsVisitor visitor = new ComponentsExtensionVisitorImpl(this);
        Stream.of(
                        components.getInputPorts(),
                        components.getOutputPorts(),
                        components.getDiscoveryPorts(),
                        components.getObservabilityPorts(),
                        components.getControlPorts()
                )
                .filter(Objects::nonNull)
                .flatMap(map -> map.entrySet().stream())
                .forEach(entry -> visitor.visit(entry.getValue()));

        if (components.getApplicationComponents() != null) {
            components.getApplicationComponents().forEach((k, v) -> visitor.visit(v));
        }
        if (components.getInfrastructuralComponents() != null) {
            components.getInfrastructuralComponents().forEach((k, v) -> visitor.visit(v));
        }
        if (components.getApis() != null) {
            components.getApis().forEach((k, v) -> visitor.visit(v));
        }
        if (components.getTemplates() != null) {
            components.getTemplates().forEach((k, v) -> visitor.visit(v));
        }
    }

    @Override
    public void visit(ExternalDocs externalDocs) {
        extensionHandler.handleComponentBaseExtension(externalDocs, DataProductVersion.class);
    }
}
