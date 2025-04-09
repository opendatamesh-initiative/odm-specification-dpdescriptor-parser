package org.opendatamesh.dpds.datastoreapi.v1;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApi;
import org.opendatamesh.dpds.extensions.DefinitionVisitor;

public abstract class DataStoreApiDefinitionVisitor extends DefinitionVisitor<DataStoreApi> {
    protected DataStoreApiDefinitionVisitor() {
        super(DataStoreApi.class);
    }
}
