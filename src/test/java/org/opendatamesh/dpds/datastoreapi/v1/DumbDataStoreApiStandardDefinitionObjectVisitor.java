package org.opendatamesh.dpds.datastoreapi.v1;

import org.opendatamesh.dpds.datastoreapi.v1.extensions.DataStoreApiStandardDefinitionVisitor;

import java.util.concurrent.atomic.AtomicInteger;

class DumbDataStoreApiStandardDefinitionObjectVisitor extends DataStoreApiStandardDefinitionVisitor<DumbDataStoreApiStandardDefinitionObject> {

    private final AtomicInteger counter;

    DumbDataStoreApiStandardDefinitionObjectVisitor(AtomicInteger counter) {
        super(DumbDataStoreApiStandardDefinitionObject.class);
        this.counter = counter;
    }

    @Override
    protected void visitDefinition(DumbDataStoreApiStandardDefinitionObject definition) {
        this.counter.incrementAndGet();
    }
}
