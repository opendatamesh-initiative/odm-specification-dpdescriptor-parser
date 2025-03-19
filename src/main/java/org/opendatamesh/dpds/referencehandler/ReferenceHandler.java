package org.opendatamesh.dpds.referencehandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.opendatamesh.dpds.model.core.ComponentBase;
import org.opendatamesh.dpds.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.opendatamesh.dpds.referencehandler.utils.JacksonUtils.mergeJsonNodes;

/**
 * Utility class used inside the data product descriptor visitors implementations
 * to resolve references based on the {@link DescriptorFormat}
 */
public class ReferenceHandler {

    private final ObjectMapper jsonMapper;
    private final ObjectMapper yamlMapper;
    private final DescriptorFormat format;
    private final Path descriptorRootPath;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ReferenceHandler(DescriptorFormat format, Path descriptorRootPath) {
        this.format = format;
        this.descriptorRootPath = descriptorRootPath;
        this.jsonMapper = new ObjectMapper();
        configObjectMapper(jsonMapper);
        this.yamlMapper = new ObjectMapper(new YAMLFactory());
        configObjectMapper(yamlMapper);
    }

    public <T extends ComponentBase> void handleComponentBaseReference(T componentBase) {
        if (componentBase == null) {
            return;
        }
        switch (format) {
            case CANONICAL:
                if (StringUtils.hasText(componentBase.getRef()) && isMediaTypeDeserializable(componentBase.getMediaType())) {
                    loadAttributesFromRef(componentBase.getRef(), componentBase);
                }
                break;
            case NORMALIZED:
                if (!StringUtils.hasText(componentBase.getRef())) {
                    break; //Ignores it
                }
                String ref = componentBase.getRef();
                storeEntityContent(ref, componentBase);
                clearEntityAttributes(componentBase);
                componentBase.setRef(ref);
                break;
        }

    }

    private void storeEntityContent(String ref, Object entity) {
        File targetFile = resolveFile(ref);
        try {
            generateParentDirsIfNotExist(targetFile);

            if (targetFile.exists()) {
                String rawContent = Files.readString(targetFile.toPath(), StandardCharsets.UTF_8);
                JsonNode sourceFile = getMapper(ref).readTree(rawContent);
                //Using plain ObjectMapper so also null values are represented
                JsonNode pojoContent = new ObjectMapper().valueToTree(entity);
                JsonNode mergedContent = mergeJsonNodes(getMapper(ref), sourceFile, pojoContent);
                Object contentToSave = getMapper(ref).treeToValue(mergedContent, Object.class);
                getMapper(ref).writerWithDefaultPrettyPrinter().writeValue(targetFile, contentToSave);
            } else {
                getMapper(ref).writerWithDefaultPrettyPrinter().writeValue(targetFile, entity);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to store entity content in file: " + ref, e);
        }
    }

    private void generateParentDirsIfNotExist(File targetFile) {
        File parentDir = targetFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    private <T> void loadAttributesFromRef(String ref, T targetObject) {
        if (ref == null || ref.isEmpty()) {
            return;
        }
        try {
            File file = resolveFile(ref);
            if (!file.exists()) {
                logger.warn("Reference file not found: {}", ref);
            } else {
                getMapper(ref).readerForUpdating(targetObject).readValue(file);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load reference file: " + ref, e);
        }
    }

    private void clearEntityAttributes(Object entity) {
        Class<?> clazz = entity.getClass();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    field.set(entity, null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to clear attribute: " + field.getName(), e);
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    private File resolveFile(String ref) {
        if (descriptorRootPath == null) {
            throw new IllegalStateException("ReferenceHandler: missing descriptor root path");
        }
        return descriptorRootPath.getParent().resolve(ref).normalize().toFile();
    }

    private boolean isMediaTypeDeserializable(String mediaType) {
        if (mediaType == null) return true;
        return mediaType.toLowerCase().contains("json") || mediaType.toLowerCase().contains("yaml") || mediaType.toLowerCase().contains("yml");
    }


    private void configObjectMapper(ObjectMapper objectMapper) {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    private ObjectMapper getMapper(String ref) {
        if (ref.endsWith(".json")) {
            return jsonMapper;
        } else if (ref.endsWith(".yaml") || ref.endsWith(".yml")) {
            return yamlMapper;
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + ref);
        }
    }
}
