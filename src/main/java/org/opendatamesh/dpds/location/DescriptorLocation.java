package org.opendatamesh.dpds.location;

import org.opendatamesh.dpds.exceptions.FetchException;

import java.net.URI;

public interface DescriptorLocation extends Fetcher {
    //TODO this method should be removed
    URI getRootDocumentBaseUri();

    //TODO this method should be removed
    void setRootDocumentBaseUri(URI documentBaseUri);

    void open() throws FetchException;

    void close() throws FetchException;

    String fetchRootDoc() throws FetchException;
}