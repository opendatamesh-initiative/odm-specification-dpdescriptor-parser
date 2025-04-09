package org.opendatamesh.dpds.datastoreapi.v1.extensions.visitorimpl;

import org.opendatamesh.dpds.datastoreapi.v1.model.DataStoreApiStandardDefinitionObject;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiSchemaVisitor;
import org.opendatamesh.dpds.datastoreapi.v1.visitor.DataStoreApiStandardDefinitionObjectVisitor;

public class DataStoreApiSchemaVisitorImpl extends ExtensionVisitor implements DataStoreApiSchemaVisitor {
    public DataStoreApiSchemaVisitorImpl(ExtensionVisitor parent) {
        super(parent);
    }

    @Override
    public void visit(DataStoreApiStandardDefinitionObject standardDefinitionObject) {
        extensionHandler.handleComponentBaseExtension(standardDefinitionObject, DataStoreApiStandardDefinitionObject.class);
        extensionHandler.handleStandardObjectDefinition(standardDefinitionObject);
        DataStoreApiStandardDefinitionObjectVisitor visitor = new DataStoreApiExternalResourceObjectVisitorImpl(this);
        if (standardDefinitionObject.getExternalDocs() != null) {
            visitor.visit(standardDefinitionObject.getExternalDocs());
        }
    }
}
