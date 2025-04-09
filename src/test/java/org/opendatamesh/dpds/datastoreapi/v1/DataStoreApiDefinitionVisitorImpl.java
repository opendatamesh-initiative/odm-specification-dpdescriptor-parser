package org.opendatamesh.dpds.datastoreapi.v1;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApi;

public class DataStoreApiDefinitionVisitorImpl extends DataStoreApiDefinitionVisitor {

    public DataStoreApiDefinitionVisitorImpl() {
        //DO NOTHING
    }

    @Override
    protected void visitDefinition(DataStoreApi definition) {
        throw new RuntimeException("OK");
    }
}
