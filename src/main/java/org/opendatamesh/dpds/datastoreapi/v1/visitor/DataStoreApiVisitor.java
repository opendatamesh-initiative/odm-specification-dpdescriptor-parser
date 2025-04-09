package org.opendatamesh.dpds.datastoreapi.v1.visitor;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiDatabaseService;
import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiInfo;
import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiSchema;

public interface DataStoreApiVisitor {
    void visit(DataStoreApiInfo info);

    void visit(DataStoreApiDatabaseService databaseService);

    void visit(DataStoreApiSchema schema);
}
