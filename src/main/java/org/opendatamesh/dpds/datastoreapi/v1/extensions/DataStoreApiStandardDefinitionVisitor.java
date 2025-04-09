package org.opendatamesh.dpds.datastoreapi.v1.extensions;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiExternalResourceObject;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiStandardDefinitionObjectVisitor;
import org.opendatamesh.dpds.model.core.ComponentBase;

public abstract class DataStoreApiStandardDefinitionVisitor<T extends ComponentBase> implements DataStoreApiStandardDefinitionObjectVisitor {

    private final Class<T> clazz;

    protected DataStoreApiStandardDefinitionVisitor(Class<T> clazz) {
        this.clazz = clazz;
    }

    public final void visit(ComponentBase definition) {
        if (clazz.isInstance(definition)) {
            visitDefinition(clazz.cast(definition));
        }
    }

    @Override
    public void visit(DataStoreApiExternalResourceObject externalResourceObject) {
        //DO NOTHING
    }

    protected abstract void visitDefinition(T definition);
}
