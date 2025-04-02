package org.opendatamesh.dpds.extensions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.opendatamesh.dpds.model.DataProductVersion;
import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.model.interfaces.Port;
import org.opendatamesh.dpds.parser.Parser;
import org.opendatamesh.dpds.parser.ParserFactory;
import org.opendatamesh.dpds.visitors.core.StandardDefinitionVisitor;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomDefinitionTest {

    @Test
    public void testCustomDefinitionParsing() throws IOException {
        JsonNode initialJson = new ObjectMapper().readTree(this.getClass().getResource("data_product_descriptor_with_custom_definitions.json"));

        Parser parser = ParserFactory.getParser()
                .register(new CustomDefinitionConverter());

        DataProductVersion pojo = parser.deserialize(initialJson);

        JsonNode json = parser.serialize(pojo);

        assertThat(json).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(initialJson);
        assertThat(pojo.getInterfaceComponents().getOutputPorts()).hasSize(2);

        Port outputPort = pojo.getInterfaceComponents().getOutputPorts()
                .stream()
                .filter(port -> port.getName().equalsIgnoreCase("outputPortName")).findFirst().orElse(null);

        Port outputPort2 = pojo.getInterfaceComponents().getOutputPorts()
                .stream()
                .filter(port -> port.getName().equalsIgnoreCase("outputPortName2")).findFirst().orElse(null);

        assertThat(outputPort).isNotNull();
        assertThat(outputPort2).isNotNull();

        assertThat(outputPort.getPromises().getApi().getDefinition()).isInstanceOf(CustomDefinition.class);
        assertThat(outputPort.getPromises().getApi().getDefinition().getParsedProperties()).isEmpty();
        assertThat(outputPort.getPromises().getApi().getDefinition().getAdditionalProperties()).hasSize(1);

        assertThat(outputPort2.getPromises().getApi().getDefinition()).isInstanceOf(ComponentBase.class);
        assertThat(outputPort2.getPromises().getApi().getDefinition()).isNotInstanceOf(CustomDefinition.class);
    }

    @Test
    public void testCustomDefinitionVisitor() throws IOException {
        JsonNode initialJson = new ObjectMapper().readTree(this.getClass().getResource("data_product_descriptor_with_custom_definitions.json"));

        Parser parser = ParserFactory.getParser()
                .register(new CustomDefinitionConverter());

        DataProductVersion pojo = parser.deserialize(initialJson);
        StandardDefinitionVisitor visitor = new CustomDefinitionVisitor();

        Port outputPort = pojo.getInterfaceComponents().getOutputPorts()
                .stream()
                .filter(port -> port.getName().equalsIgnoreCase("outputPortName")).findFirst().orElse(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                visitor.visit(outputPort.getPromises().getApi().getDefinition())
        );

        assertThat(exception.getMessage()).isEqualTo("OK");
    }

}
