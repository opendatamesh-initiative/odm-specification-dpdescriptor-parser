package org.opendatamesh.dpds.location;

import org.opendatamesh.dpds.exceptions.FetchException;

import java.net.URI;

public interface Fetcher {
    /**
     * @param baseUri     the base uri used to resolve the resource uri. Must be absolute
     * @param relativeResourceUri the uri of the resource to fetch. It should be relative.
     *                    If it is absolute the base uri is ignored. No exception is thrown.
     *                    Anyway in this case is better to just call the fetch method below.
     * @return the content fetched from uri
     * @throws FetchException
     */
    String fetchRelativeResource(URI baseUri, URI relativeResourceUri) throws FetchException;

    String fetchAbsoluteResource(URI absoluteResourceUri) throws FetchException;
}
