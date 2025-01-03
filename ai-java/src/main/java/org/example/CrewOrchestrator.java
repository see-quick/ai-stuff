// src/main/java/org/example/CrewOrchestrator.java
package org.example;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import org.example.agents.Agent;
import org.example.config.AgentConfig;
import org.example.config.TaskConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CrewOrchestrator {

    private final Map<String, Agent> agents = new HashMap<>();
    private final Map<String, Task> tasks = new HashMap<>();

    public CrewOrchestrator(OllamaAPI api, String model) {
        // Load configs
        Map<String, AgentConfig> agentConfigs = YamlLoader.loadAgents("/agents.yaml");
        Map<String, TaskConfig> taskConfigs = YamlLoader.loadTasks("/tasks.yaml");

        // Instantiate Agents
        for (Map.Entry<String, AgentConfig> entry : agentConfigs.entrySet()) {
            agents.put(entry.getKey(), new Agent(entry.getValue(), api, model));
        }

        // Instantiate Tasks
        for (Map.Entry<String, TaskConfig> entry : taskConfigs.entrySet()) {
            // Find which agent to use
            Agent agent = agents.get(entry.getValue().agent());
            tasks.put(entry.getKey(), new Task(entry.getValue(), agent));
        }
    }

    public void kickoff(String topic) throws OllamaBaseException, IOException, InterruptedException {
        // Define the sequence of tasks to run
        String[] steps = {"research_task", "reporting_task"};

        String context;
        for (String step : steps) {
            Task task = tasks.get(step);
            if (task == null) {
                System.out.println("Task not found: " + step);
                continue;
            }

            // Run the task
            System.out.println("\n=== Running Task: " + step + " ===");
            context = task.execute(topic);
            System.out.println("Result:\n" + context);

            if (task.getOutputFile() != null) {
                // Define the path for the output file
                Path outputPath = Paths.get(task.getOutputFile());

                // Ensure the parent directories exist
                if (outputPath.getParent() != null) {
                    Files.createDirectories(outputPath.getParent());
                }

                // Write the context to the output file
                try {
                    Files.writeString(outputPath, context);
                    System.out.println("Saved output to: " + outputPath.toAbsolutePath());
                } catch (IOException e) {
                    System.err.println("Failed to write to file: " + outputPath);
                    e.printStackTrace();
                }
            }
        }
    }
}