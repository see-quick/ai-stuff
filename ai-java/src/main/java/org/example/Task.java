package org.example;

import io.github.ollama4j.exceptions.OllamaBaseException;
import org.example.config.TaskConfig;

import java.io.IOException;

public class Task {
    private final TaskConfig config;
    private final org.example.agents.Agent agent;

    public Task(TaskConfig config, org.example.agents.Agent agent) {
        this.config = config;
        this.agent = agent;
    }

    public String execute(String topic) throws OllamaBaseException, IOException, InterruptedException {
        // Replace {topic} in the description
        String prompt = config.description().replace("{topic}", topic);
        return agent.run(prompt);
    }

    public String getExpectedOutput() {
        return config.expectedOutput();
    }

    public String getOutputFile() {
        return config.outputFile();
    }
}