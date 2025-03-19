package org.opendatamesh.dpds.model.info;

import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.visitors.info.InfoVisitor;

public class ContactPoint extends ComponentBase {

    private String name;
    private String description;
    private String channel;
    private String address;

    public void accept(InfoVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
