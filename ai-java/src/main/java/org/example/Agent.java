package org.example.agents;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.response.OllamaResult;
import io.github.ollama4j.utils.OptionsBuilder;

import java.io.IOException;

public abstract class Agent {
    protected final OllamaAPI api;
    protected final String model;
    protected final String rolePrompt;

    public Agent(OllamaAPI api, String model, String rolePrompt) {
        this.api = api;
        this.model = model;
        this.rolePrompt = rolePrompt;
    }

    public String respond(String userPrompt) throws OllamaBaseException, IOException, InterruptedException {
        String prompt = rolePrompt + "\n\nUser: " + userPrompt + "\nAgent:";
        OllamaResult result = api.generate(model, prompt, false, new OptionsBuilder().build());
        return result.getResponse();
    }
}