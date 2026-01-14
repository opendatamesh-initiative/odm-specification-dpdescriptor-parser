package org.opendatamesh.dpds.visitors.core;

import org.opendatamesh.dpds.model.core.ComponentBase;

public interface ComponentBaseVisitor<T extends ComponentBase> {
    void visit(T componentBase);
}
