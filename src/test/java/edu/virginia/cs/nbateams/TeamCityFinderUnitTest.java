package edu.virginia.cs.nbateams;

import org.junit.jupiter.api.*;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class TeamCityFinderUnitTest {
    private CityTeamFinder testTeamFinder;
    private NBATeamReader mockTeamReader;

    private static NBATeam LAKERS = new NBATeam(1,"Lakers","Los Angelos","LAL", Conference.WESTERN, Division.PACIFIC);
    private static NBATeam CELTICS = new NBATeam(2, "Celtics", "Boston", "BOS", Conference.EASTERN, Division.ATLANTIC);

    @BeforeEach
    public void setup() {
        mockTeamReader = mock(NBATeamReader.class);
        testTeamFinder = new CityTeamFinder(mockTeamReader);
    }

    @Test
    public void testSingleCity() {
        when(mockTeamReader.getNBATeams()).thenReturn(List.of(LAKERS, CELTICS));

        Set<NBATeam> teamsInBoston = testTeamFinder.getTeamsFromLocations(List.of("Boston"));

        assertEquals(new HashSet<NBATeam>(Set.of(CELTICS)), teamsInBoston);
    }
}
