package org.opendatamesh.dpds.extensions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.opendatamesh.dpds.model.DataProductVersion;
import org.opendatamesh.dpds.parser.Parser;
import org.opendatamesh.dpds.parser.ParserFactory;
import org.opendatamesh.dpds.visitors.core.ComponentBaseVisitor;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentBaseExtensionTest {

    @Test
    public void testExtension() throws IOException {
        JsonNode initialJson = new ObjectMapper().readTree(this.getClass().getResource("data_product_descriptor_extension.json"));

        Parser parser = ParserFactory.getParser()
                .register(new ComponentBaseDumbExtensionConverter());

        DataProductVersion pojo = parser.deserialize(initialJson);
        JsonNode json = parser.serialize(pojo);

        assertThat(json).usingRecursiveComparison().isEqualTo(initialJson);
        assertThat(pojo.getAdditionalProperties()).hasSize(1);
        assertThat(pojo.getParsedProperties()).hasSize(1);
        assertThat(pojo.getParsedProperties().get("dumb_extension")).isInstanceOf(ComponentBaseDumbExtension.class);
    }

    @Test
    public void testExtensionVisitor() throws IOException {

        JsonNode initialJson = new ObjectMapper().readTree(this.getClass().getResource("data_product_descriptor_extension.json"));

        Parser parser = ParserFactory.getParser()
                .register(new ComponentBaseDumbExtensionConverter());

        DataProductVersion dataProductVersion = parser.deserialize(initialJson);
        ComponentBaseVisitor visitor = new ComponentBaseDumbExtensionVisitorImpl();

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                dataProductVersion.getParsedProperties()
                        .values()
                        .forEach(visitor::visit)
        );

        assertThat(exception.getMessage()).isEqualTo("OK");
    }

}
