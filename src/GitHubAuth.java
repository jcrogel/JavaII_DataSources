import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class GitHubAuth {
    public static void main(String args[]){
        String addr = "https://api.github.com/user";

        try {
            URL address = new URL(addr);

            Scanner scan = new Scanner(new FileReader("src/token.txt"));
            String token = scan.nextLine();

            URLConnection uc = address.openConnection();
            String basicAuth = "token " + token;
            uc.setRequestProperty ("Authorization", basicAuth);
            InputStream in = uc.getInputStream();
            JsonReader reader = new JsonReader(new InputStreamReader(in));

            Gson gson = new Gson();
            JsonObject root = gson.fromJson(reader, JsonObject.class);
            System.out.println(root.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
