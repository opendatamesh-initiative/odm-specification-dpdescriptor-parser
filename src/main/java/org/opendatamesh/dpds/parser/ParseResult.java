package org.opendatamesh.dpds.parser;

import lombok.Data;
import org.opendatamesh.dpds.model.DataProductVersionDPDS;

@Data
public class ParseResult {
    DataProductVersionDPDS descriptorDocument;
}
