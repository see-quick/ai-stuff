package org.example.agents;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.response.OllamaResult;
import io.github.ollama4j.utils.OptionsBuilder;
import org.example.config.AgentConfig;

import java.io.IOException;


public class Agent {
    private final AgentConfig config;
    private final OllamaAPI api;
    private final String model;

    public Agent(AgentConfig config, OllamaAPI api, String model) {
        this.config = config;
        this.api = api;
        this.model = model;
    }

    public String run(String prompt) throws OllamaBaseException, IOException, InterruptedException {
        // Prepare final prompt using placeholders
        String finalPrompt = String.format(
            "%s\n\nGoal: %s\nBackstory: %s\nUser Prompt: %s",
            this.config.role(), this.config.goal(), this.config.backstory(), prompt
        );

        OllamaResult result = api.generate(model, finalPrompt, false, new OptionsBuilder().build());
        return result.getResponse();
    }
}