package org.opendatamesh.dpds.extensions;

import org.opendatamesh.dpds.model.core.ExternalDocs;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionVisitor;

class CustomDefinitionVisitorImpl implements StandardDefinitionVisitor<CustomDefinition> {

    @Override
    public void visit(ExternalDocs externalDocs) {

    }

    @Override
    public void visit(CustomDefinition componentBase) {
        throw new RuntimeException("OK");
    }
}
