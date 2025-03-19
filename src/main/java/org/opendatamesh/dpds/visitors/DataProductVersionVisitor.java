package org.opendatamesh.dpds.visitors;

import org.opendatamesh.dpds.model.components.Components;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.model.info.Info;
import org.opendatamesh.dpds.model.interfaces.InterfaceComponents;
import org.opendatamesh.dpds.model.internals.InternalComponents;

public interface DataProductVersionVisitor {
    void visit(Info info);

    void visit(InterfaceComponents interfaceComponents);

    void visit(InternalComponents internalComponents);

    void visit(Components components);

    void visit(ExternalDocs externalDocs);
}
