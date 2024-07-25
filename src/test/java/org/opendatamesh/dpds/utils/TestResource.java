package org.opendatamesh.dpds.utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

@Data
public class TestResource {
    private ObjectNode rawDPDS;
    private ObjectNode expectedDataProductVersionDPDS;
}
