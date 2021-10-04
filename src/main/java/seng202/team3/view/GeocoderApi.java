package seng202.team3.view;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GeocoderApi {

    public String doRequest(String address) throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(
                URI.create("https://maps.googleapis.com/maps/api/geocode/json?address="+ address +"&key=AIzaSyArZlTsI6ekABAXnxX2XKFSGNxvG6evMxA"))
                .header("accept", "application/json").build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
