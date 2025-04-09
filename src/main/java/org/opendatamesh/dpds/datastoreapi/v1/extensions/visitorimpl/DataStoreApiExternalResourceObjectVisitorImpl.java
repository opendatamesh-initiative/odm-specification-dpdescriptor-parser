package org.opendatamesh.dpds.datastoreapi.v1.extensions.visitorimpl;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiExternalResourceObject;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiStandardDefinitionObjectVisitor;

public class DataStoreApiExternalResourceObjectVisitorImpl extends ExtensionVisitor implements DataStoreApiStandardDefinitionObjectVisitor {
    public DataStoreApiExternalResourceObjectVisitorImpl(ExtensionVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(DataStoreApiExternalResourceObject externalResourceObject) {
        extensionHandler.handleComponentBaseExtension(externalResourceObject, DataStoreApiExternalResourceObject.class);
    }
}
