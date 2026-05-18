package org.opendatamesh.dpds.model.blueprint;

import com.fasterxml.jackson.databind.JsonNode;
import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.visitors.DataProductVersionVisitor;

public class Blueprint extends ComponentBase {

    private String schemaVersion;
    private String blueprintUuid;
    private String blueprintName;
    private String blueprintDisplayName;
    private String blueprintVersionUuid;
    private String blueprintVersionNumber;
    private String blueprintVersionTag;
    private JsonNode parameters;

    public void accept(DataProductVersionVisitor visitor) {
        visitor.visit(this);
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public String getBlueprintUuid() {
        return blueprintUuid;
    }

    public void setBlueprintUuid(String blueprintUuid) {
        this.blueprintUuid = blueprintUuid;
    }

    public String getBlueprintName() {
        return blueprintName;
    }

    public void setBlueprintName(String blueprintName) {
        this.blueprintName = blueprintName;
    }

    public String getBlueprintVersionUuid() {
        return blueprintVersionUuid;
    }

    public void setBlueprintVersionUuid(String blueprintVersionUuid) {
        this.blueprintVersionUuid = blueprintVersionUuid;
    }

    public String getBlueprintVersionNumber() {
        return blueprintVersionNumber;
    }

    public void setBlueprintVersionNumber(String blueprintVersionNumber) {
        this.blueprintVersionNumber = blueprintVersionNumber;
    }

    public String getBlueprintVersionTag() {
        return blueprintVersionTag;
    }

    public void setBlueprintVersionTag(String blueprintVersionTag) {
        this.blueprintVersionTag = blueprintVersionTag;
    }

    public JsonNode getParameters() {
        return parameters;
    }

    public void setParameters(JsonNode parameters) {
        this.parameters = parameters;
    }

    public String getBlueprintDisplayName() {
        return blueprintDisplayName;
    }

    public void setBlueprintDisplayName(String blueprintDisplayName) {
        this.blueprintDisplayName = blueprintDisplayName;
    }
}
