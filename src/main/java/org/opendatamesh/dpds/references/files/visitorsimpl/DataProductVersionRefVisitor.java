package org.opendatamesh.dpds.references.files.visitorsimpl;

import org.opendatamesh.dpds.model.components.Components;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.model.info.Info;
import org.opendatamesh.dpds.model.interfaces.InterfaceComponents;
import org.opendatamesh.dpds.model.internals.InternalComponents;
import org.opendatamesh.dpds.references.files.ReferenceFileHandler;
import org.opendatamesh.dpds.references.files.visitorsimpl.components.ComponentsRefVisitor;
import org.opendatamesh.dpds.references.files.visitorsimpl.info.InfoRefVisitorImpl;
import org.opendatamesh.dpds.references.files.visitorsimpl.interfaces.InterfaceComponentsRefVisitor;
import org.opendatamesh.dpds.references.files.visitorsimpl.internals.InternalComponentsRefVisitor;
import org.opendatamesh.dpds.visitors.DataProductVersionVisitor;
import org.opendatamesh.dpds.visitors.components.ComponentsVisitor;
import org.opendatamesh.dpds.visitors.info.InfoVisitor;
import org.opendatamesh.dpds.visitors.interfaces.InterfaceComponentsVisitor;
import org.opendatamesh.dpds.visitors.internals.InternalComponentsVisitor;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

public class DataProductVersionRefVisitor extends RefVisitor implements DataProductVersionVisitor {

    public DataProductVersionRefVisitor(ReferenceFileHandler referenceFileHandler) {
        super(null);
        this.referenceFileHandler = referenceFileHandler;
    }

    @Override
    public void visit(Info info) {
        referenceFileHandler.handleComponentBaseReference(info);
        InfoVisitor infoVisitor = new InfoRefVisitorImpl(this);
        if (info.getOwner() != null) {
            info.getOwner().accept(infoVisitor);
        }
        if (info.getContactPoints() != null) {
            info.getContactPoints().forEach(cp -> cp.accept(infoVisitor));
        }
    }

    @Override
    public void visit(InterfaceComponents interfaceComponents) {
        referenceFileHandler.handleComponentBaseReference(interfaceComponents);
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
                .forEach(p -> p.accept(visitor));
    }

    @Override
    public void visit(InternalComponents internalComponents) {
        referenceFileHandler.handleComponentBaseReference(internalComponents);
        InternalComponentsVisitor visitor = new InternalComponentsRefVisitor(this);
        if (internalComponents.getApplicationComponents() != null) {
            internalComponents.getApplicationComponents().forEach(a -> a.accept(visitor));
        }
        if (internalComponents.getInfrastructuralComponents() != null) {
            internalComponents.getInfrastructuralComponents().forEach(i -> i.accept(visitor));
        }
        if (internalComponents.getLifecycleInfo() != null) {
            internalComponents.getLifecycleInfo()
                    .forEach((stageName, tasks) -> tasks.forEach(t -> t.accept(visitor)));
        }
    }

    @Override
    public void visit(Components components) {
        referenceFileHandler.handleComponentBaseReference(components);
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
                .forEach(entry -> entry.getValue().accept(visitor));

        if (components.getApplicationComponents() != null) {
            components.getApplicationComponents().forEach((k, v) -> v.accept(visitor));
        }
        if (components.getInfrastructuralComponents() != null) {
            components.getInfrastructuralComponents().forEach((k, v) -> v.accept(visitor));
        }
        if (components.getApis() != null) {
            components.getApis().forEach((k, v) -> v.accept(visitor));
        }
        if (components.getTemplates() != null) {
            components.getTemplates().forEach((k, v) -> v.accept(visitor));
        }
    }

    @Override
    public void visit(ExternalDocs externalDocs) {
        referenceFileHandler.handleComponentBaseReference(externalDocs);
    }
}
