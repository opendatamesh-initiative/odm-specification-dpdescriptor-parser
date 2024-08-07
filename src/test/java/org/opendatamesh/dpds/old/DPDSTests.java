package org.opendatamesh.dpds.old;

import org.opendatamesh.dpds.model.DataProductVersionDPDS;
import org.opendatamesh.dpds.parser.*;
import org.opendatamesh.dpds.location.DescriptorLocation;
import org.opendatamesh.dpds.old.utils.DPDSTestResources;

import static org.assertj.core.api.Assertions.fail;

public class DPDSTests {

    public static final ParseOptions DEFAULT_PARSE_OPTION;

    protected static final DPDSParser parser = new DPDSParser(
            "https://raw.githubusercontent.com/opendatamesh-initiative/odm-specification-dpdescriptor/main/schemas/",
            "1.0.0",
            null
    );

    static {
        DEFAULT_PARSE_OPTION = new ParseOptions();
        DEFAULT_PARSE_OPTION.setServerUrl("http://localhost:80");
        DEFAULT_PARSE_OPTION.setIdentifierStrategy(IdentifierStrategyFactory.getDefault());
    }

    protected ParseResult parseDescriptorFromContent(DPDSTestResources resource, ParseOptions options) {
        DescriptorLocation location = getContentLocation(resource);
        return parseDescriptor(location, options);
    }

    protected ParseResult parseDescriptorFromUri(DPDSTestResources resource, ParseOptions options) {
        DescriptorLocation location = getUriLocation(resource);
        return parseDescriptor(location, options);
    }

    protected ParseResult parseDescriptorFromGit(DPDSTestResources resource, ParseOptions options) {
        DescriptorLocation location = getGitLocation(resource);
        return parseDescriptor(location, options);
    }

    protected ParseResult parseDescriptor(DescriptorLocation location, ParseOptions options) {

        ParseResult result = null;

        if (options == null) {
            options = DEFAULT_PARSE_OPTION;
        }

        DPDSParser parser = new DPDSParser(
                "https://raw.githubusercontent.com/opendatamesh-initiative/odm-specification-dpdescriptor/main/schemas/",
                "1.0.0",
                null
        );
        try {
            result = parser.parse(location, options);
        } catch (Throwable e) {
            e.printStackTrace();
            fail("Impossible to parse descriptor", e);
        }

        return result;
    }

    protected DescriptorLocation getContentLocation(DPDSTestResources resource) {
        DescriptorLocation location = null;
        try {
            location = resource.getContentLocation();
        } catch (Throwable t) {
            fail("Impossible to get descriptor location fomp path [" + resource.localPath + "]", t);
        }

        return location;
    }

    protected DescriptorLocation getUriLocation(DPDSTestResources resource) {
        DescriptorLocation location = null;
        try {
            location = resource.getUriLocation();
        } catch (Throwable t) {
            fail("Impossible to get descriptor location fomp path [" + resource.localPath + "]", t);
        }

        return location;
    }

    protected DescriptorLocation getGitLocation(DPDSTestResources resource) {
        DescriptorLocation location = null;
        try {
            location = resource.getGitLocation();
        } catch (Throwable t) {
            fail("Impossible to get descriptor location fomp git [" + resource.repoPath + "]", t);
        }

        return location;
    }

    protected String serializeDescriptor(DataProductVersionDPDS descriptor, String form, String mediaType) {
        String descriptorContent = null;

        DPDSSerializer serializer = new DPDSSerializer(mediaType, true);
        try {
            descriptorContent = serializer.serialize(descriptor, form);
        } catch (Throwable t) {
            fail("Impossible to serialize descriptor", t);
        }

        return descriptorContent;
    }

    // ==========================================================================
    // Verify descriptor
    // ==========================================================================
}
