package org.opendatamesh.dpds.location;

import org.opendatamesh.dpds.exceptions.FetchException;

import java.net.URI;

public class UriLocation implements DescriptorLocation {

    protected boolean opened;

    private URI rootDocumentUri;
    private URI rootDocumentBaseUri;
    private String rootDocumentFileName;
    private String rootDocContent;

    private UriFetcher fetcher;

    public UriLocation(String descriptorContent) {
        rootDocContent = descriptorContent;
        fetcher = new UriFetcher();
        opened = true;
    }

    public UriLocation(URI descriptorUri) {
        setDescriptorUri(descriptorUri);
        fetcher = new UriFetcher();
        opened = true;
    }

    UriLocation() {
        fetcher = new UriFetcher();
        opened = true;
    }

    void setDescriptorUri(URI descriptorUri) {
        rootDocumentUri = descriptorUri.normalize();
        rootDocumentBaseUri = UriUtils.getResourcePathUri(rootDocumentUri);
        rootDocumentFileName = UriUtils.getResourceName(rootDocumentUri);
    }

    @Override
    public String fetchRootDoc() throws FetchException {
        if (opened == false) {
            throw new FetchException("Impossible to fecth a closed location", rootDocumentUri);
        }

        if (rootDocContent == null) {
            rootDocContent = fetcher.fetchRelativeResource(rootDocumentBaseUri, rootDocumentUri);
        }
        return rootDocContent;
    }

    @Override
    public String fetchRelativeResource(URI baseURI, URI relativeResourceUri) throws FetchException {
        if (opened == false) {
            throw new FetchException("Impossible to fecth a closed location", relativeResourceUri);
        }
        return fetcher.fetchRelativeResource(baseURI, relativeResourceUri);
    }

    @Override
    public String fetchAbsoluteResource(URI absoluteResourceUri) throws FetchException {
        if (opened == false) {
            throw new FetchException("Impossible to fecth a closed location", absoluteResourceUri);
        }
        return fetcher.fetchAbsoluteResource(absoluteResourceUri);
    }

    @Override
    public URI getRootDocumentBaseUri() {
        return rootDocumentBaseUri;
    }

    @Override
    public void setRootDocumentBaseUri(URI rootDocumentBaseUri) {
        this.rootDocumentBaseUri = rootDocumentBaseUri;
    }

    @Override
    public void open() throws FetchException {
        opened = true;
    }

    @Override
    public void close() throws FetchException {
        opened = false;
    }
}