package org.opendatamesh.dpds.datastoreapi.v1;

import org.opendatamesh.dpds.visitors.core.ComponentBaseVisitor;

import java.util.concurrent.atomic.AtomicInteger;

class DumbDataStoreApiStandardDefinitionObjectVisitorImpl implements ComponentBaseVisitor<DumbDataStoreApiStandardDefinitionObject> {

    private final AtomicInteger counter;

    DumbDataStoreApiStandardDefinitionObjectVisitorImpl(AtomicInteger counter) {
        this.counter = counter;
    }

    @Override
    public void visit(DumbDataStoreApiStandardDefinitionObject componentBase) {
        this.counter.incrementAndGet();
    }
}
