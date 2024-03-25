import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MarsPhoto extends APIRequest{
    @Override
    public void fetchAndPrint() throws Exception {
        String responseBody = sendRequest("https://images-api.nasa.gov/search?q=mars%20surface&media_type=image&year_start=2018&year_end=2018");
        JsonElement parsedResponseBody = JsonParser.parseString(responseBody);
        JsonObject collectionObject = parsedResponseBody.getAsJsonObject().getAsJsonObject("collection");
        JsonArray itemsArray = collectionObject.getAsJsonArray("items");

        int count = 0;

        for (JsonElement itemElement : itemsArray) {
            JsonObject itemObject = itemElement.getAsJsonObject();
            JsonArray linksArray = itemObject.getAsJsonArray("links");

            for (JsonElement linkElement : linksArray) {
                JsonObject linkObject = linkElement.getAsJsonObject();
                String link = linkObject.get("href").getAsString();

                if (link.startsWith("https") && link.endsWith(".jpg")) {
                    System.out.println(link);
                    count++;
                }
                if (count == 5) {
                    return;
                }
            }
        }
    }
}
