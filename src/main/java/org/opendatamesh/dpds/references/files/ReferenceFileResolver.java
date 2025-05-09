
package org.opendatamesh.dpds.references.files;

import org.opendatamesh.dpds.references.DescriptorFormat;
import org.opendatamesh.dpds.references.files.visitorsimpl.DataProductVersionRefVisitor;
import org.opendatamesh.dpds.model.DataProductVersion;

import java.nio.file.Path;

/**
 * Abstract class responsible for resolving references in a data product descriptor.
 * <p>
 * This class provides a method to resolve references within a {@link DataProductVersion}
 * instance based on the given {@link DescriptorFormat} and path. It utilizes a {@link ReferenceFileHandler}
 * and a {@link DataProductVersionRefVisitor} to process different elements of the data product.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public abstract class ReferenceFileResolver {

    /**
     * Resolves references in a {@link DataProductVersion} instance.
     * <p>
     * This method initializes a {@link ReferenceFileHandler} based on the specified format
     * and descriptor path. It then creates a {@link DataProductVersionRefVisitor} to traverse
     * the data product version's elements and resolve any references.
     * </p>
     *
     * @param format             The descriptor format to be used for resolving references.
     * @param dataProductVersion The data product version containing references to resolve.
     * @param descriptorPath     The file path of the descriptor.
     */
    public static void resolveReferences(DescriptorFormat format, DataProductVersion dataProductVersion, Path descriptorPath) {
        ReferenceFileHandler referenceFileHandler = new ReferenceFileHandler(format, descriptorPath);
        DataProductVersionRefVisitor visitor = new DataProductVersionRefVisitor(referenceFileHandler);

        if (dataProductVersion.getInterfaceComponents() != null) {
            visitor.visit(dataProductVersion.getInterfaceComponents());
        }
        if (dataProductVersion.getInternalComponents() != null) {
            visitor.visit(dataProductVersion.getInternalComponents());
        }
        if (dataProductVersion.getComponents() != null) {
            visitor.visit(dataProductVersion.getComponents());
        }
    }
}
