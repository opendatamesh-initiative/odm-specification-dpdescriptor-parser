package org.opendatamesh.dpds.datastoreapi.v1;

import org.opendatamesh.dpds.datastoreapi.v1.extensions.DataStoreApiStandardDefinitionVisitor;
import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApi;
import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiStandardDefinitionObject;
import org.opendatamesh.dpds.extensions.DefinitionVisitor;

import java.util.concurrent.atomic.AtomicInteger;

class DataStoreApiDefinitionVisitorImpl extends DefinitionVisitor<DataStoreApi> {

    private final AtomicInteger counter;

    DataStoreApiDefinitionVisitorImpl(AtomicInteger counter) {
        super(DataStoreApi.class);
        this.counter = counter;
    }

    @Override
    protected void visitDefinition(DataStoreApi definition) {
        if (definition.getSchema() != null && definition.getSchema().getTables() != null) {
            DataStoreApiStandardDefinitionVisitor<?> visitor = new DumbDataStoreApiStandardDefinitionObjectVisitor(counter);
            definition.getSchema()
                    .getTables()
                    .stream()
                    .map(DataStoreApiStandardDefinitionObject::getDefinition)
                    .forEach(visitor::visit);
        }
    }
}
