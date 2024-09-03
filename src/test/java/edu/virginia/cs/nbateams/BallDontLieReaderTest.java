package edu.virginia.cs.nbateams;

import org.json.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class BallDontLieReaderTest {
    private static JSONObject readerOutput;

    @BeforeAll
    public static void initialize() {
        BallDontLieReader reader = new BallDontLieReader();
        readerOutput = reader.getAllNBATeams();
    }

    @Test
    public void readerOutputNotNull() {
        assertNotNull(readerOutput);
    }

    @Test
    public void readerOutputContainsData() {
        assertTrue(readerOutput.has("data"));
    }

    @Test
    public void readerOutputReturnsExpectedTeamNumber() {
        JSONArray array = readerOutput.getJSONArray("data");
        assertFalse(array.isEmpty());
    }

}