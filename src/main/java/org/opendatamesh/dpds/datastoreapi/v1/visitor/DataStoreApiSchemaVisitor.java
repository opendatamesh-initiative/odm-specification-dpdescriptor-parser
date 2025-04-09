package org.opendatamesh.dpds.datastoreapi.v1.visitor;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiStandardDefinitionObject;

public interface DataStoreApiSchemaVisitor {
    void visit(DataStoreApiStandardDefinitionObject standardDefinitionObject);
}
