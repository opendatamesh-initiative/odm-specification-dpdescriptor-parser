package org.opendatamesh.dpds.api;

import org.opendatamesh.dpds.exceptions.DeserializationException;
import org.opendatamesh.dpds.exceptions.FetchException;
import org.opendatamesh.dpds.model.definitions.ApiDefinitionReferenceDPDS;

public interface ApiParser {
    ApiDefinitionReferenceDPDS parse(String rawContent, String mediaType) throws DeserializationException, FetchException;
}
