package org.opendatamesh.dpds.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.junit.jupiter.api.Test;
import org.opendatamesh.dpds.exceptions.ParseException;
import org.opendatamesh.dpds.location.DescriptorLocation;
import org.opendatamesh.dpds.location.UriLocation;
import org.opendatamesh.dpds.model.DataProductVersionDPDS;
import org.opendatamesh.dpds.utils.TestResource;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class DPDSParserTest {

    @Test
    public void testDpdsParserIssue5() throws IOException, ParseException {
        DPDSParser dpdsParser = new DPDSParser(
                "https://raw.githubusercontent.com/opendatamesh-initiative/odm-specification-dpdescriptor/main/schemas/",
                "1.0.0",
                null
        );
        ParseOptions parseOptions = new ParseOptions();
        TestResource testResource = new ObjectMapper().readValue(Resources.toByteArray(getClass().getResource("DPDSParser_issue5.json")), TestResource.class);
        DescriptorLocation descriptorLocation = new UriLocation(testResource.getRawInput().toString());
        DataProductVersionDPDS dpdsResult = new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(dpdsParser.parse(descriptorLocation, parseOptions).getDescriptorDocument()), DataProductVersionDPDS.class);
        DataProductVersionDPDS expectedDpdsResult = new ObjectMapper().treeToValue(testResource.getExpectedResult(), DataProductVersionDPDS.class);
        assertThat(dpdsResult).usingRecursiveComparison().isEqualTo(expectedDpdsResult);
    }
}
