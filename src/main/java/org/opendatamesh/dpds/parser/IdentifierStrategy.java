package org.opendatamesh.dpds.parser;

import org.opendatamesh.dpds.model.DataProductVersionDPDS;
import org.opendatamesh.dpds.model.core.ComponentDPDS;
import org.opendatamesh.dpds.model.core.EntityTypeDPDS;

/**
 * Interface for strategies that generate identifiers and fully qualified names (FQNs)
 * for data products and their components.
 */
public interface IdentifierStrategy {

    /**
     * Generates an identifier based on the given fully qualified name (FQN).
     *
     * @param fqn the fully qualified name for which an identifier is to be generated
     * @return the generated identifier
     */
    String getId(String fqn);

    /**
     * Generates the fully qualified name (FQN) for the given data product version descriptor.
     *
     * @param descriptor the data product version descriptor for which the FQN is to be generated
     * @return the generated FQN
     */
    String getFqn(DataProductVersionDPDS descriptor);

    /**
     * Generates the fully qualified name (FQN) for the given data product version descriptor
     * and component.
     *
     * @param descriptor the data product version descriptor for which the FQN is to be generated
     * @param component  the component of the descriptor that will be used to generate the FQN
     * @return the generated FQN
     */
    String getFqn(DataProductVersionDPDS descriptor, ComponentDPDS component);

    /**
     * Generates the fully qualified name (FQN) for an external component based on the given type,
     * name, and version.
     *
     * @param type    the entity type of the external component
     * @param name    the name of the external component
     * @param version the version of the external component
     * @return the generated FQN
     */
    String getExternalComponentFqn(
            EntityTypeDPDS type,
            String name,
            String version);
}
