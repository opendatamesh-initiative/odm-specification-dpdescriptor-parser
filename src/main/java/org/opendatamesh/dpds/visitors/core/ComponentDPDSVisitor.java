package org.opendatamesh.dpds.visitors.core;

import org.opendatamesh.dpds.model.core.ExternalResourceDPDS;

public interface ComponentDPDSVisitor {
    void visit(ExternalResourceDPDS externalResourceDPDS);
}
