package org.opendatamesh.dpds.references;

/**
 * Enum defining different descriptor formats for reference resolution.
 * <p>
 * Available formats:
 * <ul>
 *   <li>{@code CANONICAL} - All references are resolved and incorporated directly within the data product descriptor.</li>
 *   <li>{@code NORMALIZED} - Elements containing references store their full content within the reference, keeping only the ref field, while all other fields are set to null.</li>
 * </ul>
 * </p>
 */

public enum DescriptorFormat {
    /**
     * All references are resolved and incorporated directly within the data product descriptor.
     */
    CANONICAL,

    /**
     * Elements containing references store their full content within their reference,
     * keeping only their ref fields while all other fields are set to null.
     */
    NORMALIZED
}
