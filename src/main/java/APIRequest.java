import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class APIRequest {
    private final HttpClient client;

    public APIRequest() {
        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }
     protected String sendRequest(String uri) throws Exception {
         HttpRequest request = HttpRequest.newBuilder()
                 .uri(URI.create(uri))
                 .GET()
                 .build();

         HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

         if (response.statusCode() == 200) {
             return response.body();
         } else {
             System.out.println("Failed to fetch data: " + response.statusCode());
             return null;
         }
     }
     public abstract void fetchAndPrint() throws Exception;
}
