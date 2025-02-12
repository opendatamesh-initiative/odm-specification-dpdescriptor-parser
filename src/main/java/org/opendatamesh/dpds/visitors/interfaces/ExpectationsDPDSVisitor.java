package org.opendatamesh.dpds.visitors.interfaces;

import org.opendatamesh.dpds.model.core.SpecificationExtensionPointDPDS;

public interface ExpectationsDPDSVisitor {
    void visit(SpecificationExtensionPointDPDS specificationExtensionPointDPDS);
}
