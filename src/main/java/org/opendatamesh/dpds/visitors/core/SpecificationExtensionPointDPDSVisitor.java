package org.opendatamesh.dpds.visitors.core;

import org.opendatamesh.dpds.model.core.ExternalResourceDPDS;

public interface SpecificationExtensionPointDPDSVisitor {

    void visit(ExternalResourceDPDS externalResourceDPDS);
}
