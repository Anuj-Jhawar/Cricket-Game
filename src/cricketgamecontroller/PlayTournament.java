package cricketgamecontroller;

import databaseadd.AddTournamentToTournamentTable;

import java.util.Scanner;

public class PlayTournament {
    public static void main(String[] args) {
        Tournament tournament = new Tournament();
        String tournamentName;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter tournament name:");
        tournamentName = scanner.nextLine();
        tournament.setTournamentName(tournamentName);
        AddTournamentToTournamentTable addTournamentToTournamentTable = new AddTournamentToTournamentTable(tournamentName);
        addTournamentToTournamentTable.add();
        tournament.playTournament();
    }
}
