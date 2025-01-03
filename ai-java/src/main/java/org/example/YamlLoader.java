package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.example.config.AgentConfig;
import org.example.config.TaskConfig;

import java.io.InputStream;
import java.util.Map;

public class YamlLoader {

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public static Map<String, AgentConfig> loadAgents(String path) {
        try (InputStream is = YamlLoader.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + path);
            }
            return mapper.readValue(is,
                mapper.getTypeFactory().constructMapType(Map.class, String.class, AgentConfig.class));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load agents configuration", e);
        }
    }

    public static Map<String, TaskConfig> loadTasks(String path) {
        try (InputStream is = YamlLoader.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + path);
            }
            return mapper.readValue(is,
                mapper.getTypeFactory().constructMapType(Map.class, String.class, TaskConfig.class));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load tasks configuration", e);
        }
    }
}