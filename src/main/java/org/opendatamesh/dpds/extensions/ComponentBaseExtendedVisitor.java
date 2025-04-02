package org.opendatamesh.dpds.extensions;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.visitors.core.ComponentBaseVisitor;

public abstract class ComponentBaseExtendedVisitor<T extends ComponentBase> implements ComponentBaseVisitor {

    private final Class<T> clazz;

    protected ComponentBaseExtendedVisitor(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void visit(ComponentBase componentBase) {
        if (clazz.isInstance(componentBase)) {
            visitExtension(clazz.cast(componentBase));
        }
    }

    abstract void visitExtension(T componentBase);
}
