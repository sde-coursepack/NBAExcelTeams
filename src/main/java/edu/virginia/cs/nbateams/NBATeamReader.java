package edu.virginia.cs.nbateams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;



public class NBATeamReader {
    public static final int MAXIMUM_MODERN_TEAM_ID = 30;

    private List<NBATeam> memo = new ArrayList<>();
    private final BallDontLieReader apiReader;

    protected NBATeamReader(BallDontLieReader apiReader) {
        this.apiReader = apiReader;
    }
    public NBATeamReader() {
        this(new BallDontLieReader());
    }



    public List<NBATeam> getNBATeams() {
        if (!memo.isEmpty()) {
            return memo;
        }
        memo = getTeamsFromAPI();
        return memo;
    }

    private List<NBATeam> getTeamsFromAPI() {
        JSONArray teamArray = getTeamsArrayFromAPI();
        List<NBATeam> teamList = new ArrayList<>();
        for (Object teamObject : teamArray) {
            if (isModernNBATeam(teamObject)) {
                JSONObject teamJSon = (JSONObject) teamObject;
                NBATeam newTeam = getTeamFromJSONObject(teamJSon);
                teamList.add(newTeam);
            }
        }
        return teamList;
    }

    private boolean isModernNBATeam(Object teamObject) {
        if (!(teamObject instanceof JSONObject teamJSon)) {
            return false;
        }
        return teamJSon.getInt("id") <= MAXIMUM_MODERN_TEAM_ID;
    }

    private JSONArray getTeamsArrayFromAPI() {
        JSONObject apiOutput = apiReader.getAllNBATeams();
        return apiOutput.getJSONArray("data");
    }

    private NBATeam getTeamFromJSONObject(JSONObject teamJSon) {
        int id = teamJSon.getInt("id");
        String abbreviation = teamJSon.getString("abbreviation");
        String city = teamJSon.getString("city");
        String name = teamJSon.getString("name");

        Conference conference = getConference(teamJSon);
        Division division = getDivision(teamJSon);

        return new NBATeam(id, name, city, abbreviation,
                conference, division);
    }

    private Division getDivision(JSONObject teamJSon) {
        String divisionText = teamJSon.getString("division");
        return Division.getDivision(divisionText);
    }

    private Conference getConference(JSONObject teamJSon) {
        String conferenceText = teamJSon.getString("conference");
        return Conference.getConference(conferenceText);
    }

}
