package org.opendatamesh.dpds.datastoreapi.v1.visitor;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiJdbcConnectionObject;
import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiOdbcConnectionObject;

public interface DataStoreApiConnectionProtocolObjectVisitor {
    void visit(DataStoreApiJdbcConnectionObject jdbc);

    void visit(DataStoreApiOdbcConnectionObject odbc);
}
