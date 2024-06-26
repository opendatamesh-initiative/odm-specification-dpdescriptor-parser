package org.opendatamesh.dpds.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ParseException extends Exception {
    
    Stage stage;

    public ParseException(String message, Stage stage) {
        super(message);
        this.stage = stage;
    }

    public ParseException(String message, Stage stage, Throwable t) {
        super(message, t);
        this.stage = stage;
    }

    static public enum Stage {
        LOAD_ROOT_DOC, 
        RESOLVE_EXTERNAL_REFERENCES, 
        RESOLVE_INTERNAL_REFERENCES,
        RESOLVE_READ_ONLY_PROPERTIES,
        RESOLVE_STANDARD_DEFINITIONS, 
        RESOLVE_TEMPLATE_PROPERTIES,
        VALIDATE
    }
}
