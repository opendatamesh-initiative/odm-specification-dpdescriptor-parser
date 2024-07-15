package org.opendatamesh.dpds.parser;

import org.opendatamesh.dpds.model.DataProductVersionDPDS;
import org.opendatamesh.dpds.model.core.ComponentDPDS;
import org.opendatamesh.dpds.model.core.EntityTypeDPDS;

public interface IdentifierStrategy {

    String getId(String fqn);

    String getFqn(DataProductVersionDPDS descriptor, ComponentDPDS component);

    String getExternalComponentFqn(
            EntityTypeDPDS type,
            String name,
            String version);
}
