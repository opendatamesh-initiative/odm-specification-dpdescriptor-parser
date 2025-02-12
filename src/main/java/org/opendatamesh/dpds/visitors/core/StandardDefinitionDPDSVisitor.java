package org.opendatamesh.dpds.visitors.core;

import org.opendatamesh.dpds.model.definitions.ApiDefinitionReferenceDPDS;
import org.opendatamesh.dpds.model.definitions.DefinitionReferenceDPDS;

public interface StandardDefinitionDPDSVisitor extends ComponentDPDSVisitor {
    void visit(DefinitionReferenceDPDS definitionReferenceDPDS);

    void visit(ApiDefinitionReferenceDPDS apiDefinitionReferenceDPDS);
}
