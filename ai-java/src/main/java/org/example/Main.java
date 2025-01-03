package org.example;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws OllamaBaseException, IOException, InterruptedException {
        final String modelName = "llama3.2:1b";

        OllamaAPI api = new OllamaAPI("http://localhost:11434/");
        api.setRequestTimeoutSeconds(60);

        ResearcherAgent researcher = new ResearcherAgent(api, modelName);
        SoftwareEngineerAgent engineer = new SoftwareEngineerAgent(api, modelName);

        System.out.println("Researcher says: " +
            researcher.respond("What are the latest findings in AI?"));

        System.out.println("Software Engineer says: " +
            engineer.respond("How do I implement a REST API in Java?"));
    }
}