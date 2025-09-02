package edu.virginia.cs.nbateams;

import java.util.*;

public class StubNBATeamReader extends NBATeamReader {

    public static final NBATeam LAKERS = new NBATeam(1,"Lakers","Los Angelos","LAL",
            Conference.WESTERN, Division.PACIFIC);
    public static final NBATeam CELTICS = new NBATeam(2, "Celtics", "Boston", "BOS",
            Conference.EASTERN, Division.ATLANTIC);

    public List<NBATeam> getNBATeams() {
        return generateFakeNBATeamList();
    }

    private List<NBATeam> generateFakeNBATeamList() {
        return List.of(LAKERS, CELTICS);
    }
}
