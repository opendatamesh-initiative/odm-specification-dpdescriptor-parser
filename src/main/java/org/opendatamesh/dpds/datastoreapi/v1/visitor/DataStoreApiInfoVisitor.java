package org.opendatamesh.dpds.datastoreapi.v1.visitor;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiContact;
import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiLicense;

public interface DataStoreApiInfoVisitor {
    void visit(DataStoreApiContact contact);

    void visit(DataStoreApiLicense license);
}
