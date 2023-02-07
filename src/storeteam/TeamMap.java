package storeteam;

import cricketgame.Team;

import java.util.HashMap;

public class TeamMap {
    private static TeamMap teamMap;
    private HashMap<String , Team> TeamMap = new HashMap<>();
    private TeamMap()
    {

    }
    public static TeamMap getTeamMap(){
        if(teamMap==null){
            synchronized (TeamMap.class){
                if(teamMap==null){
                    teamMap = new TeamMap();
                }
            }
        }
        return teamMap;
    }

    public void addTeam(String name,Team TeamToAdd){
        TeamMap.put(name,TeamToAdd);
    }
    public Team getTeam(String team){
        return TeamMap.get(team);
    }
}
