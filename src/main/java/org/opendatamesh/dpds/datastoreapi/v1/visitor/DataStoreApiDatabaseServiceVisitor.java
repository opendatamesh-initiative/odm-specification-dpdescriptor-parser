package org.opendatamesh.dpds.datastoreapi.v1.visitor;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiServerInfo;
import org.opendatamesh.dpds.datastoreapi.v1.model.DatastoreApiVariableObject;

public interface DataStoreApiDatabaseServiceVisitor {
    void visit(DataStoreApiServerInfo serverInfo);

    void visit(DatastoreApiVariableObject variableObject);
}
