package org.opendatamesh.dpds.model.definitions;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.opendatamesh.dpds.model.core.ReferenceObjectDPDS;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionDPDSVisitor;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefinitionReferenceDPDS extends ReferenceObjectDPDS {

    public void accept(StandardDefinitionDPDSVisitor visitor) {
        visitor.visit(this);
    }
}
