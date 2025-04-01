package org.opendatamesh.dpds.parser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ParserFactory {

    private ParserFactory() {
        //DO NOTHING
    }

    public static Parser getParser() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return new ParserImpl(objectMapper);
    }

    public static Parser getParser(ObjectMapper objectMapper) {
        return new ParserImpl(objectMapper);
    }
}
