package org.opendatamesh.dpds.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.opendatamesh.dpds.exceptions.DeserializationException;
import org.opendatamesh.dpds.exceptions.UnresolvableReferenceException;

public interface PropertiesProcessor {
    void process() throws UnresolvableReferenceException, DeserializationException, JsonProcessingException;
}
