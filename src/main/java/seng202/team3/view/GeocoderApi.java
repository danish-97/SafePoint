package seng202.team3.view;



import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Class which takes an input address and Geocodes it to get the result in a Json string text.
 * @author Danish Jahangir
 */
public class GeocoderApi {

    /**
     * Takes the input address and Geocodes it to get the result as a Json text String.
     * @param address is the input address to be geocoded.
     * @return the Json text string
     * @throws IOException if the input is invalid.
     * @throws InterruptedException if there is an interruption when it is getting the result.
     */
    public String doRequest(String address) throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(
                URI.create("https://maps.googleapis.com/maps/api/geocode/json?address="+ address +"&key=your-api-key"))
                .header("accept", "application/json").build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
