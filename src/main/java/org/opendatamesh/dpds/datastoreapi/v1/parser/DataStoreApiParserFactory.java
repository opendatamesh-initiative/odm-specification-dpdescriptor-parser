package org.opendatamesh.dpds.datastoreapi.v1.parser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class DataStoreApiParserFactory {

    private DataStoreApiParserFactory() {
        //DO NOTHING
    }

    public static DataStoreApiParser getParser() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return new DataStoreApiParserImpl(objectMapper);
    }

    public static DataStoreApiParser getParser(ObjectMapper objectMapper) {
        return new DataStoreApiParserImpl(objectMapper);
    }
}
