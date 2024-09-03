package edu.virginia.cs.nbateams;

import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.stream.*;

public class BallDontLieReader {
    public static final String ballDontLieAPITeamsApiURL = "https://api.balldontlie.io/v1/teams";


    public JSONObject getAllNBATeams() {
        try {
            String jsonText = getJSONText();
            System.out.println(jsonText);
            return new JSONObject(jsonText);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private String getJSONText() throws IOException, URISyntaxException {
        InputStreamReader apiReader = getAPIReader();
        return getTextFromAPIReader(apiReader);
    }

    private String getTextFromAPIReader(InputStreamReader apiReader) {
        BufferedReader bufferedReader = new BufferedReader(apiReader);
        return bufferedReader.lines().collect(Collectors.joining());
    }

    private InputStreamReader getAPIReader() throws IOException, URISyntaxException {
        URL ballDontLieAPI = new URI(ballDontLieAPITeamsApiURL).toURL();
        URLConnection urlConnection = ballDontLieAPI.openConnection();

        // Students: Never do this! Never post an un-obscured API key! Never include the API
        // key in a code repository! I only do this here for lack of a better option, as this
        // feature was changed to require in API key in early 2024
        urlConnection.setRequestProperty("Authorization", "4fdbf99a-3d36-4fca-82ec-848fe50a62e4");

        InputStream apiStream = urlConnection.getInputStream();
        return new InputStreamReader(apiStream, StandardCharsets.UTF_8);
    }
}
