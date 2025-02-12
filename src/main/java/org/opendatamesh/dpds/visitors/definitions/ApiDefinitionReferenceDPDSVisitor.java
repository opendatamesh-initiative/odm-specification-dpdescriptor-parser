package org.opendatamesh.dpds.visitors.definitions;

import org.opendatamesh.dpds.model.definitions.ApiDefinitionEndpointDPDS;

public interface ApiDefinitionReferenceDPDSVisitor {
    void visit(ApiDefinitionEndpointDPDS apiDefinitionEndpointDPDS);
}
