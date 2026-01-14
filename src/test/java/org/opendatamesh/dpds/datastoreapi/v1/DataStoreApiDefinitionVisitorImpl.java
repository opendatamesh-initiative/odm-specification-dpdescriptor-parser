package org.opendatamesh.dpds.datastoreapi.v1;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApi;
import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiStandardDefinitionObject;
import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.visitors.core.ComponentBaseVisitor;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionVisitor;

import java.util.concurrent.atomic.AtomicInteger;

class DataStoreApiDefinitionVisitorImpl implements StandardDefinitionVisitor<DataStoreApi> {
    private final AtomicInteger counter;

    DataStoreApiDefinitionVisitorImpl(AtomicInteger counter) {
        this.counter = counter;
    }

    @Override
    public void visit(ExternalDocs externalDocs) {
        //DO NOTHING
    }

    @Override
    public void visit(DataStoreApi definition) {
        if (definition.getSchema() != null && definition.getSchema().getTables() != null) {
            ComponentBaseVisitor<DumbDataStoreApiStandardDefinitionObject> visitor = new DumbDataStoreApiStandardDefinitionObjectVisitorImpl(counter);
            definition.getSchema()
                    .getTables()
                    .stream()
                    .map(DataStoreApiStandardDefinitionObject::getDefinition)
                    .forEach(standardDefinitionObject -> {
                        if (standardDefinitionObject instanceof DumbDataStoreApiStandardDefinitionObject) {
                            //P.A. The type check MUST be done in visitor implementations!!!
                            standardDefinitionObject.accept(visitor);
                        }
                    });
        }
    }
}
