package org.example;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws OllamaBaseException, IOException, InterruptedException {
        // Start Ollama (server must be running)
        OllamaAPI api = new OllamaAPI("http://localhost:11434/");
        api.setRequestTimeoutSeconds(60);

        if (api.ping()) {
            System.out.println("Ollamma server is running...");

            // Model can be "llama2", "llama2:7B", etc.
            String model = "llama3.2:1b";

            CrewOrchestrator orchestrator = new CrewOrchestrator(api, model);

            // Kick off tasks with your "topic"
            String topic = "AI Agents";
            orchestrator.kickoff(topic);
        } else {
            System.err.println("Olamma is not running make sure you do `ollama serve`");
        }
    }
}
