import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public class NOAAJson {
    public static void main(String args[]){
        String addr = "https://www.ncdc.noaa.gov/cag/global/time-series/globe/land_ocean/all/1/2018-2018.json";

        try {
            URL address = new URL(addr);
            JsonReader reader = new JsonReader(
                    new InputStreamReader(address.openStream()));

            Gson gson = new Gson();
            JsonObject root = gson.fromJson(reader, JsonObject.class);
            JsonObject data = root.getAsJsonObject("data");

            Set<Map.Entry<String, JsonElement>> entrySet = data.entrySet();
            for(Map.Entry<String,JsonElement> entry : entrySet){
                String raw_year = entry.getKey();
                String year = raw_year.substring(0, 4);
                String month = raw_year.substring(4, 6);
                Float value = entry.getValue().getAsFloat();
                System.out.println(String.format("%s %s %f", year, month, value));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
