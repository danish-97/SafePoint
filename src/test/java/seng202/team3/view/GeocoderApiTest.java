package seng202.team3.view;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.IOException;

public class GeocoderApiTest {

    @Test
    /**
     * Testing the latitude field of the address.
     */
    public void checkLatitude() throws IOException, InterruptedException {
        Double lat = null;
        GeocoderApi geocoder = new GeocoderApi();
        String res = geocoder.doRequest("Paris,France");
        JSONObject obj = new JSONObject(res);
        JSONArray data = obj.getJSONArray("results");
        for (int i = 0; i < data.length(); i++) {
            JSONObject result = data.getJSONObject(i);
            lat = result.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
        }
        assertEquals(48.856614, lat);

    }

    @Test
    public void checkLongitude() throws IOException, InterruptedException {
        Double lon = null;
        GeocoderApi geocoder = new GeocoderApi();
        String res = geocoder.doRequest("Paris,France");
        JSONObject obj = new JSONObject(res);
        JSONArray data = obj.getJSONArray("results");
        for (int i = 0; i < data.length(); i++) {
            JSONObject result = data.getJSONObject(i);
            lon = result.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
        }
        assertEquals(2.3522219, lon);
    }



}
