package org.opendatamesh.dpds.datastoreapi.v1.visitor;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiExternalResourceObject;

public interface DataStoreApiOdbcConnectionObjectVisitor {
    void visit(DataStoreApiExternalResourceObject externalResourceObject);
}
