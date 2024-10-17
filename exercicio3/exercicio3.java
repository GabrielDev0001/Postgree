
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.JSONObject;

public class CognitiveServiceExample {

    private static final String subscriptionKey = "b0aa8653-7b40-4dd9-a9e0-7579f719dfb3";
    private static final String endpoint = "https://portal.azure.com/?Microsoft_Azure_Education_correlationId=ceb7d702-708c-449e-9258-c687746f7d51&Microsoft_Azure_Education_newA4E=true&Microsoft_Azure_Education_asoSubGuid=2aa3dd40-351c-42c5-919c-7135ce7c6081#@sgapucminasbr.onmicrosoft.com/resource/subscriptions/2aa3dd40-351c-42c5-919c-7135ce7c6081/resourceGroups/Gabriel/providers/Microsoft.DBforPostgreSQL/flexibleServers/gabriel/overview";
    
    public static void main(String[] args) throws Exception {
        String imagePath = ".../Desktop/Screenshot_2";

        Path imagePathPath = Paths.get(imagePath);
        byte[] imageBytes = Files.readAllBytes(imagePathPath);
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endpoint + "?visualFeatures=Categories,Description,Color"))
            .header("Ocp-Apim-Subscription-Key", subscriptionKey)
            .header("Content-Type", "application/octet-stream")
            .POST(HttpRequest.BodyPublishers.ofByteArray(imageBytes))
            .build();
        
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        JSONObject jsonResponse = new JSONObject(response.body());
        System.out.println(jsonResponse.toString(2));
    }
}
