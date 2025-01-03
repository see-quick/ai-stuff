import io.github.ollama4j.OllamaAPI;

public class OllamaAPITest {

    public static void main(String[] args) {
        OllamaAPI ollamaAPI = new OllamaAPI();

        boolean isOllamaServerReachable = ollamaAPI.ping();

        System.out.println("Is Ollama server running: " + isOllamaServerReachable);
    }
}
