package org.opendatamesh.dpds.datastoreapi.v1.visitor;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiConnectionProtocolObject;

public interface DataStoreApiServerInfoVisitor {
    void visit(DataStoreApiConnectionProtocolObject connectionProtocolObject);
}
