package org.opendatamesh.dpds.extensions;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionVisitor;

public abstract class DefinitionVisitor<T extends ComponentBase> implements StandardDefinitionVisitor {

    private final Class<T> clazz;

    protected DefinitionVisitor(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public final void visit(ComponentBase definition) {
        if (clazz.isInstance(definition)) {
            visitDefinition(clazz.cast(definition));
        }
    }

    @Override
    public void visit(ExternalDocs externalDocs) {
    }

    abstract void visitDefinition(T definition);
}
