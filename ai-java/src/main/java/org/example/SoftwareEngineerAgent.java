package org.example;

import io.github.ollama4j.OllamaAPI;

public class SoftwareEngineerAgent extends org.example.agents.Agent {
    private static final String ENGINEER_PROMPT =
        "You are a Software Engineer. Provide technical detail, code snippets, and best practices.";

    public SoftwareEngineerAgent(OllamaAPI api, String model) {
        super(api, model, ENGINEER_PROMPT);
    }
}