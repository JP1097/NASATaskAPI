import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MarsVideo extends APIRequest{
    @Override
    public void fetchAndPrint() throws Exception {
        String responseBody = sendRequest("https://images-api.nasa.gov/search?keywords=mars&media_type=video&year_start=2018&year_end=2018");
        JsonElement parsedResponseBody = JsonParser.parseString(responseBody);
        JsonObject collectionObject = parsedResponseBody.getAsJsonObject().getAsJsonObject("collection");
        JsonArray itemsArray = collectionObject.getAsJsonArray("items");

        int count = 0;

        for (JsonElement itemElement : itemsArray) {
            JsonObject itemObject = itemElement.getAsJsonObject();
            String link = itemObject.get("href").getAsString();
            if (link.startsWith("https") && link.endsWith(".json")) {
                String responseBodyVideoJson = sendRequest(link.replaceAll(" ", "%20"));
                JsonArray videoLinksArray = JsonParser.parseString(responseBodyVideoJson).getAsJsonArray();

                for (JsonElement videoLink : videoLinksArray) {
                    String videoLinkString = videoLink.getAsString();
                    if (videoLinkString.startsWith("http") && videoLinkString.endsWith("orig.mp4")) {
                        System.out.println(videoLinkString);
                        count++;
                        if (count == 5) {
                            return;
                        }
                    }
                }
            }
        }
    }
}

