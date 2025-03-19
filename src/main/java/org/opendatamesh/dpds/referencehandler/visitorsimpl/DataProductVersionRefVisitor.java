package org.opendatamesh.dpds.referencehandler.visitorsimpl;

import org.opendatamesh.dpds.model.components.Components;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.model.info.Info;
import org.opendatamesh.dpds.model.interfaces.InterfaceComponents;
import org.opendatamesh.dpds.model.internals.InternalComponents;
import org.opendatamesh.dpds.referencehandler.ReferenceHandler;
import org.opendatamesh.dpds.referencehandler.visitorsimpl.components.ComponentsRefVisitor;
import org.opendatamesh.dpds.referencehandler.visitorsimpl.info.InfoRefVisitorImpl;
import org.opendatamesh.dpds.referencehandler.visitorsimpl.interfaces.InterfaceComponentsRefVisitor;
import org.opendatamesh.dpds.referencehandler.visitorsimpl.internals.InternalComponentsRefVisitor;
import org.opendatamesh.dpds.visitors.DataProductVersionVisitor;
import org.opendatamesh.dpds.visitors.components.ComponentsVisitor;
import org.opendatamesh.dpds.visitors.info.InfoVisitor;
import org.opendatamesh.dpds.visitors.interfaces.InterfaceComponentsVisitor;
import org.opendatamesh.dpds.visitors.internals.InternalComponentsVisitor;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

public class DataProductVersionRefVisitor extends RefVisitor implements DataProductVersionVisitor {

    public DataProductVersionRefVisitor(ReferenceHandler referenceHandler) {
        super(null);
        this.referenceHandler = referenceHandler;
    }

    @Override
    public void visit(Info info) {
        referenceHandler.handleComponentBaseReference(info);
        InfoVisitor infoVisitor = new InfoRefVisitorImpl(this);
        if (info.getOwner() != null) {
            infoVisitor.visit(info.getOwner());
        }
        if (info.getContactPoints() != null) {
            info.getContactPoints().forEach(infoVisitor::visit);
        }
    }

    @Override
    public void visit(InterfaceComponents interfaceComponents) {
        referenceHandler.handleComponentBaseReference(interfaceComponents);
        InterfaceComponentsVisitor visitor = new InterfaceComponentsRefVisitor(this);
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
        referenceHandler.handleComponentBaseReference(internalComponents);
        InternalComponentsVisitor visitor = new InternalComponentsRefVisitor(this);
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
        referenceHandler.handleComponentBaseReference(components);
        ComponentsVisitor visitor = new ComponentsRefVisitor(this);
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
        referenceHandler.handleComponentBaseReference(externalDocs);
    }
}
