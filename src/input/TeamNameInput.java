package input;

import cricketgame.Team;

import java.util.Scanner;

public class TeamNameInput implements InputInterface{
    Team team;
    public TeamNameInput(Team Team){
        this.team = Team;
    }
    public void collectInput(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Please Enter Team " + (Team.getTeamCount()+1) + " Name: ");
        Team.incrementTeamCount();
        String TeamName = scn.nextLine();
        team.setTeamName(TeamName);
    }
}
