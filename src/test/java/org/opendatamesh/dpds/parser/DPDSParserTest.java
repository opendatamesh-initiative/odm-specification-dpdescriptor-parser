package org.opendatamesh.dpds.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opendatamesh.dpds.exceptions.ParseException;
import org.opendatamesh.dpds.location.DescriptorLocation;
import org.opendatamesh.dpds.location.UriLocation;
import org.opendatamesh.dpds.model.DataProductVersionDPDS;
import org.opendatamesh.dpds.utils.TestResource;

import java.io.IOException;

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
        DescriptorLocation descriptorLocation = new UriLocation(testResource.getRawDPDS().toString());
        DataProductVersionDPDS dpdsResult = dpdsParser.parse(descriptorLocation, parseOptions).getDescriptorDocument();
        Assertions.assertThat(new ObjectMapper().writeValueAsString(dpdsResult)).isEqualTo(testResource.getExpectedDataProductVersionDPDS().toString());
    }
}
