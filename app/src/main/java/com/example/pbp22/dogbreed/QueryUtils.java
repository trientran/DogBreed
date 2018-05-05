package com.example.pbp22.dogbreed;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryUtils {

    /** Tag for the log messages */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Query the dataset and return a list of {@link Product} objects.
     */
    public static List<Product> fetchData() {

        // Perform HTTP request to the URL and receive a JSON response back
        List<String> jsonResponse = readURL();

        // Extract relevant fields from the JSON response and create a list of {@link Product}s
        List<Product> products = extractDataFromJson(jsonResponse);

        // Return the list of {@link Product}s
        return products;
    }

    private static List<String> readURL() {

        List<String> reponses = new ArrayList<>();

        for (String breed : getDogList()) {
            StringBuilder content = new StringBuilder();
            try {
                // create a url object
                URL url = new URL(String.format("https://dog.ceo/api/breed/%s/images/random", breed));
                // create a urlconnection object
                URLConnection urlConnection = url.openConnection();
                // wrap the urlconnection in a bufferedreader
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                // read from the urlconnection via the bufferedreader
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line + "\n");
                }
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            reponses.add(content.toString());

        }

        return reponses;
    }

    /**
     * Return a list of {@link Product} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<Product> extractDataFromJson(List<String> responses) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(responses.toString())) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        List<Product> products = new ArrayList<>();
        List<String> breedList = getDogList();

        for (int i = 0; i < responses.size(); i++) {

            try {

                // Create a JSONObject from the JSON response string
                JSONObject baseJsonResponse = new JSONObject(responses.get(i));

                String image = baseJsonResponse.getString("message");

                String breedName = breedList.get(i);

                products.add(new Product(image, breedName));

            } catch (JSONException e) {
                // If an error is thrown when executing any of the above statements in the "try" block,
                // catch the exception here, so the app doesn't crash. Print a log message
                // with the message from the exception.
                Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
            }


        }

        // Return the list of earthquakes
        return products;
    }

    private static List<String> getDogList() {

        return new ArrayList<>(Arrays.asList("affenpinscher",
                "african",
                "airedale",
                "akita",
                "appenzeller",
                "basenji",
                "beagle",
                "bluetick",
                "borzoi",
                "bouvier",
                "boxer",
                "brabancon",
                "briard",
                "bulldog",
                "bullterrier",
                "cairn",
                "chihuahua",
                "chow",
                "clumber",
                "collie",
                "coonhound",
                "corgi",
                "dachshund",
                "dalmatian",
                "dane",
                "deerhound",
                "dhole",
                "dingo",
                "doberman",
                "elkhound",
                "entlebucher",
                "eskimo",
                "germanshepherd",
                "greyhound",
                "groenendael",
                "hound",
                "husky",
                "keeshond",
                "kelpie",
                "komondor",
                "kuvasz",
                "labrador",
                "leonberg",
                "lhasa",
                "malamute",
                "malinois",
                "maltese",
                "mastiff",
                "mexicanhairless",
                "mix",
                "mountain",
                "newfoundland",
                "otterhound",
                "papillon",
                "pekinese",
                "pembroke",
                "pinscher",
                "pointer",
                "pomeranian",
                "poodle",
                "pug",
                "pyrenees",
                "redbone",
                "retriever",
                "ridgeback",
                "rottweiler",
                "saluki",
                "samoyed",
                "schipperke",
                "schnauzer",
                "setter",
                "sheepdog",
                "shiba",
                "shihtzu",
                "spaniel",
                "springer",
                "stbernard",
                "terrier",
                "vizsla",
                "weimaraner",
                "whippet",
                "wolfhound"
        ));

    }
}
