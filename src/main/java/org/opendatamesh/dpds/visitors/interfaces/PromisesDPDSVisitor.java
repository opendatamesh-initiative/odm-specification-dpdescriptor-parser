package org.opendatamesh.dpds.visitors.interfaces;

import org.opendatamesh.dpds.model.core.SpecificationExtensionPointDPDS;
import org.opendatamesh.dpds.model.core.StandardDefinitionDPDS;

public interface PromisesDPDSVisitor {
    void visit(StandardDefinitionDPDS standardDefinitionDPDS);

    void visit(SpecificationExtensionPointDPDS specificationExtensionPointDPDS);
}
