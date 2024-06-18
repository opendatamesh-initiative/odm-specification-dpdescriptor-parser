package org.opendatamesh.dpds.api;

import java.net.URI;

public class ApiParserFactory {
    public static ApiParser getApiParser(String specification, URI documentBaseUri) {
        switch (specification) {
            case "datastoreApi":
                return new DataStoreApiParser(documentBaseUri);
            case "asyncApi":
                return new AsyncApiParser(documentBaseUri);
            case "openApi":
                return new OpenApiParser(documentBaseUri);
            default:
                return null;
        }
    }
}
