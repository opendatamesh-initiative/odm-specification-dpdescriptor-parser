package org.opendatamesh.dpds.api;

import java.net.URI;

public class ApiParserFactory {
    public static ApiParser getApiParser(String specification, URI documentBaseUri) {
        if (specification == null) return null;
        switch (specification.toLowerCase()) {
            case "datastoreapi":
                return new DataStoreApiParser(documentBaseUri);
            case "asyncapi":
                return new AsyncApiParser(documentBaseUri);
            case "openapi":
                return new OpenApiParser(documentBaseUri);
            default:
                return null;
        }
    }
}
