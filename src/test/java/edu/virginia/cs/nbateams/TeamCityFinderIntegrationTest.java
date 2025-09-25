package edu.virginia.cs.nbateams;

import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import org.json.JSONObject;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamCityFinderIntegrationTest {
    private CityTeamFinder testTeamFinder;
    private BallDontLieReader mockBDLReader;
    private NBATeamReader teamReader;
    private static NBATeam CELTICS = new NBATeam(2, "Celtics", "Boston", "BOS",
            Conference.EASTERN, Division.ATLANTIC);

    @BeforeEach
    public void setup() {
        mockBDLReader = mock(BallDontLieReader.class);
        teamReader = new NBATeamReader(mockBDLReader);
        testTeamFinder = new CityTeamFinder(teamReader);
    }


    @Test
    public void testSingleCityNBATeamReaderIntegrationOnly() {
        when(mockBDLReader.getAllNBATeams()).thenReturn(getMockJSONObject());
        Set<NBATeam> teamsInBoston = testTeamFinder.getTeamsFromLocations(List.of("Boston"));
        HashSet<NBATeam> expected = new HashSet<>(Set.of(CELTICS));
        assertEquals(expected, teamsInBoston);
    }

    private JSONObject getMockJSONObject() {
        String mockJSONString = """
                {
                  "data":[
                     {"id":2,"abbreviation":"BOS","city":"Boston","conference":"East","division":"Atlantic",
                             "full_name":"Boston Celtics","name":"Celtics"},
                     {"id":14,"abbreviation":"LAL","city":"Los Angeles","conference":"West","division":"Pacific",
                             "full_name":"Los Angeles Lakers","name":"Lakers"}
                    ]
                }
                """;

        JSONObject teamsJSONObject = new JSONObject(mockJSONString);
        return teamsJSONObject;

    }

}