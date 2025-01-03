package org.example;

import io.github.ollama4j.OllamaAPI;

public class ResearcherAgent extends org.example.agents.Agent {
    private static final String RESEARCHER_PROMPT =
        "You are a Researcher. Use reliable sources and provide concise factual insights.";

    public ResearcherAgent(OllamaAPI api, String model) {
        super(api, model, RESEARCHER_PROMPT);
    }
}